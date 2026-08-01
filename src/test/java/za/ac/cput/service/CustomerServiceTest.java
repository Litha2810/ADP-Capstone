package za.ac.cput.service;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Customer;
import za.ac.cput.factory.CustomerFactory;

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

    private static Customer customer = CustomerFactory.createCustomer(
            "C2424",
            "Anathi Mgcubhe",
            "0678372987",
            "Anathimgcubhe@gmail.com",
            "31 Dumani Street Joe Slovo Milnerton Cape Town"
    );

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