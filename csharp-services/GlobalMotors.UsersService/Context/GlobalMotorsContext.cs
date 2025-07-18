﻿using System;
using System.Collections.Generic;
using GlobalMotors.UsersService.Models;
using GlobalMotors.UsersService.Models.Enums;
using Microsoft.EntityFrameworkCore;

namespace GlobalMotors.UsersService.Context;

public partial class GlobalMotorsContext : DbContext
{
    public GlobalMotorsContext()
    {
    }

    public GlobalMotorsContext(DbContextOptions<GlobalMotorsContext> options)
        : base(options)
    {
    }

    public virtual DbSet<FavoriteCar> FavoriteCars { get; set; }

    public virtual DbSet<SystemInfo> SystemInfo { get; set; }

    public virtual DbSet<UserSetting> UserSettings { get; set; }

    protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder) { }

    protected override void OnModelCreating(ModelBuilder modelBuilder)
    {
        modelBuilder
            .HasPostgresEnum("currencies", new[] { "USD", "EUR", "BRL" })
            .HasPostgresEnum("languages", new[] { "PT-BR", "EN-US", "IT-IT" });

        modelBuilder.Entity<FavoriteCar>(entity =>
        {
            entity.HasKey(e => new { e.User, e.Car }).HasName("favorite_cars_pkey");

            entity.ToTable("favorite_cars");

            entity.Property(e => e.User).HasColumnName("user");
            entity.Property(e => e.Car).HasColumnName("car");
            entity.Property(e => e.LastUpdated)
                .HasDefaultValueSql("now()")
                .HasColumnType("timestamp without time zone")
                .HasColumnName("last_updated");
        });

        modelBuilder.Entity<SystemInfo>(entity =>
        {
            entity.HasKey(e => e.Id).HasName("system_info_pkey");

            entity.ToTable("system_info");

            entity.Property(e => e.Id)
                .HasDefaultValue(1)
                .HasColumnName("id");
            entity.Property(e => e.Version)
                .HasColumnType("character varying")
                .HasColumnName("version");
        });

        modelBuilder.Entity<UserSetting>(entity =>
        {
            entity.HasKey(e => e.User).HasName("user_settings_pkey");

            entity.ToTable("user_settings");

            entity.Property(e => e.User)
                .ValueGeneratedNever()
                .HasColumnName("user");
            entity.Property(e => e.LastUpdated)
                .HasDefaultValueSql("now()")
                .HasColumnType("timestamp without time zone")
                .HasColumnName("last_updated");
            entity.Property(e => e.Language)
                .HasColumnType("languages")
                .HasColumnName("language");
            entity.Property(e => e.DisplayedCurrency)
                .HasColumnType("currencies")
                .HasColumnName("displayed_currency");
        });

        OnModelCreatingPartial(modelBuilder);
    }

    partial void OnModelCreatingPartial(ModelBuilder modelBuilder);
}
