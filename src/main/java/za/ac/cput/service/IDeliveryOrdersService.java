package za.ac.cput.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import za.ac.cput.domain.DeliveryOrders;

import java.util.List;

public interface IDeliveryOrdersService extends IService<DeliveryOrders,String>{
 List<DeliveryOrders> getAllDeliveryOrders();
 Page<DeliveryOrders> getPagedOrders(Pageable pageable);
 Page<DeliveryOrders> searchOrders(String search, Pageable pageable);
}
