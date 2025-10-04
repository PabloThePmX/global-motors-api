using System;
using System.Collections.Generic;
using GlobalMotors.UsersService.Models;
using GlobalMotors.UsersService.Models.Enums;
using Microsoft.EntityFrameworkCore;

namespace GlobalMotors.UsersService.Context;

public class GlobalMotorsUsersContext : DbContext
{

    public GlobalMotorsUsersContext(DbContextOptions<GlobalMotorsUsersContext> options)
        : base(options)
    {
    }

    public virtual DbSet<FavoriteCar> FavoriteCars { get; set; }

    public virtual DbSet<SystemInfo> SystemInfo { get; set; }

    public virtual DbSet<UserSetting> UserSettings { get; set; }

    protected override void OnModelCreating(ModelBuilder modelBuilder)
    {
        modelBuilder.Entity<FavoriteCar>()
            .HasKey(c => new { c.User, c.Car });

        modelBuilder.Entity<UserSetting>()
            .HasKey(c => new { c.User });
    }
}
