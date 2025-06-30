using System;
using System.Collections.Generic;
using System.Text.Json.Serialization;

namespace GlobalMotors.OrdersService.Models;

public partial class CartItem
{
    public Guid User { get; set; }

    public Guid Car { get; set; }

    [JsonIgnore]
    public DateTime? LastUpdated { get; set; }
}
