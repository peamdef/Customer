package com.digitalacademy.customer.support;

import com.digital.academy.customer.model.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerSupportTest {

    public static Customer createNewCustomer(){
        Customer customer = new Customer();
        customer.setFirstName("Madrid");
        customer.setLastName("Bayern");
        customer.setEmail("test@test.com");
        customer.setPhoneNo("0912345678");
        customer.setAge(18);
        return customer;
    }
    public static Customer getUpdateCustomer(){
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setFirstName("Noon2");
        customer.setLastName("Bow");
        customer.setEmail("bow@test.com");
        customer.setPhoneNo("0912345678");
        customer.setAge(18);
        return customer;
    }
    public static Customer responseCreateNewCustomer(){
        Customer customer = new Customer();
        customer.setId(8L);
        customer.setFirstName("Madrid");
        customer.setLastName("Bayern");
        customer.setEmail("test@test.com");
        customer.setPhoneNo("0912345678");
        customer.setAge(18);
        return customer;
    }
    public static List<Customer> getCustomerNameRyan(){
        List<Customer> customerList = new ArrayList<Customer>();
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setFirstName("Ryan");
        customer.setLastName("Gig");
        customer.setEmail("iamgig@test.com");
        customer.setPhoneNo("0912345678");
        customer.setAge(22);
        customerList.add(customer);

        customer = new Customer();
        customer.setId(2L);
        customer.setFirstName("Ryan");
        customer.setLastName("New");
        customer.setEmail("David@test.com");
        customer.setPhoneNo("0912345678");
        customer.setAge(23);
        customerList.add(customer);
        return customerList;
    }
    public static Customer getNewCustomer(){
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setFirstName("Noon");
        customer.setLastName("Bow");
        customer.setEmail("bow@test.com");
        customer.setPhoneNo("0912345678");
        customer.setAge(18);
        return customer;
    }
    public static List<Customer> getCustomerList(){
        List<Customer> customerList = new ArrayList<>();
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setFirstName("Ryan");
        customer.setLastName("Giggs");
        customer.setEmail("hello@hi.com");
        customer.setPhoneNo("0911111111");
        customer.setAge(23);
        customerList.add(customer);

        customer = new Customer();
        customer.setId(2L);
        customer.setFirstName("Robin");
        customer.setLastName("Good");
        customer.setEmail("yellow@red.com");
        customer.setPhoneNo("0912345678");
        customer.setAge(22);
        customerList.add(customer);
        return customerList;
    }
}
