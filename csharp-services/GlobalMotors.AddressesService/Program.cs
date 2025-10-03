using GlobalMotors.AddressesService.Context;
using Microsoft.EntityFrameworkCore;
using Microsoft.OpenApi.Models;
using System.Text.Json.Serialization;
using JsonOptions = Microsoft.AspNetCore.Http.Json.JsonOptions;
using Steeltoe.Discovery.Client;
using GlobalMotors.AddressesService.Models.DTOs;
using Microsoft.AspNetCore.Mvc;
using GlobalMotors.AddressesService.Models;

var builder = WebApplication.CreateBuilder(args);

// Add services to the container.
// Learn more about configuring Swagger/OpenAPI at https://aka.ms/aspnetcore/swashbuckle
builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen(opt =>
{
    opt.SwaggerDoc("v1", new OpenApiInfo
    {
        Version = "v1",
        Title = "Addresses Service",
        Description = "Documentação da API do Microsserviço Addresses, contendo rotas para Buscar, Registrar ou Atualizar um endereço",
    });

    opt.EnableAnnotations();
    opt.UseInlineDefinitionsForEnums();

});

builder.Services.AddDbContextFactory<GlobalMotorsAddressesContext>(opt =>
    opt.UseNpgsql(builder.Configuration.GetConnectionString("DefaultConnection")));

builder.Services.AddDiscoveryClient(builder.Configuration);
builder.Services.AddHealthChecks();

builder.Services.Configure<JsonOptions>(opt =>
{
    opt.SerializerOptions.Converters.Add(new JsonStringEnumConverter());
});

var app = builder.Build();

var scope = app.Services.CreateScope();
var context = scope.ServiceProvider.GetRequiredService<GlobalMotorsAddressesContext>();

// Configure the HTTP request pipeline.
app.UseSwagger();
app.UseSwaggerUI();
app.UseHealthChecks("/health");

app.UseHttpsRedirection();

app.MapGet("addresses/{id}", async (Guid id) =>
{
    var address = await context.Addresses.FindAsync(id);

    return (address == null) ? Results.NotFound() : Results.Ok(address);
});

app.MapPost("addresses", async ([FromBody] AddressRequestDTO address) =>
{
    var newAddress = await context.Addresses.AddAsync(MapDtoToAddress(address));

    await context.SaveChangesAsync();

    return (address == null) ? Results.Problem() : Results.Ok(newAddress.Entity.Id);
});

app.MapPut("addresses/{id}", async ([FromRoute] Guid id, [FromBody] AddressRequestDTO address) =>
{
    var currentAddress = await context.Addresses.FindAsync(id);

    if (currentAddress == null)
        return Results.NotFound();

    context.Entry(currentAddress).CurrentValues.SetValues(address);

    await context.SaveChangesAsync();

    return Results.Ok();
});

app.Run();

Address MapDtoToAddress(AddressRequestDTO addressDto) => new Address
{
    Street = addressDto.Street,
    City = addressDto.City,
    PostalCode = addressDto.PostalCode,
    Neighborhood = addressDto.Neighborhood,
    Number = addressDto.Number,
    Complement = addressDto.Complement,
    States = addressDto.States,
    ReferencePoint = addressDto.ReferencePoint
};