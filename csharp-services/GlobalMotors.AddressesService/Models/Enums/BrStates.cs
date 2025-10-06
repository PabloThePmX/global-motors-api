using NpgsqlTypes;
using System.Text.Json.Serialization;

namespace GlobalMotors.AddressesService.Models.Enums
{
    [JsonConverter(typeof(JsonStringEnumConverter))]
    public enum BrStates
    {
        AC,
        AL,
        AM,
        AP,
        BA,
        CE,
        DF,
        ES,
        GO,
        MA,
        MG,
        MS,
        MT,
        PA,
        PB,
        PE,
        PI,
        PR,
        RJ,
        RN,
        RO,
        RR,
        RS,
        SC,
        SE,
        SP,
        TO
    }
}
