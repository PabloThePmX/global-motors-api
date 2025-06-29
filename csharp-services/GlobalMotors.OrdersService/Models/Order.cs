using GlobalMotors.OrdersService.Models.Enums;
using System;
using System.Collections.Generic;
using System.Text.Json.Serialization;

namespace GlobalMotors.OrdersService.Models;

public partial class Order
{
    public Guid Id { get; set; }

    public required string OrderNumber { get; set; }

    public decimal FinalPrice { get; set; }

    public Guid Buyer { get; set; }

    public TransactionStatus Status { get; set; }

    public bool? Delivered { get; set; }

    public PaymentTypes PaymentType { get; set; }

    [JsonIgnore]
    public DateTime? LastUpdated { get; set; }
}
