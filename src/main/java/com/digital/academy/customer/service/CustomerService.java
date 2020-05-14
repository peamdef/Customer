package com.digital.academy.customer.service;

import com.digital.academy.customer.model.Customer;
import com.digital.academy.customer.repositories.CustomerRepository;
import org.apache.el.stream.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    private CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getCustomerList(){
        return customerRepository.findAll();
        //return customerRepository.findAll();
    }

    public  Customer getCustomer(Long id){
        return customerRepository.findAllById(id);
    }
    public List<Customer> getCustomerName(String name){
        //return getCustomerDemo().get(0);
        return customerRepository.findByFirstName(name);
    }

    public Customer createCustomer(Customer body){
     return customerRepository.save(body);
    }

    public Customer updateCustomer(Long id,Customer customerReq){
        Customer customerResp= customerRepository.findAllById(id);
        /*if(customerResp != null)
        {
            return customerRepository.save(customerReq);
        }else{
            return null;
        }*/
        return customerRepository.findAllById(id) != null ?
                customerRepository.save(customerReq) :
                null;
    }

    public boolean deleteById(Long id){
        try {
            customerRepository.deleteById(id);
            return true;
        } catch (EmptyResultDataAccessException e){
            return  false;
        }

    }
}
