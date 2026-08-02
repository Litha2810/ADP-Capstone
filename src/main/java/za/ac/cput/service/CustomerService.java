package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Customer;
import za.ac.cput.repository.CustomerRepo.CustomerRepository;

import java.util.List;

@Service
public class CustomerService implements ICustomerService {

    private CustomerRepository repository;

    @Autowired
    CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    public Customer create(Customer customer) {
        return this.repository.save(customer);
    }

    @Override
    public Customer read(String customerId) {
        return this.repository.findById(customerId).orElse(null);
    }

    @Override
    public Customer update(Customer customer) {
        return this.repository.save(customer);
    }

    @Override
    public boolean delete(String customerId) {
        this.repository.deleteById(customerId);
        return true;
    }

    @Override
    public List<Customer> getAll() {
        return this.repository.findAll();
    }
}