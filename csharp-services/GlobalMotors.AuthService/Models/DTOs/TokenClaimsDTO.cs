namespace GlobalMotors.AuthService.Models.DTOs
{
    //PascalCase pois sao parametros publicos
    public record TokenClaimsDTO(string Id, string Type, string Email);
}
