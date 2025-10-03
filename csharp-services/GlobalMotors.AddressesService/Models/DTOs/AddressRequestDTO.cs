using GlobalMotors.AddressesService.Models.Enums;

namespace GlobalMotors.AddressesService.Models.DTOs
{
    public class AddressRequestDTO
    {
        public required string Street { get; set; }

        public required int Number { get; set; }

        public required string Neighborhood { get; set; }

        public required string City { get; set; }

        public required BrStates States { get; set; }

        public required string PostalCode { get; set; }

        public string? Complement { get; set; }

        public string? ReferencePoint { get; set; }
    }
}
