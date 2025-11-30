using System.Text.Json.Serialization;

namespace GlobalMotors.AuthService.Models.Enums
{
    [JsonConverter(typeof(JsonStringEnumConverter))]
    public enum DocumentTypes
    {
        Fisica,
        Juridica
    }

    [JsonConverter(typeof(JsonStringEnumConverter))]
    public enum Genders
    {
        Masculino,
        Feminino,
        NaoInformar
    }

    [JsonConverter(typeof(JsonStringEnumConverter))]
    public enum UserRoles
    {
        Admin,
        Normal,
        Owner
    }
}
