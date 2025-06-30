using GlobalMotors.OrdersService.Models.Enums;
using System.Text.Json.Serialization;

namespace GlobalMotors.OrdersService.Models.DTO
{
    public class OrderDTO
    {
        public required string OrderNumber { get; set; }

        public required decimal FinalPrice { get; set; }

        public required Guid Buyer { get; set; }

        public required TransactionStatus Status { get; set; }

        public bool? Delivered { get; set; }

        public required PaymentTypes PaymentType { get; set; }

        [JsonIgnore]
        public DateTime? LastUpdated { get; set; }
    }
}
