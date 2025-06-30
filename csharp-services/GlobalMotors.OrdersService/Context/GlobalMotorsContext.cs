using System;
using System.Collections.Generic;
using GlobalMotors.OrdersService.Models;
using GlobalMotors.OrdersService.Models.Enums;
using Microsoft.EntityFrameworkCore;
using Npgsql;
using Npgsql.NameTranslation;

namespace GlobalMotors.OrdersService.Context;

public partial class GlobalMotorsContext : DbContext
{
    public GlobalMotorsContext()
    {
    }

    public GlobalMotorsContext(DbContextOptions<GlobalMotorsContext> options)
        : base(options)
    {
    }

    public virtual DbSet<CartItem> CartItems { get; set; }

    public virtual DbSet<Order> Orders { get; set; }

    public virtual DbSet<OrderItem> OrderItems { get; set; }

    public virtual DbSet<Shipping> Shippings { get; set; }

    protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder) { }

    protected override void OnModelCreating(ModelBuilder modelBuilder)
    {
        modelBuilder
            .HasPostgresEnum("payment_types", new[] { "Transferência Bancária", "Débito", "Pix", "Boleto" })
            .HasPostgresEnum("transaction_status", new[] { "Aguardando pagamento", "Problema no pagamento", "Pagamento confirmado", "Carro em preparação", "Carro a caminho", "Entregue", "Falha na entrega" });

        modelBuilder.Entity<CartItem>(entity =>
        {
            entity.HasKey(e => new { e.User, e.Car }).HasName("cart_items_pkey");

            entity.ToTable("cart_items");

            entity.Property(e => e.User).HasColumnName("user");
            entity.Property(e => e.Car).HasColumnName("car");
            entity.Property(e => e.LastUpdated)
                .HasDefaultValueSql("now()")
                .HasColumnType("timestamp without time zone")
                .HasColumnName("last_updated");
        });

        modelBuilder.Entity<Order>(entity =>
        {
            entity.HasKey(e => e.Id).HasName("orders_pkey");

            entity.ToTable("orders");

            entity.HasIndex(e => e.OrderNumber, "orders_order_number_key").IsUnique();

            entity.Property(e => e.Id)
                .HasDefaultValueSql("gen_random_uuid()")
                .HasColumnName("id");
            entity.Property(e => e.Buyer).HasColumnName("buyer");
            entity.Property(e => e.Delivered).HasColumnName("delivered");
            entity.Property(e => e.FinalPrice)
                .HasPrecision(12, 2)
                .HasColumnName("final_price");
            entity.Property(e => e.PaymentType)
                .HasColumnName("payment_type");
            entity.Property(e => e.Status)
                .HasColumnType("transaction_status")
                .HasColumnName("status");
            entity.Property(e => e.LastUpdated)
                .HasDefaultValueSql("now()")
                .HasColumnType("timestamp without time zone")
                .HasColumnName("last_updated");
            entity.Property(e => e.OrderNumber)
                .HasMaxLength(20)
                .HasColumnName("order_number");
        });

        modelBuilder.Entity<OrderItem>(entity =>
        {
            entity.HasKey(e => new { e.Order, e.Car }).HasName("order_items_pkey");

            entity.ToTable("order_items");

            entity.Property(e => e.Order).HasColumnName("order");
            entity.Property(e => e.Car).HasColumnName("car");
        });

        modelBuilder.Entity<Shipping>(entity =>
        {
            entity.HasKey(e => e.Id).HasName("shippings_pkey");

            entity.ToTable("shippings");

            entity.Property(e => e.Id)
                .HasDefaultValueSql("gen_random_uuid()")
                .HasColumnName("id");
            entity.Property(e => e.DeliveredAt).HasColumnName("delivered_at");
            entity.Property(e => e.EstimatedDelivery).HasColumnName("estimated_delivery");
            entity.Property(e => e.LastUpdated)
                .HasDefaultValueSql("now()")
                .HasColumnType("timestamp without time zone")
                .HasColumnName("last_updated");
            entity.Property(e => e.Order).HasColumnName("order");
            entity.Property(e => e.ShippingAddress)
                .HasColumnName("shipping_address");
            entity.Property(e => e.ShippingPrice)
                .HasPrecision(10, 2)
                .HasColumnName("shipping_price");
            entity.Property(e => e.Tracking)
                .HasMaxLength(40)
                .HasColumnName("tracking");
        });

        OnModelCreatingPartial(modelBuilder);
    }

    partial void OnModelCreatingPartial(ModelBuilder modelBuilder);
}
