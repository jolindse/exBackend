package to.mattias.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import to.mattias.entities.Customer;
import to.mattias.services.CustomerService;

import java.util.List;

/**
 * <h1>Created by Mattias on 2017-01-23.</h1>
 */
@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService service;

    @GetMapping
    public List<Customer> findAll() {
        return service.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Customer findOne(@PathVariable int customerId) {
        return service.findById(customerId);
    }

    @PostMapping
    public Customer save(@RequestBody Customer customer) {
        return service.save(customer);
    }

    @DeleteMapping
    @RequestMapping(value = "{customerId}", method = RequestMethod.DELETE)
    public void deleteCustomer(@PathVariable int customerId) {
        service.delete(customerId);
    }

    @PutMapping
    public Customer updateCustomer(@RequestBody Customer customer) {
        return service.update(customer);
    }
}
