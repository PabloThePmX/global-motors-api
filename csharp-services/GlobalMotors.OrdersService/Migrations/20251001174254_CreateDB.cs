using System;
using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

namespace GlobalMotors.OrdersService.Migrations
{
    /// <inheritdoc />
    public partial class CreateDB : Migration
    {
        /// <inheritdoc />
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.CreateTable(
                name: "CartItems",
                columns: table => new
                {
                    User = table.Column<Guid>(type: "uuid", nullable: false),
                    Car = table.Column<Guid>(type: "uuid", nullable: false),
                    LastUpdated = table.Column<DateTime>(type: "timestamp with time zone", nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_CartItems", x => new { x.User, x.Car });
                });

            migrationBuilder.CreateTable(
                name: "OrderItems",
                columns: table => new
                {
                    Order = table.Column<Guid>(type: "uuid", nullable: false),
                    Car = table.Column<Guid>(type: "uuid", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_OrderItems", x => new { x.Order, x.Car });
                });

            migrationBuilder.CreateTable(
                name: "Orders",
                columns: table => new
                {
                    Id = table.Column<Guid>(type: "uuid", nullable: false),
                    OrderNumber = table.Column<string>(type: "text", nullable: false),
                    FinalPrice = table.Column<decimal>(type: "numeric", nullable: false),
                    Buyer = table.Column<Guid>(type: "uuid", nullable: false),
                    Status = table.Column<int>(type: "integer", nullable: false),
                    Delivered = table.Column<bool>(type: "boolean", nullable: true),
                    PaymentType = table.Column<int>(type: "integer", nullable: false),
                    LastUpdated = table.Column<DateTime>(type: "timestamp with time zone", nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Orders", x => x.Id);
                });

            migrationBuilder.CreateTable(
                name: "Shippings",
                columns: table => new
                {
                    Id = table.Column<Guid>(type: "uuid", nullable: false),
                    ShippingPrice = table.Column<decimal>(type: "numeric", nullable: false),
                    ShippingAddress = table.Column<Guid>(type: "uuid", nullable: false),
                    Order = table.Column<Guid>(type: "uuid", nullable: false),
                    Tracking = table.Column<string>(type: "text", nullable: false),
                    EstimatedDelivery = table.Column<DateOnly>(type: "date", nullable: false),
                    DeliveredAt = table.Column<DateOnly>(type: "date", nullable: true),
                    LastUpdated = table.Column<DateTime>(type: "timestamp with time zone", nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Shippings", x => x.Id);
                });
        }

        /// <inheritdoc />
        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropTable(
                name: "CartItems");

            migrationBuilder.DropTable(
                name: "OrderItems");

            migrationBuilder.DropTable(
                name: "Orders");

            migrationBuilder.DropTable(
                name: "Shippings");
        }
    }
}
