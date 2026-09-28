package com.example.buspassapplicationtracker.Service;

import com.example.buspassapplicationtracker.Model.BusRoute;
import com.example.buspassapplicationtracker.Repository.BusRouteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusRouteService {

    private final BusRouteRepository busRouteRepository;

    public BusRouteService(BusRouteRepository busRouteRepository) {
        this.busRouteRepository = busRouteRepository;
    }

    public BusRoute createRoute(BusRoute busRoute) {
        return busRouteRepository.save(busRoute);
    }

    public List<BusRoute> getAllRoutes() {
        return busRouteRepository.findAll();
    }

    public BusRoute getRouteById(Long id) {
        return busRouteRepository.findById(id).orElse(null);
    }

    public BusRoute updateRoute(Long id, BusRoute busRoute) {
        BusRoute existingRoute = busRouteRepository.findById(id).orElse(null);

        if (existingRoute != null) {
            existingRoute.setRouteName(busRoute.getRouteName());
            existingRoute.setBoardingPoint(busRoute.getBoardingPoint());
            existingRoute.setDestination(busRoute.getDestination());

            return busRouteRepository.save(existingRoute);
        }

        return null;
    }

    public void deleteRoute(Long id) {
        busRouteRepository.deleteById(id);
    }
}