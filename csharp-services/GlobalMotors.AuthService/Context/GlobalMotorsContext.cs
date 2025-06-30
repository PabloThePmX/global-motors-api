using System;
using System.Collections.Generic;
using GlobalMotors.AuthService.Models;
using GlobalMotors.AuthService.Models.Enums;
using Microsoft.EntityFrameworkCore;

namespace GlobalMotors.AuthService.Context;

public partial class GlobalMotorsContext : DbContext
{
    public GlobalMotorsContext()
    {
    }

    public GlobalMotorsContext(DbContextOptions<GlobalMotorsContext> options)
        : base(options)
    {
    }

    public virtual DbSet<Address> Addresses { get; set; }

    public virtual DbSet<User> Users { get; set; }

    protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder) { }

    protected override void OnModelCreating(ModelBuilder modelBuilder)
    {
        modelBuilder
            .HasPostgresEnum("br_states", new[] { "AC", "AL", "AM", "AP", "BA", "CE", "DF", "ES", "GO", "MA", "MG", "MS", "MT", "PA", "PB", "PE", "PI", "PR", "RJ", "RN", "RO", "RR", "RS", "SC", "SE", "SP", "TO" })
            .HasPostgresEnum("document_types", new[] { "Física", "Jurídica" })
            .HasPostgresEnum("genders", new[] { "Masculino", "Feminino", "Não Informar" })
            .HasPostgresEnum("user_roles", new[] { "Admin", "Normal", "Owner" });

        modelBuilder.Entity<Address>(entity =>
        {
            entity.HasKey(e => e.Id).HasName("addresses_pkey");

            entity.ToTable("addresses");

            entity.Property(e => e.Id)
                .HasDefaultValueSql("gen_random_uuid()")
                .HasColumnName("id");
            entity.Property(e => e.City)
                .HasMaxLength(100)
                .HasColumnName("city");
            entity.Property(e => e.Complement)
                .HasMaxLength(150)
                .HasColumnName("complement");
            entity.Property(e => e.Neighborhood)
                .HasMaxLength(100)
                .HasColumnName("neighborhood");
            entity.Property(e => e.Number).HasColumnName("number");
            entity.Property(e => e.PostalCode)
                .HasMaxLength(9)
                .HasColumnName("postal_code");
            entity.Property(e => e.ReferencePoint)
                .HasMaxLength(100)
                .HasColumnName("reference_point");
            entity.Property(e => e.Street)
                .HasMaxLength(150)
                .HasColumnName("street");
            entity.Property(e => e.States)
                .HasColumnType("br_states")
                .HasColumnName("states");

        });

        modelBuilder.Entity<User>(entity =>
        {
            entity.HasKey(e => e.Id).HasName("users_pkey");

            entity.ToTable("users");

            entity.HasIndex(e => e.DocumentNumber, "users_document_number_key").IsUnique();

            entity.Property(e => e.Id)
                .HasDefaultValueSql("gen_random_uuid()")
                .HasColumnName("id");
            entity.Property(e => e.Birthday).HasColumnName("birthday");
            entity.Property(e => e.CurrentAddress).HasColumnName("current_address");
            entity.Property(e => e.DocumentNumber)
                .HasMaxLength(14)
                .HasColumnName("document_number");
            entity.Property(e => e.Email)
                .HasMaxLength(150)
                .HasColumnName("email");
            entity.Property(e => e.FirstName)
                .HasMaxLength(50)
                .HasColumnName("first_name");
            entity.Property(e => e.LastName)
                .HasMaxLength(50)
                .HasColumnName("last_name");
            entity.Property(e => e.LastUpdated)
                .HasDefaultValueSql("now()")
                .HasColumnType("timestamp without time zone")
                .HasColumnName("last_updated");
            entity.Property(e => e.Password)
                .HasMaxLength(100)
                .HasColumnName("password");
            entity.Property(e => e.PhoneNumber)
                .HasMaxLength(11)
                .HasColumnName("phone_number");
            entity.Property(e => e.Picture)
                .HasMaxLength(255)
                .HasColumnName("picture");
            entity.Property(e => e.DocumentType)
                .HasColumnType("document_types")
                .HasColumnName("document_type");
            entity.Property(e => e.Gender)
                .HasColumnType("genders")
                .HasColumnName("gender");
            entity.Property(e => e.Role)
                .HasColumnType("user_roles")
                .HasColumnName("role");

        });

        OnModelCreatingPartial(modelBuilder);
    }

    partial void OnModelCreatingPartial(ModelBuilder modelBuilder);
}
