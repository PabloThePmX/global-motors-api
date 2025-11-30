using NpgsqlTypes;
using System.Text.Json.Serialization;

namespace GlobalMotors.UsersService.Models.Enums
{
    [JsonConverter(typeof(JsonStringEnumConverter))]
    public enum Languages
    {
        PTBR,
        ENUS,
        ITIT,
    }

    [JsonConverter(typeof(JsonStringEnumConverter))]
    public enum Currencies
    {
        USD,
        EUR,
        BRL
    }
}
