package com.digitalacademy.customer.service;

import com.digital.academy.customer.model.Customer;
import com.digital.academy.customer.repositories.CustomerRepository;
import com.digital.academy.customer.service.CustomerService;
import com.digitalacademy.customer.support.CustomerSupportTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@SpringBootConfiguration
public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    CustomerService customerService;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        customerService = new CustomerService(customerRepository);
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
    @DisplayName("test get all customer should return list")
    @Test
    void testGetAllCustomer(){
        when(customerRepository.findAll()).thenReturn(getCustomerList());
        List<Customer> resp = customerService.getCustomerList();

        assertEquals(1,resp.get(0).getId().intValue());
        assertEquals("Ryan",resp.get(0).getFirstName());
        assertEquals("Giggs",resp.get(0).getLastName());
        assertEquals("hello@hi.com",resp.get(0).getEmail());
        assertEquals("0911111111",resp.get(0).getPhoneNo());
        assertEquals(23,resp.get(0).getAge().intValue());

        assertEquals(2,resp.get(1).getId().intValue());
        assertEquals("Robin",resp.get(1).getFirstName());
        assertEquals("Good",resp.get(1).getLastName());
        assertEquals("yellow@red.com",resp.get(1).getEmail());
        assertEquals("0912345678",resp.get(1).getPhoneNo());
        assertEquals(22,resp.get(1).getAge().intValue());
    }

    @DisplayName("Test get all customer by id should return list")
    @Test
    void testGetCustomerById(){
        Long reqParam = 1L;
        when(customerRepository.findAllById(reqParam)).thenReturn(getCustomerList().get(0));
        Customer resp = customerService.getCustomer(reqParam);
        assertEquals(1,resp.getId().intValue());
        assertEquals("Ryan",resp.getFirstName());
        assertEquals("Giggs",resp.getLastName());
        assertEquals("hello@hi.com",resp.getEmail());
        assertEquals("0911111111",resp.getPhoneNo());
        assertEquals(23,resp.getAge().intValue());
    }
    @DisplayName("Test get all customer by name should return list")
    @Test
    void testGetAllCustomerByName(){
        String name = "Ryan";
        when(customerRepository.findByFirstName(name)).thenReturn(CustomerSupportTest
        .getCustomerNameRyan());
        List<Customer> resp = customerService.getCustomerName(name);
        assertEquals(1,resp.get(0).getId().intValue());
        assertEquals("Ryan",resp.get(0).getFirstName());
        assertEquals("Gig",resp.get(0).getLastName());
        assertEquals("iamgig@test.com",resp.get(0).getEmail());
        assertEquals("0912345678",resp.get(0).getPhoneNo());
        assertEquals(22,resp.get(0).getAge().intValue());

        assertEquals(2,resp.get(1).getId().intValue());
        assertEquals("Ryan",resp.get(1).getFirstName());
        assertEquals("New",resp.get(1).getLastName());
        assertEquals("David@test.com",resp.get(1).getEmail());
        assertEquals("0912345678",resp.get(1).getPhoneNo());
        assertEquals(23,resp.get(1).getAge().intValue());
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

    @DisplayName("test create customer")
    @Test
    void testCreateCustomer(){
        Customer reqCustomer = new Customer();
        reqCustomer.setFirstName("Noon");
        reqCustomer.setLastName("Bow");
        reqCustomer.setEmail("bow@test.com");
        reqCustomer.setPhoneNo("0912345678");
        reqCustomer.setAge(18);

        when(customerRepository.save(reqCustomer)).thenReturn(getNewCustomer());
        Customer resp = customerService.createCustomer(reqCustomer);

        assertEquals(1,resp.getId().intValue());
        assertEquals("Noon",resp.getFirstName());
        assertEquals("Bow",resp.getLastName());
        assertEquals("bow@test.com",resp.getEmail());
        assertEquals("0912345678",resp.getPhoneNo());
        assertEquals(18,resp.getAge().intValue());
    }

    @DisplayName("Test update customer")
    @Test
    void  testUpdateCustomer(){
        Long reqId = 1L;
        Customer reqCustomer = new Customer();
        reqCustomer.setId(1L);
        reqCustomer.setFirstName("Noon");
        reqCustomer.setLastName("Bow");
        reqCustomer.setEmail("bow@test.com");
        reqCustomer.setPhoneNo("0912345678");
        reqCustomer.setAge(18);
        when(customerRepository.findAllById(reqId)).thenReturn(getNewCustomer());
        when(customerRepository.save(reqCustomer)).thenReturn(getNewCustomer());
        Customer resp = customerService.updateCustomer(reqId,reqCustomer);

        assertEquals(1,resp.getId().intValue());
        assertEquals("Noon",resp.getFirstName());
        assertEquals("Bow",resp.getLastName());
        assertEquals("bow@test.com",resp.getEmail());
        assertEquals("0912345678",resp.getPhoneNo());
        assertEquals(18,resp.getAge().intValue());
    }

    @DisplayName("Test update customer should return fail")
    @Test
    void testUpdateCustomerFail() {
        Long reqId = 4L;
        Customer reqCustomer = new Customer();
        reqCustomer.setId(1L);
        reqCustomer.setFirstName("Flint");
        reqCustomer.setLastName("Stone");
        reqCustomer.setEmail("flints.stone@test.com");
        reqCustomer.setPhoneNo("0988888888");
        reqCustomer.setAge(26);
        when(customerRepository.findAllById(reqId)).thenReturn(null);
        Customer resp = customerService.updateCustomer(reqId, reqCustomer);
        assertEquals(null, resp);
    }


    @DisplayName("Test delete customer")
    @Test
    void testDeleteCustomer(){
        Long reqId = 1L;
        doNothing().when(customerRepository).deleteById(reqId);
        boolean resp = customerService.deleteById(reqId);
        assertTrue(resp);

    }

    @DisplayName("Test delete customer fail case")
    @Test
    void testDeleteCustomerFail(){
        Long reqId = 1L;
        doThrow(EmptyResultDataAccessException.class).when(customerRepository).deleteById(reqId);
        boolean resp = customerService.deleteById(reqId);
        assertFalse(resp);
    }
}
