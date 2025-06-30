using System.Text.Json.Serialization;
using System.Text.Json;
using GlobalMotors.OrdersService.Models.Enums;

namespace GlobalMotors.OrdersService.Helpers
{
    public class TransactionStatusJsonConverter : JsonConverter<TransactionStatus>
    {
        private static readonly Dictionary<string, TransactionStatus> _fromString = new()
        {
            { "Pagamento confirmado", TransactionStatus.PagamentoConfirmado },
            { "Problema no pagamento", TransactionStatus.ProblemaNoPagamento },
            { "Aguardando pagamento", TransactionStatus.AguardandoPagamento },
            { "Carro em preparação", TransactionStatus.CarroEmPreparacao },
            { "Carro a caminho", TransactionStatus.CarroACaminho },
            { "Entregue", TransactionStatus.Entregue },
            { "Falha na entrega", TransactionStatus.FalhaNaEntrega }
        };

        private static readonly Dictionary<TransactionStatus, string> _toString = _fromString.ToDictionary(x => x.Value, x => x.Key);

        public override TransactionStatus Read(ref Utf8JsonReader reader, Type typeToConvert, JsonSerializerOptions options)
        {
            var raw = reader.GetString();
            if (_fromString.TryGetValue(raw, out var result))
                return result;

            throw new JsonException($"Valor inválido para TransactionStatus: {raw}");
        }

        public override void Write(Utf8JsonWriter writer, TransactionStatus value, JsonSerializerOptions options)
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
