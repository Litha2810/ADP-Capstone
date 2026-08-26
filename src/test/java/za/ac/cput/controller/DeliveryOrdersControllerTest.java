package za.ac.cput.controller;

import org.junit.jupiter.api.*;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import za.ac.cput.domain.Customer;
import za.ac.cput.domain.DeliveryOrders;
import za.ac.cput.factory.CustomerFactory;
import za.ac.cput.factory.DeliveryOrdersFactory;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(MethodOrderer.MethodName.class)
class DeliveryOrdersControllerTest {

    protected final RestTemplate restTemplate = new RestTemplate();
    protected static String BASE_URL = "http://localhost:8080/freightanddelivery/deliveryOrders";
    protected static String CUSTOMER_URL = "http://localhost:8080/freightanddelivery/customer";

    private static List<DeliveryOrders> orderList = new ArrayList<>();
    protected static  DeliveryOrders order1;
    static Customer customer;
    private static boolean initialised = false;

    @BeforeEach
    void setUp() {
        if(initialised) return;//other test methods dont work withou this, only allows to post customer for first test, so other test dont run into the error of duplicate

         LocalDate orderDate = LocalDate.of(2026, Month.MARCH, 25);
         LocalDate deliveryDate = LocalDate.of(2026, Month.APRIL, 1);
         DeliveryOrders.Status deliveryStatus = DeliveryOrders.Status.OrderPlaced;
         DeliveryOrders.PaymentStatus paymentStatus = DeliveryOrders.PaymentStatus.PENDING;

        customer = CustomerFactory.createCustomer("CUST-23451", "Yamkela", "0732510842"
                ,"yamkela197@gmail.com"
                ,"14 Aquarius Av Sandrift Milnerton 7441",orderList);

        RestTemplate setUpCustomerTemplate = new RestTemplate();
        ResponseEntity<Customer> customerResponse = setUpCustomerTemplate.postForEntity(CUSTOMER_URL+"/create", customer, Customer.class);
        customer = customerResponse.getBody();

                order1 = DeliveryOrdersFactory.createDeliveryOrder(
                "001",
                customer,
                orderDate, deliveryDate,
                deliveryStatus, paymentStatus,
                19990.0f,"no special instructions"
        );
        orderList.add(order1);

        initialised = true;
    }

    @Test
    void a_createDeliveryOrder() {
        String url = BASE_URL+"/create";
        ResponseEntity<DeliveryOrders> response = this.restTemplate.postForEntity(url,order1,DeliveryOrders.class);
        assertNotNull(response);
        DeliveryOrders created = response.getBody();
        System.out.println(created);
    }

    @Test
    void b_readDeliveryOrder() {
        String url = BASE_URL+"/read/"+order1.getOrderId();
        ResponseEntity<DeliveryOrders> response = this.restTemplate.getForEntity(url,DeliveryOrders.class);
        assertNotNull(response);
        DeliveryOrders read = response.getBody();
        System.out.println(read);
    }

    @Test
    void c_updateDeliveryOrder() {
        String url = BASE_URL+"/update";
        DeliveryOrders updatedOrder = new DeliveryOrders.Builder().copy(order1).setSpecialInstructions("Bring it to the small gate").build();
        this.restTemplate.put(url,updatedOrder);
        ResponseEntity<DeliveryOrders> response = this.restTemplate.getForEntity(BASE_URL+"/read/"+updatedOrder.getOrderId(),DeliveryOrders.class);
        System.out.println(response.getBody());
    }

    @Test
    void e_deleteOrder() {
        String url = BASE_URL+"/delete/"+order1.getOrderId();
        this.restTemplate.delete(url);
        ResponseEntity<DeliveryOrders> response = this.restTemplate.getForEntity(BASE_URL+"/read/"+order1.getOrderId(),DeliveryOrders.class);
        assertNull(response.getBody());
        System.out.println("Deleted: true");
    }

    @Test
    void d_getAllDeliveryOrders() {
        String url = BASE_URL+"/getAll";
        ResponseEntity<DeliveryOrders[]> response = this.restTemplate.getForEntity(url,DeliveryOrders[].class);
        System.out.println("All Orders");
        for(DeliveryOrders allOrders:response.getBody()){
            System.out.println(allOrders);
        }
    }
}