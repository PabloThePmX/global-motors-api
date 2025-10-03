using System;
using System.Collections.Generic;

namespace GlobalMotors.OrdersService.Models;

public class OrderItem
{
    public Guid Order { get; set; }

    public Guid Car { get; set; }
}
