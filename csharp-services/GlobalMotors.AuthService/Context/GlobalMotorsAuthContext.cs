using System;
using System.Collections.Generic;
using GlobalMotors.AuthService.Models;
using GlobalMotors.AuthService.Models.Enums;
using Microsoft.EntityFrameworkCore;

namespace GlobalMotors.AuthService.Context;

public class GlobalMotorsAuthContext : DbContext
{
    public GlobalMotorsAuthContext(DbContextOptions<GlobalMotorsAuthContext> options)
        : base(options)
    {
    }

    public virtual DbSet<User> Users { get; set; }

    protected override void OnModelCreating(ModelBuilder modelBuilder)
    {
        
    }
}
