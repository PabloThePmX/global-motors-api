using NpgsqlTypes;
using System.Runtime.Serialization;
using System.Text.Json.Serialization;

namespace GlobalMotors.OrdersService.Models.Enums
{
    [JsonConverter(typeof(JsonStringEnumConverter))]
    public enum TransactionStatus
    {
        [PgName("Aguardando pagamento")]
        AguardandoPagamento,

        [PgName("Problema no pagamento")]
        ProblemaNoPagamento,

        [PgName("Pagamento confirmado")]
        PagamentoConfirmado,

        [PgName("Carro em preparação")]
        CarroEmPreparacao,

        [PgName("Carro a caminho")]
        CarroACaminho,

        [PgName("Entregue")]
        Entregue,

        [PgName("Falha na entrega")]
        FalhaNaEntrega
    }

    [JsonConverter(typeof(JsonStringEnumConverter))]
    public enum PaymentTypes
    {
        [PgName("Transferência Bancária")]
        TransferenciaBancaria,

        [PgName("Débito")]
        Debito,

        [PgName("Pix")]
        Pix,

        [PgName("Boleto")]
        Boleto
    }
}
