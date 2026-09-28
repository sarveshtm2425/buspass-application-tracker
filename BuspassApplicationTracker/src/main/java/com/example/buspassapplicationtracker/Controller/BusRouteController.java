package com.example.buspassapplicationtracker.Controller;

import com.example.buspassapplicationtracker.Model.BusRoute;
import com.example.buspassapplicationtracker.Service.BusRouteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/routes")
public class BusRouteController {

    private final BusRouteService busRouteService;

    public BusRouteController(BusRouteService busRouteService) {
        this.busRouteService = busRouteService;
    }

    @PostMapping
    public BusRoute createRoute(@RequestBody BusRoute busRoute) {
        return busRouteService.createRoute(busRoute);
    }

    @GetMapping
    public List<BusRoute> getAllRoutes() {
        return busRouteService.getAllRoutes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BusRoute> getRouteById(@PathVariable Long id) {
        return ResponseEntity.ok(busRouteService.getRouteById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BusRoute> updateRoute(
            @PathVariable Long id,
            @RequestBody BusRoute busRoute) {

        return ResponseEntity.ok(
                busRouteService.updateRoute(id, busRoute)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRoute(@PathVariable Long id) {
        busRouteService.deleteRoute(id);
        return ResponseEntity.ok("Bus route deleted successfully");
    }
}