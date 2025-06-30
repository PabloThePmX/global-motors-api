using GlobalMotors.OrdersService.Context;
using GlobalMotors.OrdersService.Models;
using Microsoft.EntityFrameworkCore;
using Microsoft.OpenApi.Models;
using System.Text.Json.Serialization;
using Microsoft.AspNetCore.Mvc;
using JsonOptions = Microsoft.AspNetCore.Http.Json.JsonOptions;
using GlobalMotors.OrdersService.Models.DTO;
using Swashbuckle.AspNetCore.SwaggerGen;
using Steeltoe.Discovery.Client;
using GlobalMotors.OrdersService.Models.Enums;

var builder = WebApplication.CreateBuilder(args);

// Add services to the container.
// Learn more about configuring Swagger/OpenAPI at https://aka.ms/aspnetcore/swashbuckle
builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen(opt =>
{
    opt.SwaggerDoc("v1", new OpenApiInfo
    {
        Version = "v1",
        Title = "Orders Service",
        Description = "Documentação da API do Microsserviço Orders, contendo rotas para Pedidos (Orders), Fretes (Shippings) e Carrinhos (Carts).",
    });

    opt.EnableAnnotations();
    opt.UseInlineDefinitionsForEnums();

});

builder.Services.AddDbContextFactory<GlobalMotorsContext>(opt =>
    opt.UseNpgsql(builder.Configuration.GetConnectionString("DefaultConnection"), en =>
    {
        en.MapEnum<PaymentTypes>(enumName: "payment_types");
        en.MapEnum<TransactionStatus>(enumName: "transaction_status");
    })
);

builder.Services.AddDiscoveryClient(builder.Configuration);
builder.Services.AddHealthChecks();

builder.Services.Configure<JsonOptions>(opt =>
{
    opt.SerializerOptions.Converters.Add(new JsonStringEnumConverter());
});

var app = builder.Build();

var scope = app.Services.CreateScope();
var context = scope.ServiceProvider.GetRequiredService<GlobalMotorsContext>();

// Configure the HTTP request pipeline.
app.UseSwagger();
app.UseSwaggerUI();
app.UseHealthChecks("/health");

app.UseHttpsRedirection();

#region Orders

app.MapGet("/orders/{userId}", async ([FromRoute] Guid userId) =>
{
    var order = await context.Orders.Where(x => x.Buyer == userId).FirstOrDefaultAsync();

    return (order == null) ? Results.NotFound() : Results.Ok(order);

})
.WithTags("Orders");

app.MapPost("/orders", async ([FromBody] OrderDTO order) =>
{
    #region Create Order

    var newOrder = await context.Orders.AddAsync(MapDtoToOrder(order));

    if (newOrder == null)
        return Results.Problem();

    //precisa para criar o id e usa-lo depois
    await context.SaveChangesAsync();

    #endregion

    #region Add cars of the cart in the cars of the new order

    var carItems = await context.CartItems.Where(x => x.User == order.Buyer).Select(x => x.Car).ToListAsync();

    if (carItems == null)
        return Results.BadRequest("Não existem itens no carrinho do usuário. O pedido foi criado vazio.");

    var OrderItems = new List<OrderItem>();
    carItems.ForEach(car => 
        OrderItems.Add(new OrderItem() { Car = car, Order = newOrder.Entity.Id }
    ));

    await context.OrderItems.AddRangeAsync(OrderItems);

    await context.SaveChangesAsync();

    #endregion

    #region Create Shipping
    //TODO: PARA CRIAR O FRETE CHAMA O ADDRESS PEGANDO O ID DO CURRENT ADDRESS DO USUARIO DA ORDEM 
    #endregion

    #region Delete cars from user's cart

    context.CartItems.RemoveRange(await context.CartItems.Where(x => x.User == order.Buyer).ToListAsync());
    await context.SaveChangesAsync();

    #endregion

    return Results.Ok(newOrder.Entity.Id);
})
.WithTags("Orders");

app.MapPut("/orders/{id}", async ([FromRoute] Guid id, [FromBody] Order order) =>
{
    var oldOrder = await context.Orders.FindAsync(id);

    if (oldOrder == null)
        return Results.NotFound();

    if (oldOrder.Id != id)
        return Results.BadRequest();

    context.Entry(oldOrder).CurrentValues.SetValues(order);

    await context.SaveChangesAsync();

    return Results.Ok();
})
.WithTags("Orders");

app.MapGet("/orders/{id}/items", async ([FromRoute] Guid id) =>
{
    var order = await context.Orders.FindAsync(id);

    if(order == null)
        return Results.NotFound();

    var orderItems = await context.OrderItems.Where(x => x.Order == order.Id).Select(x => x.Car).ToListAsync();

    return orderItems == null ? Results.NotFound() : Results.Ok(orderItems);
})
.WithTags("Orders");

#endregion

# region Shipping

app.MapGet("orders/shippings/{id}", async ([FromRoute] Guid id) =>
{
    var shipping = await context.Shippings.FindAsync(id);

    return shipping == null ? Results.NotFound() : Results.Ok(shipping);
})
.WithTags("Shippings");

app.MapPost("/orders/shippings", async ([FromBody] ShippingDTO shipping) =>
{
    var newShipping = await context.Shippings.AddAsync(MapDtoToShipping(shipping));

    if (newShipping == null)
        return Results.Problem();

    await context.SaveChangesAsync();

    return Results.Ok(newShipping.Entity.Id);
})
.WithTags("Shippings")
.WithDescription("Pode deixar que em um primeiro momento, o backend vai criar o frete.");

app.MapPut("/orders/shippings/{id}", async ([FromRoute] Guid id, [FromBody] Shipping shipping) =>
{
    var oldShipping = await context.Shippings.FindAsync(id);

    if (oldShipping == null)
        return Results.NotFound();

    if (oldShipping.Id != id)
        return Results.BadRequest();

    context.Entry(oldShipping).CurrentValues.SetValues(shipping);

    await context.SaveChangesAsync();

    return Results.Ok();
})
.WithTags("Shippings");

#endregion

#region Cart

app.MapGet("/orders/cart/{userId}", async ([FromRoute] Guid userId) =>
{
    var cartItems = await context.CartItems.Where(x => x.User == userId).Select(x => x.Car).ToListAsync();

    return cartItems == null ? Results.NotFound() : Results.Ok(cartItems);
})
.WithDescription("Retorna apenas os itens do carrinho do usuário.")
.WithTags("Carts");

app.MapPost("/orders/cart", async ([FromBody] CartItem cartItem) =>
{
    var newCartItem = await context.CartItems.AddAsync(cartItem);

    if (newCartItem == null)
        return Results.Problem();

    await context.SaveChangesAsync();

    return Results.Ok();
})
.WithTags("Carts");

app.MapDelete("/orders/cart/{userId}/{carId}", async ([FromRoute] Guid userId, [FromRoute] Guid carId) =>
{
    var cartItem = await context.CartItems.FindAsync(userId, carId);

    if (cartItem == null)
        return Results.NotFound();

    if (cartItem.User != userId || cartItem.Car != carId)
        return Results.BadRequest();

    context.CartItems.Remove(cartItem);

    await context.SaveChangesAsync();

    return Results.Ok();
})
.WithTags("Carts");

#endregion

Order MapDtoToOrder(OrderDTO order) => new Order 
{
    OrderNumber = order.OrderNumber,
    Buyer = order.Buyer,
    Delivered = order.Delivered,
    FinalPrice = order.FinalPrice,
    PaymentType = order.PaymentType,
    Status = order.Status
};

Shipping MapDtoToShipping(ShippingDTO shipping) => new Shipping
{
    ShippingPrice = shipping.ShippingPrice,
    ShippingAddress = shipping.ShippingAddress,
    Order = shipping.Order,
    Tracking = shipping.Tracking,
    EstimatedDelivery = shipping.EstimatedDelivery,
    DeliveredAt = shipping.DeliveredAt
};

app.Run();


