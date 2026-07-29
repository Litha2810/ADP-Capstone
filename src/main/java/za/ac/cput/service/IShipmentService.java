/* IShipmentService.java
   Shipment Service interface
   Author: NOMPUMELELO MBATHA (240256727)
   Date: 2026 */

package za.ac.cput.service;

import za.ac.cput.domain.Shipment;
import java.util.List;

public interface IShipmentService {
    Shipment create(Shipment shipment);
    Shipment read(String shipmentId);
    Shipment update(Shipment shipment);
    boolean delete(String shipmentId);
    List<Shipment> getAll();
}