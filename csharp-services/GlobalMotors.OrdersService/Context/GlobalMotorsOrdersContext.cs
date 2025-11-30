using System;
using System.Collections.Generic;
using GlobalMotors.OrdersService.Models;
using GlobalMotors.OrdersService.Models.Enums;
using Microsoft.EntityFrameworkCore;
using Npgsql;
using Npgsql.NameTranslation;

namespace GlobalMotors.OrdersService.Context;

public class GlobalMotorsOrdersContext : DbContext
{
   public GlobalMotorsOrdersContext(DbContextOptions<GlobalMotorsOrdersContext> options)
        : base(options)
   {
   }

    public virtual DbSet<CartItem> CartItems { get; set; }

    public virtual DbSet<Order> Orders { get; set; }

    public virtual DbSet<OrderItem> OrderItems { get; set; }

    public virtual DbSet<Shipping> Shippings { get; set; }

    protected override void OnModelCreating(ModelBuilder modelBuilder)
    {
        modelBuilder.Entity<CartItem>()
            .HasKey(c => new { c.User, c.Car });

        modelBuilder.Entity<OrderItem>()
            .HasKey(c => new { c.Order, c.Car });
    }
}
