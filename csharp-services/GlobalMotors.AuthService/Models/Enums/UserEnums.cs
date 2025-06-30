using NpgsqlTypes;
using System.Text.Json.Serialization;

namespace GlobalMotors.AuthService.Models.Enums
{
    [JsonConverter(typeof(JsonStringEnumConverter))]
    public enum DocumentTypes
    {
        [PgName("Física")]
        Fisica,

        [PgName("Jurídica")]
        Juridica
    }

    [JsonConverter(typeof(JsonStringEnumConverter))]
    public enum Genders
    {
        [PgName("Masculino")]
        Masculino,

        [PgName("Feminino")]
        Feminino,

        [PgName("Não Informar")]
        NaoInformar
    }

    [JsonConverter(typeof(JsonStringEnumConverter))]
    public enum UserRoles
    {
        [PgName("Admin")]
        Admin,

        [PgName("Normal")]
        Normal,

        [PgName("Owner")]
        Owner
    }
}
