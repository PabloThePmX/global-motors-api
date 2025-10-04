using System;
using Microsoft.EntityFrameworkCore.Migrations;
using Npgsql.EntityFrameworkCore.PostgreSQL.Metadata;

#nullable disable

namespace GlobalMotors.UsersService.Migrations
{
    /// <inheritdoc />
    public partial class CreateDB : Migration
    {
        /// <inheritdoc />
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.CreateTable(
                name: "favorite_cars",
                columns: table => new
                {
                    user = table.Column<Guid>(type: "uuid", nullable: false),
                    car = table.Column<Guid>(type: "uuid", nullable: false),
                    last_updated = table.Column<DateTime>(type: "timestamp with time zone", nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("pk_favorite_cars", x => new { x.user, x.car });
                });

            migrationBuilder.CreateTable(
                name: "system_info",
                columns: table => new
                {
                    id = table.Column<int>(type: "integer", nullable: false)
                        .Annotation("Npgsql:ValueGenerationStrategy", NpgsqlValueGenerationStrategy.IdentityByDefaultColumn),
                    version = table.Column<string>(type: "text", nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("pk_system_info", x => x.id);
                });

            migrationBuilder.CreateTable(
                name: "user_settings",
                columns: table => new
                {
                    user = table.Column<Guid>(type: "uuid", nullable: false),
                    language = table.Column<int>(type: "integer", nullable: false),
                    displayed_currency = table.Column<int>(type: "integer", nullable: false),
                    last_updated = table.Column<DateTime>(type: "timestamp with time zone", nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("pk_user_settings", x => x.user);
                });
        }

        /// <inheritdoc />
        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropTable(
                name: "favorite_cars");

            migrationBuilder.DropTable(
                name: "system_info");

            migrationBuilder.DropTable(
                name: "user_settings");
        }
    }
}
