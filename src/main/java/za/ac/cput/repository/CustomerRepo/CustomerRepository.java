package za.ac.cput.repository.CustomerRepo;


/*
CustomerRepository.java
Customer module class
Author: YAMKELA MGCUBHE (222040114)
Date: 2026
 */

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {

}




