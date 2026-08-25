package za.ac.cput.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import za.ac.cput.domain.Customer;
import za.ac.cput.domain.DeliveryOrders;
import za.ac.cput.factory.CustomerFactory;
import za.ac.cput.factory.DeliveryOrdersFactory;
import za.ac.cput.repository.CustomerRepo.CustomerRepository;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/*
CustomerRepositoryTest.java
Customer Module class
Author: YAMKELA MGCUBHE (222040114)
Date: 2026
 */

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(MethodOrderer.MethodName.class)
class CustomerControllerTest {

    protected final RestTemplate restTemplate = new RestTemplate();

    protected static final String BASE_URL =
            "http://localhost:8080/freightanddelivery/customer";

    protected static Customer customer;
    static DeliveryOrders order1;
    static DeliveryOrders order2;


    static LocalDate orderDate = LocalDate.of(2026, Month.MARCH, 25);
    static LocalDate deliveryDate = LocalDate.of(2026, Month.APRIL, 1);

    static DeliveryOrders.Status deliveryStatus = DeliveryOrders.Status.OrderPlaced;
    static DeliveryOrders.PaymentStatus paymentStatus = DeliveryOrders.PaymentStatus.PENDING;

    static List<DeliveryOrders> orderList = new ArrayList<>();


    @BeforeAll
    public static void setUp() {

        customer = CustomerFactory.createCustomer("CUST-23451", "Yamkela", "0732510842"
                , "yamkela197@gmail.com"
                , "14 Aquarius Av Sandrift Milnerton 7441", orderList);
        order1 = DeliveryOrdersFactory.createDeliveryOrder("001", customer, orderDate, deliveryDate, deliveryStatus, paymentStatus, 444f, "None");
        order2 = DeliveryOrdersFactory.createDeliveryOrder("002", customer , orderDate, deliveryDate, deliveryStatus, paymentStatus, 500f, "Place at front of the door");

        orderList.add(order1);
        orderList.add(order2);
    }


    @Test
    void a_createCustomer() {

        String url = BASE_URL + "/create";

        ResponseEntity<Customer> response =
                this.restTemplate.postForEntity(url, customer, Customer.class);

        assertNotNull(response);

        Customer created = response.getBody();

        System.out.println(created);
    }

    @Test
    void b_readCustomer() {

        String url = BASE_URL + "/read/" + customer.getCustomerId();

        ResponseEntity<Customer> response =
                this.restTemplate.getForEntity(url, Customer.class);

        assertNotNull(response);

        Customer read = response.getBody();

        System.out.println(read);
    }

    @Test
    void c_updateCustomer() {

        String url = BASE_URL + "/update";

        Customer updatedCustomer = new Customer.Builder()
                .copy(customer)
                .setCustomerName("John Smith")
                .setPhoneNumber("0823456789")
                .setAddress("Bellville")
                .build();

        this.restTemplate.put(url, updatedCustomer);

        ResponseEntity<Customer> response =
                this.restTemplate.getForEntity(
                        BASE_URL + "/read/" + updatedCustomer.getCustomerId(),
                        Customer.class);

        System.out.println(response.getBody());
    }

    @Test
    void d_getAllCustomers() {

        String url = BASE_URL + "/getAll";

        ResponseEntity<Customer[]> response =
                this.restTemplate.getForEntity(url, Customer[].class);

        System.out.println("All Customers");

        for (Customer customer : response.getBody()) {
            System.out.println(customer);
        }
    }

    @Test
    void e_deleteCustomer() {

        String url = BASE_URL + "/delete/" + customer.getCustomerId();

        this.restTemplate.delete(url);

        ResponseEntity<Customer> response =
                this.restTemplate.getForEntity(
                        BASE_URL + "/read/" + customer.getCustomerId(),
                        Customer.class);

        assertNull(response.getBody());

        System.out.println("Deleted: true");
    }
}