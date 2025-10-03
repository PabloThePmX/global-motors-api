using GlobalMotors.AuthService.Models.DTOs;
using GlobalMotors.AuthService.Models.Enums;
using System;
using System.Collections.Generic;
using System.Text.Json.Serialization;

namespace GlobalMotors.AuthService.Models;

public class User
{
    public Guid Id { get; set; }

    public required string Email { get; set; }

    public required string Password { get; set; }

    public required UserRoles Role { get; set; }

    public string? PhoneNumber { get; set; }

    public required string FirstName { get; set; }

    public required string LastName { get; set; }

    public required DateOnly Birthday { get; set; }

    public required string DocumentNumber { get; set; }

    public required DocumentTypes DocumentType { get; set; }

    public required Genders Gender { get; set; }

    public string? Picture { get; set; }

    public Guid? CurrentAddress { get; set; }

    public DateTime? LastUpdated { get; set; }
}
