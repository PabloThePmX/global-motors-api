using GlobalMotors.OrdersService.Models.Enums;
using System.Text.Json.Serialization;

namespace GlobalMotors.OrdersService.Models;

public class Order
{
    public Guid Id { get; set; }

    public required string OrderNumber { get; set; }

    public required decimal FinalPrice { get; set; }

    public required Guid Buyer { get; set; }

    public required TransactionStatus Status { get; set; }

    public bool? Delivered { get; set; }

    public required PaymentTypes PaymentType { get; set; }

    public DateTime? LastUpdated { get; set; }
}
