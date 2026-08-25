package za.ac.cput.factory;

import za.ac.cput.domain.Customer;
import za.ac.cput.domain.DeliveryOrders;
import za.ac.cput.util.Helper;

import java.util.List;

/*
CustomerFactory.java
Customer module class
Author: YAMKELA MGCUBHE (222040114)
Date: 2026
 */

public class CustomerFactory {
    public static Customer createCustomer(String customerId,
                                          String customerName,
                                          String phoneNumber, String email , String address, List<DeliveryOrders> orders){

        if(Helper.isEmptyOrNull(customerId) ||
                Helper.isEmptyOrNull(customerName) ||
                Helper.isEmptyOrNull(phoneNumber) ||
                Helper.isEmptyOrNull(address))
        {
            return null;

        }

        if(!Helper.isValidEmail(email)){
            return null;
        }


        return new Customer.Builder()
                .setCustomerId(customerId)
                .setCustomerName(customerName)
                .setPhoneNumber(phoneNumber)
                .setEmail(email)
                .setAddress(address).setOrders(orders)
                .build();

    }

}
