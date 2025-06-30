using System.Text.Json.Serialization;

namespace GlobalMotors.OrdersService.Models.DTO
{
    public class ShippingDTO
    {
        public required decimal ShippingPrice { get; set; }

        public required Guid ShippingAddress { get; set; }

        public required Guid Order { get; set; }

        public required string Tracking { get; set; }

        public DateOnly EstimatedDelivery { get; set; }

        public DateOnly? DeliveredAt { get; set; }

        [JsonIgnore]
        public DateTime? LastUpdated { get; set; }
    }
}
