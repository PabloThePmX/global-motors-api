using GlobalMotors.OrdersService.Models.Enums;

namespace GlobalMotors.OrdersService.Models.DTOs
{
    public class OrderRequestDTO
    {
        public required string OrderNumber { get; set; }

        public required decimal FinalPrice { get; set; }

        public required Guid Buyer { get; set; }

        public required TransactionStatus Status { get; set; }

        public bool? Delivered { get; set; }

        public required PaymentTypes PaymentType { get; set; }
    }
}
