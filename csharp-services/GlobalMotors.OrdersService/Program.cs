using GlobalMotors.OrdersService.Context;
using GlobalMotors.OrdersService.Models;
using Microsoft.EntityFrameworkCore;
using Microsoft.OpenApi.Models;
using System.Text.Json.Serialization;
using Microsoft.AspNetCore.Mvc;
using JsonOptions = Microsoft.AspNetCore.Http.Json.JsonOptions;
using GlobalMotors.OrdersService.Models.DTO;
using Swashbuckle.AspNetCore.SwaggerGen;

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
    opt.UseNpgsql(builder.Configuration.GetConnectionString("Server=localhost;Port=5432;Database=global_motors;User Id=postgres;Password=postgres;")));

builder.Services.Configure<JsonOptions>(opt =>
{
    opt.SerializerOptions.Converters.Add(new JsonStringEnumConverter());
});

var app = builder.Build();

var scope = app.Services.CreateScope();
var context = scope.ServiceProvider.GetRequiredService<GlobalMotorsContext>();

// Configure the HTTP request pipeline.
if (app.Environment.IsDevelopment())
{
    app.UseSwagger();
    app.UseSwaggerUI();
}

app.UseHttpsRedirection();

#region Orders

app.MapGet("/orders/{id_usuario}", async (Guid usuarioId) =>
{
    var order = await context.Orders.Where(x => x.Buyer == usuarioId).FirstOrDefaultAsync();

    return (order == null) ? Results.NotFound() : Results.Ok(order);

})
.WithTags("Orders");

app.MapPost("/orders", async ([FromBody] OrderDTO order) =>
{
    var newOrder = await context.Orders.AddAsync(MapDtoToOrder(order));

    if (newOrder == null)
        return Results.Problem();

    //TODO: CHAMA O ADDRESS PEGANDO O ID DO CURRENT ADDRESS DO USUARIO DA ORDEM 

    await context.SaveChangesAsync();

    return Results.Ok(newOrder.Entity.Id);
})
.WithTags("Orders");

app.MapPut("/orders/{id}", async (Guid id, [FromBody] Order order) =>
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

//TODO: FALTA TESTAR
app.MapGet("/orders/{id}/items", async (Guid id) =>
{
    var orderItems = await context.OrderItems.FindAsync(id);

    return orderItems == null ? Results.NotFound() : Results.Ok(orderItems);
})
.WithTags("Orders");

#endregion

# region Shipping

app.MapGet("orders/shippings/{id}", async (Guid id) =>
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

app.MapPut("/orders/shippings/{id}", async (Guid id, [FromBody] Shipping shipping) =>
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

app.MapGet("/orders/cart/{id_usuario}", async (Guid usuarioId) =>
{
    var cartItems = await context.CartItems.Where(x => x.User == usuarioId).Select(x => x.Car).ToListAsync();

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

app.MapDelete("/orders/cart/{id_usuario}/{id_carro}", async (Guid idUsuario, Guid idCarro) =>
{
    var cartItem = await context.CartItems.FindAsync(idUsuario, idCarro);

    if (cartItem == null)
        return Results.NotFound();

    if (cartItem.User != idUsuario || cartItem.Car != idCarro)
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


