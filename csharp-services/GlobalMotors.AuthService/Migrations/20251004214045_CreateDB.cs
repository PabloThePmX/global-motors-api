using System;
using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

namespace GlobalMotors.AuthService.Migrations
{
    /// <inheritdoc />
    public partial class CreateDB : Migration
    {
        /// <inheritdoc />
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.CreateTable(
                name: "users",
                columns: table => new
                {
                    id = table.Column<Guid>(type: "uuid", nullable: false),
                    email = table.Column<string>(type: "text", nullable: false),
                    password = table.Column<string>(type: "text", nullable: false),
                    role = table.Column<int>(type: "integer", nullable: false),
                    phone_number = table.Column<string>(type: "text", nullable: true),
                    first_name = table.Column<string>(type: "text", nullable: false),
                    last_name = table.Column<string>(type: "text", nullable: false),
                    birthday = table.Column<DateOnly>(type: "date", nullable: false),
                    document_number = table.Column<string>(type: "text", nullable: false),
                    document_type = table.Column<int>(type: "integer", nullable: false),
                    gender = table.Column<int>(type: "integer", nullable: false),
                    picture = table.Column<string>(type: "text", nullable: true),
                    current_address = table.Column<Guid>(type: "uuid", nullable: true),
                    last_updated = table.Column<DateTime>(type: "timestamp with time zone", nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("pk_users", x => x.id);
                });
        }

        /// <inheritdoc />
        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropTable(
                name: "users");
        }
    }
}
