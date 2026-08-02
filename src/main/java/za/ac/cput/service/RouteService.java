package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Route;
import za.ac.cput.repository.RouteRepo.RouteRepository;

import java.util.List;

@Service
public class RouteService implements IRouteService{
    private RouteRepository repository;

    @Autowired
    RouteService(RouteRepository repository){this.repository=repository;}

    @Override
    public Route create(Route route) {
        return this.repository.save(route);
    }

    @Override
    public Route read(String s) {
        return this.repository.findById(s).orElse(null);
    }

    @Override
    public Route update(Route route) {
        return this.repository.save(route);
    }

    @Override
    public boolean delete(String s) {
        this.repository.deleteById(s);
        return true;
    }

    @Override
    public List<Route> getAllRoutes() {
        return this.repository.findAll();
    }
}
