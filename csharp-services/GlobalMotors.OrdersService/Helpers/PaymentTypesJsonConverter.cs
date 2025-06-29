using GlobalMotors.OrdersService.Models.Enums;
using System.Text.Json;
using System.Text.Json.Serialization;

namespace GlobalMotors.OrdersService.Helpers
{
    public class PaymentTypesJsonConverter : JsonConverter<PaymentTypes>
    {
        private static readonly Dictionary<string, PaymentTypes> _fromString = new()
        {
            { "Boleto", PaymentTypes.Boleto },
            { "Pix", PaymentTypes.Pix },
            { "Débito", PaymentTypes.Debito },
            { "Transferência Bancária", PaymentTypes.TransferenciaBancaria }
        };

        private static readonly Dictionary<PaymentTypes, string> _toString = _fromString.ToDictionary(x => x.Value, x => x.Key);

        public override PaymentTypes Read(ref Utf8JsonReader reader, Type typeToConvert, JsonSerializerOptions options)
        {
            var raw = reader.GetString();
            if (_fromString.TryGetValue(raw, out var result))
                return result;

            throw new JsonException($"Valor inválido para PaymentTypes: {raw}");
        }

        public override void Write(Utf8JsonWriter writer, PaymentTypes value, JsonSerializerOptions options)
        {
            if (_toString.TryGetValue(value, out var str))
            {
                writer.WriteStringValue(str);
                return;
            }

            throw new JsonException($"Enum inválido: {value}");
        }
    }
}
