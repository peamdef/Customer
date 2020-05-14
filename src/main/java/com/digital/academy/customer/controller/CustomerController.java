package com.digital.academy.customer.controller;

import com.digital.academy.customer.model.Customer;
import com.digital.academy.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.ws.Response;
import java.util.List;

@RestController
@RequestMapping(path = "/customer")
public class CustomerController {
    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }

    @RequestMapping(path = "/list")
    public List<Customer> getAllCustomer(){
        return customerService.getCustomerList();
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomerById (@PathVariable Long id){
        Customer customer = customerService.getCustomer(id);
        /*if (customer != null)
        {
            return ResponseEntity.ok(customer);
        }
        else
        {
            return  ResponseEntity.notFound().build();
        }*/
        return customer != null ? ResponseEntity.ok(customer) :ResponseEntity.notFound().build();
    }

    @GetMapping(params = "name")
    public ResponseEntity<?> getCustomerByName (@RequestParam(value = "name") String name){
        List<Customer> customer = customerService.getCustomerName(name);
        return customer != null && !customer.isEmpty() ? ResponseEntity.ok(customer)
                : ResponseEntity.notFound().build();
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createCutomer(@Valid @RequestBody Customer body){
        Customer customer = customerService.createCustomer(body);
        return ResponseEntity.status(HttpStatus.CREATED).body(customer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> putCustomer(@PathVariable Long id, @Valid @RequestBody Customer body){
        body.setId(id);
        Customer customer = customerService.updateCustomer(id,body);
        return customer != null ? ResponseEntity.ok(customer)
                : ResponseEntity.notFound().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long id){
        return customerService.deleteById(id) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
