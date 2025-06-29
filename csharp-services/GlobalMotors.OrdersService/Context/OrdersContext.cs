using GlobalMotors.OrdersService.Models;
using Microsoft.EntityFrameworkCore;

namespace GlobalMotors.OrdersService.Context
{
    public class OrdersContext : DbContext
    {
        public OrdersContext(DbContextOptions<OrdersContext> options) : base(options)
        {

        }

        public DbSet<Order> Orders { get; set; }

        public DbSet<OrderItem> OrderItems { get; set; }

        public DbSet<Shipping> Shippings { get; set; }

        public DbSet<CartItem> CartItems { get; set; }

    }
}
