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
                name: "cart_items",
                columns: table => new
                {
                    user = table.Column<Guid>(type: "uuid", nullable: false),
                    car = table.Column<Guid>(type: "uuid", nullable: false),
                    last_updated = table.Column<DateTime>(type: "timestamp with time zone", nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("pk_cart_items", x => new { x.user, x.car });
                });

            migrationBuilder.CreateTable(
                name: "order_items",
                columns: table => new
                {
                    order = table.Column<Guid>(type: "uuid", nullable: false),
                    car = table.Column<Guid>(type: "uuid", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("pk_order_items", x => new { x.order, x.car });
                });

            migrationBuilder.CreateTable(
                name: "orders",
                columns: table => new
                {
                    id = table.Column<Guid>(type: "uuid", nullable: false),
                    order_number = table.Column<string>(type: "text", nullable: false),
                    final_price = table.Column<decimal>(type: "numeric", nullable: false),
                    buyer = table.Column<Guid>(type: "uuid", nullable: false),
                    status = table.Column<int>(type: "integer", nullable: false),
                    delivered = table.Column<bool>(type: "boolean", nullable: true),
                    payment_type = table.Column<int>(type: "integer", nullable: false),
                    last_updated = table.Column<DateTime>(type: "timestamp with time zone", nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("pk_orders", x => x.id);
                });

            migrationBuilder.CreateTable(
                name: "shippings",
                columns: table => new
                {
                    id = table.Column<Guid>(type: "uuid", nullable: false),
                    shipping_price = table.Column<decimal>(type: "numeric", nullable: false),
                    shipping_address = table.Column<Guid>(type: "uuid", nullable: false),
                    order = table.Column<Guid>(type: "uuid", nullable: false),
                    tracking = table.Column<string>(type: "text", nullable: false),
                    estimated_delivery = table.Column<DateOnly>(type: "date", nullable: false),
                    delivered_at = table.Column<DateOnly>(type: "date", nullable: true),
                    last_updated = table.Column<DateTime>(type: "timestamp with time zone", nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("pk_shippings", x => x.id);
                });
        }

        /// <inheritdoc />
        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropTable(
                name: "cart_items");

            migrationBuilder.DropTable(
                name: "order_items");

            migrationBuilder.DropTable(
                name: "orders");

            migrationBuilder.DropTable(
                name: "shippings");
        }
    }
}
