package za.ac.cput.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Customer;
import za.ac.cput.domain.DeliveryOrders;
import za.ac.cput.factory.CustomerFactory;
import za.ac.cput.factory.DeliveryOrdersFactory;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/*
CustomerServiceTest.java
Customer Module class
Author: YAMKELA MGCUBHE (222040114)
Date: 2026
 */

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class CustomerServiceTest {

    @Autowired
    private CustomerService service;

    private static  List<DeliveryOrders> orderList = new ArrayList<>();
    static LocalDate orderDate = LocalDate.of(2026, Month.MARCH, 25);
    static LocalDate deliveryDate = LocalDate.of(2026, Month.APRIL, 1);
    static DeliveryOrders.Status deliveryStatus = DeliveryOrders.Status.OrderPlaced;
    static DeliveryOrders.PaymentStatus paymentStatus = DeliveryOrders.PaymentStatus.PENDING;


    private Customer customer;

    @BeforeEach
    void setUp() {
        customer = CustomerFactory.createCustomer("CUST-23451", "Yamkela", "0732510842"
                ,"yamkela197@gmail.com"
                ,"14 Aquarius Av Sandrift Milnerton 7441",orderList);

        DeliveryOrders order1 = DeliveryOrdersFactory.createDeliveryOrder("001",customer,orderDate,deliveryDate,deliveryStatus,paymentStatus,444f,"None");
        DeliveryOrders order2 = DeliveryOrdersFactory.createDeliveryOrder("002",customer,orderDate,deliveryDate,deliveryStatus,paymentStatus,500f,"Place at front of the door");
        DeliveryOrders order3 = DeliveryOrdersFactory.createDeliveryOrder("003",customer,orderDate,deliveryDate,deliveryStatus,paymentStatus,500f,"Place at front of the door");
        DeliveryOrders order4 = DeliveryOrdersFactory.createDeliveryOrder("004",customer,orderDate,deliveryDate,deliveryStatus,paymentStatus,500f,"Place at front of the door");
        DeliveryOrders order5 = DeliveryOrdersFactory.createDeliveryOrder("005",customer,orderDate,deliveryDate,deliveryStatus,paymentStatus,500f,"Place at front of the door");
        DeliveryOrders order6 = DeliveryOrdersFactory.createDeliveryOrder("006",customer,orderDate,deliveryDate,deliveryStatus,paymentStatus,500f,"Place at front of the door");




        orderList.add(order1);
        orderList.add(order2);
        orderList.add(order3);
        orderList.add(order4);
        orderList.add(order5);
        orderList.add(order6);
    }

    @Test
    void a_create() {
        Customer created = this.service.create(customer);
        assertNotNull(created);
        System.out.println(created);
    }

    @Test
    void b_read() {
        Customer read = this.service.read(customer.getCustomerId());
        assertNotNull(read);
        System.out.println(read);
    }

    @Test
    void c_update() {
        Customer updatedCustomer = new Customer.Builder()
                .copy(customer)
                .setCustomerName("John Smith")
                .setPhoneNumber("0823456789")
                .setAddress("Bellville")
                .build();

        Customer updated = this.service.update(updatedCustomer);
        assertNotNull(updated);
        System.out.println(updated);
    }

    @Test
    void d_getAll() {
        List<Customer> customers = this.service.getAll();
        assertNotNull(customers);
        System.out.println(customers);
    }

    @Test
    void e_delete() {
        boolean deleted = this.service.delete(customer.getCustomerId());
        assertTrue(deleted);
        System.out.println(deleted);
    }
}