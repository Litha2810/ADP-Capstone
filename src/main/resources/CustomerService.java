package za.ac.cput.service;

import za.ac.cput.domain.Customer;
import za.ac.cput.repository.CustomerRepo.CustomerRepository;

public class CustomerService implements ICustomerService {

    private CustomerRepository repository;

    public CustomerService() {
        repository = CustomerRepository.getRepository();
    }

    @Override
    public Customer create(Customer customer) {
        return repository.create(customer);
    }

    @Override
    public Customer read(String customerId) {
        return repository.read(customerId);
    }

    @Override
    public Customer update(Customer customer) {
        return repository.update(customer);
    }

    @Override
    public boolean delete(String customerId) {
        return repository.delete(customerId);
    }
}
