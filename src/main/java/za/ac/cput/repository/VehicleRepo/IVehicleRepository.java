package za.ac.cput.repository.VehicleRepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Vehicle;

@Repository
public interface IVehicleRepository extends JpaRepository<Vehicle, String> {
}