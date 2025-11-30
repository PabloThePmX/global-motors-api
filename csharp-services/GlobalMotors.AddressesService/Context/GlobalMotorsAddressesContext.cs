using GlobalMotors.AddressesService.Models;
using Microsoft.EntityFrameworkCore;

namespace GlobalMotors.AddressesService.Context
{
    public class GlobalMotorsAddressesContext : DbContext
    {
        public GlobalMotorsAddressesContext(DbContextOptions<GlobalMotorsAddressesContext> options) : base(options)
        {
        }

        public virtual DbSet<Address> Addresses { get; set; }
                
    }
}
