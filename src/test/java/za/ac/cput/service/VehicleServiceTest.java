package za.ac.cput.service;

/*
Vehicle.java
Vehicle service test class
Author: Litha Owethu Mazibuko (240143485)
Date: 2026
*/

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Vehicle;
import za.ac.cput.factory.VehicleFactory;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class VehicleServiceTest {

    @Autowired
    private VehicleService service;

    private static final Vehicle vehicle =
            VehicleFactory.createVehicle(
                    "V001",
                    "CAA24680",
                    Vehicle.VehicleType.TRUCK,
                    16000.0f,
                    Vehicle.VehicleStatus.AVAILABLE,
                    18000.0f,
                    LocalDate.of(2025,12,10)
            );

    @Test
    void a_create() {

        Vehicle created = service.create(vehicle);

        assertNotNull(created);

        System.out.println(created);

    }

    @Test
    void b_read() {

        Vehicle read = service.read(vehicle.getVehicleId());

        assertNotNull(read);

        System.out.println(read);

    }

    @Test
    void c_update() {

        Vehicle updated = new Vehicle.Builder()
                .copy(vehicle)
                .setCurrentStatus(Vehicle.VehicleStatus.IN_SERVICE)
                .build();

        Vehicle result = service.update(updated);

        assertNotNull(result);

        assertEquals(
                Vehicle.VehicleStatus.IN_SERVICE,
                result.getCurrentStatus());

        System.out.println(result);

    }

    @Test
    void d_getAllVehicles() {

        List<Vehicle> vehicles = service.getAllVehicles();

        assertNotNull(vehicles);

        System.out.println(vehicles);

    }

    @Test
    void e_delete() {

        boolean deleted = service.delete(vehicle.getVehicleId());

        assertTrue(deleted);

        System.out.println(deleted);

    }

}
