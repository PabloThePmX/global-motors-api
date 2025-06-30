using GlobalMotors.AuthService.Models.DTOs;
using GlobalMotors.AuthService.Models.Enums;
using System;
using System.Collections.Generic;
using System.Text.Json.Serialization;

namespace GlobalMotors.AuthService.Models;

public partial class User : UserDTO
{
    public Guid Id { get; set; }
}
