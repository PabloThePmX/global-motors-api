using System;
using System.Collections.Generic;
using System.Text.Json.Serialization;

namespace GlobalMotors.OrdersService.Models;

public class Shipping
{
    public Guid Id { get; set; }

    public required decimal ShippingPrice { get; set; }

    public required Guid ShippingAddress { get; set; }

    public required Guid Order { get; set; }

    public required string Tracking { get; set; }

    public DateOnly EstimatedDelivery { get; set; }

    public DateOnly? DeliveredAt { get; set; }

    public DateTime? LastUpdated { get; set; }
}
