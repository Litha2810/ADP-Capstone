package za.ac.cput.repository.DeliveryOrdersRepo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.DeliveryOrders;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/*
DeliveryOrdersRepository.java
Delivery Orders module class
Author: Ryle Peter May (230333907)
Date: 2026
 */
@Repository
public interface DeliveryOrdersRepository extends JpaRepository<DeliveryOrders, String> {
    List<DeliveryOrders> findByCustomer_CustomerId(String CustomerId);
    List<DeliveryOrders> findByDeliveryStatus(DeliveryOrders.Status deliveryStatus);
    List<DeliveryOrders> findByDeliveryDate(LocalDate deliveryDate);

//https://www.baeldung.com/spring-data-jpa-query
//    @Query("SELECT d FROM DeliveryOrders d WHERE " +
//            "LOWER(d.orderId) LIKE LOWER(CONCAT('%', :search, '%') ) OR " +
//            "LOWER(d.customerId.customerName) LIKE LOWER(CONCAT('%', :search, '%'))") // Using query annotation to create custom query with jpsql
@Query("SELECT d FROM DeliveryOrders d WHERE " +
        "LOWER(d.orderId) LIKE LOWER(CONCAT('%', :search, '%') )")

    //https://www.baeldung.com/spring-data-jpa-pagination-sorting
    Page<DeliveryOrders> searchOrders(@Param("search") String search, Pageable pageable);//this could be the wrong pageable and page so take note of this incase of errors

}
