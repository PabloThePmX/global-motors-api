namespace GlobalMotors.OrdersService.Models.DTOs
{
    public class ShippingRequestDTO
    {
        public required decimal ShippingPrice { get; set; }

        public required Guid ShippingAddress { get; set; }

        public required Guid Order { get; set; }

        public required string Tracking { get; set; }

        public DateOnly EstimatedDelivery { get; set; }

        public DateOnly? DeliveredAt { get; set; }
    }
}
