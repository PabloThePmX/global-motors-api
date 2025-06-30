using GlobalMotors.OrdersService.Models.DTO;
using System;
using System.Collections.Generic;
using System.Text.Json.Serialization;

namespace GlobalMotors.OrdersService.Models;

public partial class Shipping : ShippingDTO
{
    public Guid Id { get; set; }
}
