package za.ac.cput.repository.RouteRepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Route;

@Repository
public interface RouteRepository extends JpaRepository<Route, String> {
}
