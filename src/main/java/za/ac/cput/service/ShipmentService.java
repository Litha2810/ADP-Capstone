/* ShipmentService.java
   Shipment Service implementation
   Author: NOMPUMELELO MBATHA (240256727)
   Date: 2026 */

package za.ac.cput.service;

import org.springframework.stereotype.Service;
import za.ac.cput.domain.Shipment;
import za.ac.cput.repository.ShipmentRepo.ShipmentRepository;
import java.util.List;

@Service
public class ShipmentService implements IShipmentService {

    private final ShipmentRepository shipmentRepository = ShipmentRepository.getInstance();

    @Override
    public Shipment create(Shipment shipment) {
        return shipmentRepository.create(shipment);
    }

    @Override
    public Shipment read(String shipmentId) {
        return shipmentRepository.read(shipmentId);
    }

    @Override
    public Shipment update(Shipment shipment) {
        return shipmentRepository.update(shipment);
    }

    @Override
    public boolean delete(String shipmentId) {
        return shipmentRepository.delete(shipmentId);
    }

    @Override
    public List<Shipment> getAll() {
        return shipmentRepository.getAllShipments();
    }
}