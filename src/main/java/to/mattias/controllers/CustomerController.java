package to.mattias.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.*;
import to.mattias.entities.Customer;
import to.mattias.services.CustomerService;

import java.util.List;

/**
 * <h1>Created by Mattias on 2017-01-23.</h1>
 */
@RestController
@RequestMapping("/customer")
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class CustomerController {

    @Autowired
    private CustomerService service;

    @GetMapping
    @PreAuthorize("@securityService.hasRole('ADMIN')")
    public List<Customer> findAll() {
        return service.findAll();
    }

    @RequestMapping(value = "/{customerId}", method = RequestMethod.GET)
    public Customer findOne(@PathVariable int customerId) {
        return service.findById(customerId);
    }

    @PostMapping
    @PreAuthorize("@securityService.hasRole('ADMIN')")
    public Customer save(@RequestBody Customer customer) {
        return service.save(customer);
    }

    @DeleteMapping
    @RequestMapping(value = "/{customerId}", method = RequestMethod.DELETE)
    @PreAuthorize("@securityService.hasRole('ADMIN')")
    public void deleteCustomer(@PathVariable int customerId) {
        service.delete(customerId);
    }

    @PutMapping
    @PreAuthorize("@securityService.hasRole('ADMIN')")
    public Customer updateCustomer(@RequestBody Customer customer) {
        return service.update(customer);
    }
}
