using NpgsqlTypes;
using System.Text.Json.Serialization;

namespace GlobalMotors.UsersService.Models.Enums
{
    [JsonConverter(typeof(JsonStringEnumConverter))]
    public enum Languages
    {
        [PgName("PT-BR")]
        PT,

        [PgName("EN-US")]
        EN,

        [PgName("IT-IT")]
        IT,
    }

    [JsonConverter(typeof(JsonStringEnumConverter))]
    public enum Currencies
    {
        [PgName("USD")]
        USD,
        [PgName("EUR")]
        EUR,
        [PgName("BRL")]
        BRL
    }
}
