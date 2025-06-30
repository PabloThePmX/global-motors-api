using GlobalMotors.UsersService.Context;
using GlobalMotors.UsersService.Models;
using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Http.Json;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using Microsoft.OpenApi.Models;
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

# region User Settings and System Info

app.MapGet("/users/user-settings/{usuarioId}", async ([FromRoute] Guid usuarioId) =>
{
    var settings = await context.UserSettings.FindAsync(usuarioId);

    return settings == null ? Results.NotFound() : Results.Ok(settings);
})
.WithTags("User Settings");

app.MapPost("/users/user-settings/", async ([FromBody] UserSetting settings) =>
{
    if (context.UserSettings.Where(x => x.User == settings.User).Any())
        return Results.BadRequest("Usuáio já possui configurações.");

    var newSettings = await context.UserSettings.AddAsync(settings);

    if (newSettings == null)
        return Results.Problem();

    await context.SaveChangesAsync();

    return Results.Ok();
})
.WithTags("User Settings");

app.MapPut("/users/user-settings/{usuarioId}", async ([FromRoute] Guid usuarioId, [FromBody] UserSetting settings) =>
{
    var oldSettings = await context.UserSettings.FindAsync(usuarioId);

    if (oldSettings == null)
        return Results.NotFound();

    if (oldSettings.User != usuarioId)
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

//#region User Favorites

//app.MapGet();

//app.MapPost();

//app.MapDelete();

//#endregion

app.Run();
