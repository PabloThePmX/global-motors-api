using GlobalMotors.UsersService.Context;
using GlobalMotors.UsersService.Models;
using GlobalMotors.UsersService.Models.Enums;
using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Http.Json;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using Microsoft.OpenApi.Models;
using Steeltoe.Discovery.Client;
using System.Text.Json.Serialization;
using JsonOptions = Microsoft.AspNetCore.Http.Json.JsonOptions;

var builder = WebApplication.CreateBuilder(args);

// Add services to the container.
// Learn more about configuring Swagger/OpenAPI at https://aka.ms/aspnetcore/swashbuckle
builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen(opt =>
{
    opt.SwaggerDoc("v1", new OpenApiInfo
    {
        Version = "v1",
        Title = "Users Service",
        Description = "Documentação da API do Microsserviço Users, contendo rotas para Configuração do Usuário (User Settings) e Favoritos (User Favorites).",
    });

    //opt.EnableAnnotations();
    opt.UseInlineDefinitionsForEnums();

});

builder.Services.AddDbContextFactory<GlobalMotorsContext>(opt =>
    opt.UseNpgsql(builder.Configuration.GetConnectionString("DefaultConnection"), en =>
    {
        en.MapEnum<Currencies>(enumName: "currencies");
        en.MapEnum<Languages>(enumName: "languages");
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

# region User Settings and System Info

app.MapGet("/users/user-settings/{userId}", async ([FromRoute] Guid userId) =>
{
    var settings = await context.UserSettings.FindAsync(userId);

    return settings == null ? Results.NotFound() : Results.Ok(settings);
})
.WithTags("User Settings");

app.MapPost("/users/user-settings/", async ([FromBody] UserSetting settings) =>
{
    if (context.UserSettings.Where(x => x.User == settings.User).Any())
        return Results.BadRequest("Usu�io j� possui configura��es.");

    var newSettings = await context.UserSettings.AddAsync(settings);

    if (newSettings == null)
        return Results.Problem();

    await context.SaveChangesAsync();

    return Results.Ok();
})
.WithTags("User Settings");

app.MapPut("/users/user-settings/{userId}", async ([FromRoute] Guid userId, [FromBody] UserSetting settings) =>
{
    var oldSettings = await context.UserSettings.FindAsync(userId);

    if (oldSettings == null)
        return Results.NotFound();

    if (oldSettings.User != userId)
        return Results.BadRequest();

    context.Entry(oldSettings).CurrentValues.SetValues(settings);

    await context.SaveChangesAsync();

    return Results.Ok();
})
.WithTags("User Settings");

app.MapGet("/users/system-info", async () =>
{
    var systemInfo = await context.SystemInfo.FindAsync(1);

    return systemInfo == null ? Results.NotFound() : Results.Ok(systemInfo.Version);
})
.WithTags("User Settings");

#endregion

#region User Favorites

app.MapGet("/users/user-favorites/{userId}", async ([FromRoute] Guid userId) =>
{
    var userFavorites = await context.FavoriteCars.Where(x => x.User == userId).Select(x => x.Car).ToListAsync();

    return userFavorites == null ? Results.NotFound() : Results.Ok(userFavorites);
})
.WithTags("User Favorites");

app.MapPost("/users/user-favorites", async ([FromBody] FavoriteCar favoriteCar) =>
{
    var newFavorite = await context.FavoriteCars.AddAsync(favoriteCar);

    if (newFavorite == null)
        return Results.Problem();

    await context.SaveChangesAsync();

    return Results.Ok();
})
.WithTags("User Favorites");

app.MapDelete("/users/user-favorites/{userId}/{carroId}", async ([FromRoute] Guid userId, [FromRoute] Guid carroId) =>
{
    var favoriteCar = await context.FavoriteCars.FindAsync(userId, carroId);

    if (favoriteCar == null)
        return Results.NotFound();

    if (favoriteCar.User != userId || favoriteCar.Car != carroId)
        return Results.BadRequest();

    context.FavoriteCars.Remove(favoriteCar);

    await context.SaveChangesAsync();

    return Results.Ok();
})
.WithTags("User Favorites");

#endregion

app.Run();
