using GlobalMotors.OrdersService.Models.DTO;

namespace GlobalMotors.OrdersService.Models;

public partial class Order : OrderDTO
{
    public Guid Id { get; set; }
}
