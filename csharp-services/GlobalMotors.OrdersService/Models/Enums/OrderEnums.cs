using NpgsqlTypes;
using System.Runtime.Serialization;
using System.Text.Json.Serialization;

namespace GlobalMotors.OrdersService.Models.Enums
{
    [JsonConverter(typeof(JsonStringEnumConverter))]
    public enum TransactionStatus
    {
        AguardandoPagamento,
        ProblemaNoPagamento,
        PagamentoConfirmado,
        CarroEmPreparacao,
        CarroACaminho,
        Entregue,
        FalhaNaEntrega
    }

    [JsonConverter(typeof(JsonStringEnumConverter))]
    public enum PaymentTypes
    {
        TransferenciaBancaria,
        Debito,
        Pix,
        Boleto
    }
}
