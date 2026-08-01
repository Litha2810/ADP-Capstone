package za.ac.cput.service;

import za.ac.cput.domain.Route;
import za.ac.cput.repository.RouteRepo.RouteRepository;

import java.util.List;

public interface IRouteService extends IService<Route, String> {
    List<Route> getAllRoutes();
}
