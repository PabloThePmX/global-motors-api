using System;
using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

namespace GlobalMotors.AddressesService.Migrations
{
    /// <inheritdoc />
    public partial class CreateDB : Migration
    {
        /// <inheritdoc />
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.CreateTable(
                name: "addresses",
                columns: table => new
                {
                    id = table.Column<Guid>(type: "uuid", nullable: false),
                    street = table.Column<string>(type: "text", nullable: false),
                    number = table.Column<int>(type: "integer", nullable: false),
                    neighborhood = table.Column<string>(type: "text", nullable: false),
                    city = table.Column<string>(type: "text", nullable: false),
                    states = table.Column<int>(type: "integer", nullable: false),
                    postal_code = table.Column<string>(type: "text", nullable: false),
                    complement = table.Column<string>(type: "text", nullable: true),
                    reference_point = table.Column<string>(type: "text", nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("pk_addresses", x => x.id);
                });
        }

        /// <inheritdoc />
        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropTable(
                name: "addresses");
        }
    }
}
