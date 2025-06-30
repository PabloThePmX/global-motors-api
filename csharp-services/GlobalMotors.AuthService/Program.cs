using GlobalMotors.AuthService.Context;
using GlobalMotors.AuthService.Models;
using GlobalMotors.AuthService.Models.DTOs;
using GlobalMotors.AuthService.Models.Enums;
using Microsoft.AspNetCore.Http.Json;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.Configuration;
using Microsoft.IdentityModel.Tokens;
using Microsoft.IdentityModel.Tokens.Experimental;
using Microsoft.OpenApi.Models;
using Steeltoe.Discovery.Client;
using System.IdentityModel.Tokens.Jwt;
using System.Security.Claims;
using System.Text;
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
        Title = "Auth Service",
        Description = "Documenta��o da API do Microsservi�o Auth, contendo rotas para Register e Login, bem como alter��es nas informa��es do usu�rio.",
    });

    //opt.EnableAnnotations();
    opt.UseInlineDefinitionsForEnums();

});

builder.Services.AddDbContextFactory<GlobalMotorsContext>(opt =>
    opt.UseNpgsql(builder.Configuration.GetConnectionString("DefaultConnection"), en =>
    {
        en.MapEnum<BrStates>(enumName: "br_states");
        en.MapEnum<DocumentTypes>(enumName: "document_types");
        en.MapEnum<Genders>(enumName: "genders");
        en.MapEnum<UserRoles>(enumName: "user_roles");
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

#region Auth

//https://github.com/patrickgod/JwtWebApiDotNet7/blob/master/JwtWebApiDotNet7/Controllers/AuthController.cs
app.MapPost("auth/signup", async ([FromBody] UserDTO newUser) =>
{
    string passwordHash = BCrypt.Net.BCrypt.HashPassword(newUser.Password);

    newUser.Password = passwordHash;

    var newUserDb = await context.Users.AddAsync(MapDtoToUser(newUser));

    if (newUserDb == null)
        return Results.Problem();

    await context.SaveChangesAsync();

    return Results.Ok(newUserDb.Entity.Id);
})
.WithTags("Auth");

app.MapPost("auth/signin", async ([FromBody] LoginUser loginUser) =>
{
    var user = await context.Users.Where(x => x.Email == loginUser.Email).FirstOrDefaultAsync();

    if (user == null)
        return Results.BadRequest("User not found.");

    if (!BCrypt.Net.BCrypt.Verify(loginUser.Password, user.Password))
        return Results.BadRequest("Wrong password.");

    var jwt = CreateToken(loginUser, user.Role);

    return Results.Ok(jwt);
})
.WithTags("Auth");

app.MapPost("auth/validate-jwt", ([FromBody] string jwt) =>
{
    var claimsPrincipal = ValidateToken(jwt);

    if (claimsPrincipal == null)
        return Results.Unauthorized();

    return Results.Ok(claimsPrincipal.Claims.Where(c => 
        c.Type.Contains("name") || c.Type.Contains("role")).Select(c => new { c.Type, c.Value} ));
})
.WithTags("Auth");

#endregion

#region Users

app.MapGet("auth/users/{userId}", async ([FromRoute] Guid userId) =>
{
    var user = await context.Users.FindAsync(userId);

    return user == null ? Results.NotFound() : Results.Ok(user);
})
.WithTags("Users");

app.MapPut("auth/users/{userId}", async ([FromRoute] Guid userId, [FromBody] User newUser) =>
{
    var oldUser = await context.Users.FindAsync(userId);

    if (oldUser == null)
        return Results.NotFound();

    if (oldUser.Id != userId)
        return Results.BadRequest();

    string passwordHash = BCrypt.Net.BCrypt.HashPassword(newUser.Password);

    newUser.Password = passwordHash;

    context.Entry(oldUser).CurrentValues.SetValues(newUser);

    await context.SaveChangesAsync();

    return Results.Ok();
})
.WithTags("Users");

#endregion

string CreateToken(LoginUser user, UserRoles role)
{
    List<Claim> claims = new List<Claim> {
        new Claim(ClaimTypes.Name, user.Email),
        new Claim(ClaimTypes.Role, role.ToString())
    };

    var key = new SymmetricSecurityKey(Encoding.UTF8.GetBytes("_chave_SecretaGlobalMotors_chave_"));

    var creds = new SigningCredentials(key, SecurityAlgorithms.HmacSha256);

    var token = new JwtSecurityToken(
        claims: claims,
        expires: DateTime.UtcNow.AddDays(1),
        signingCredentials: creds
    );

    var jwt = new JwtSecurityTokenHandler().WriteToken(token);

    return jwt;
}

ClaimsPrincipal? ValidateToken(string jwt)
{
    var key = new SymmetricSecurityKey(Encoding.UTF8.GetBytes("_chave_SecretaGlobalMotors_chave_"));

    var tokenHandler = new JwtSecurityTokenHandler();

    var validationParameters = new TokenValidationParameters
    {
        ValidateLifetime = true,
        ValidateIssuerSigningKey = true,
        IssuerSigningKey = key,
        ValidateIssuer = false,
        ValidateAudience = false    
    };

    try
    {
        SecurityToken validatedToken;
        return tokenHandler.ValidateToken(jwt, validationParameters, out validatedToken);
    } 
    catch (Exception e)
    {
        return null;
    }

}

User MapDtoToUser(UserDTO user) => new User()
{
    Email = user.Email,
    Password = user.Password,
    Birthday = user.Birthday,
    DocumentNumber = user.DocumentNumber,
    DocumentType = user.DocumentType,
    FirstName = user.FirstName, 
    LastName = user.LastName,
    Gender = user.Gender,
    Role = user.Role,
    CurrentAddress = user.CurrentAddress,
    Picture = user.Picture,
    PhoneNumber = user.PhoneNumber
};

app.Run();
