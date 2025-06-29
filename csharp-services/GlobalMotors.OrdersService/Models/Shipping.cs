using System;
using System.Collections.Generic;
using System.Text.Json.Serialization;

namespace GlobalMotors.OrdersService.Models;

public partial class Shipping
{
    public Guid Id { get; set; }

    public decimal ShippingPrice { get; set; }

    public Guid ShippingAddress { get; set; }

    public Guid Order { get; set; }

    public string Tracking { get; set; } = null!;

    public DateOnly EstimatedDelivery { get; set; }

    public DateOnly? DeliveredAt { get; set; }

    [JsonIgnore]
    public DateTime? LastUpdated { get; set; }
}
