using GlobalMotors.OrdersService.Context;
using GlobalMotors.OrdersService.Models;
using Microsoft.EntityFrameworkCore;
using Microsoft.OpenApi.Models;
using System.Text.Json.Serialization;
using Microsoft.AspNetCore.Mvc;
using JsonOptions = Microsoft.AspNetCore.Http.Json.JsonOptions;
using static Microsoft.EntityFrameworkCore.DbLoggerCategory.Database;

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

app.MapGet("/orders/{usuario_id}", async (Guid usuario_id) =>
{
    var order = await context.Orders.Where(x => x.Buyer == usuario_id).FirstOrDefaultAsync();

    return (order == null) ? Results.NotFound() : Results.Ok(order);

})
.WithTags("Orders");

app.MapPost("/orders", async ([FromBody] Order order) =>
{
    var newOrder = await context.Orders.AddAsync(order);

    if (newOrder == null)
        return Results.Problem();

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
#endregion

#region Cart
#endregion

app.Run();


