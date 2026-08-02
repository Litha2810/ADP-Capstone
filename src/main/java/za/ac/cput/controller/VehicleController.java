package za.ac.cput.controller;

/*
Vehicle.java
Vehicle controller class
Author: Litha Owethu Mazibuko (240143485)
Date: 2026
*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Vehicle;
import za.ac.cput.service.VehicleService;

import java.util.List;


    @RestController
    @RequestMapping("/vehicle")
    public class VehicleController {

        private final VehicleService service;

        @Autowired
        public VehicleController(VehicleService service) {
            this.service = service;
        }

        @PostMapping("/create")
        public Vehicle createVehicle(@RequestBody Vehicle vehicle) {
            return service.create(vehicle);
        }

        @GetMapping("/read/{vehicleId}")
        public Vehicle readVehicle(@PathVariable String vehicleId) {
            return service.read(vehicleId);
        }

        @PutMapping("/update")
        public Vehicle updateVehicle(@RequestBody Vehicle vehicle) {
            return service.update(vehicle);
        }

        @DeleteMapping("/delete/{vehicleId}")
        public boolean deleteVehicle(@PathVariable String vehicleId) {
            return service.delete(vehicleId);
        }

        @GetMapping("/getAll")
        public List<Vehicle> getAllVehicles() {
            return service.getAllVehicles();
        }
    }

