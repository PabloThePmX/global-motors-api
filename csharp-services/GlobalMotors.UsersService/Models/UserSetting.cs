using GlobalMotors.UsersService.Models.Enums;
using System;
using System.Collections.Generic;
using System.Text.Json.Serialization;

namespace GlobalMotors.UsersService.Models;

public partial class UserSetting
{
    public Guid User { get; set; }

    public Languages Language { get; set; }

    public Currencies DisplayedCurrency { get; set; }

    [JsonIgnore]
    public DateTime? LastUpdated { get; set; }
}
