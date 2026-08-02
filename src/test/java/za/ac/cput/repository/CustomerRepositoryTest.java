package za.ac.cput.repository;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Customer;
import za.ac.cput.factory.CustomerFactory;
import za.ac.cput.repository.CustomerRepo.CustomerRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository repository;

    private Customer customer;

    @BeforeEach
    void setUp() {
        customer = CustomerFactory.createCustomer(
                "CUST-23451",
                "Yamkela",
                "0732510842",
                "yamkela197@gmail.com",
                "14 Aquarius Av Sandrift Milnerton 7441"
        );
    }

    @Test
    void a_create() {
        Customer created = repository.save(customer);

        assertNotNull(created);
        assertEquals(customer.getCustomerId(), created.getCustomerId());

        System.out.println(created);
    }

    @Test
    void b_read() {

        repository.save(customer);

        Customer read = repository.findById(customer.getCustomerId()).orElse(null);

        assertNotNull(read);

        System.out.println(read);
    }

    @Test
    void c_update() {

        repository.save(customer);

        Customer updated = new Customer.Builder()
                .copy(customer)
                .setCustomerName("Updated Customer")
                .build();

        Customer result = repository.save(updated);

        assertNotNull(result);

        System.out.println(result);
    }

    @Test
    void d_getAllCustomers() {

        List<Customer> customers = repository.findAll();

        assertNotNull(customers);

        customers.forEach(System.out::println);
    }

    @Test
    void e_delete() {

        repository.save(customer);

        repository.deleteById(customer.getCustomerId());

        Customer deleted = repository.findById(customer.getCustomerId()).orElse(null);

        assertNull(deleted);
    }
}
