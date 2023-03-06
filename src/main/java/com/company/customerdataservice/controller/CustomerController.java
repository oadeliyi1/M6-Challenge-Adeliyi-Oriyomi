package com.company.customerdataservice.controller;


import com.company.customerdataservice.repository.CustomerRepository;
import com.company.customerdataservice.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class CustomerController {

    @Autowired
    CustomerRepository repo;

    /*

    A POST route that creates a new customer. 10 pts

A PUT route that updates an existing customer. 10 pts

A DELETE route that deletes an existing customer. 10 pts

A GET route that returns a specific customer by id. 10 pts

A GET route that returns all customers for a specific state. 10 pts
     */

    @PutMapping("/customers")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCustomer(@RequestBody Customer Customer) {
        repo.save(Customer);
    }

    @DeleteMapping("/customers/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable int id) {
        repo.deleteById(id);
    }

    @GetMapping("/customers/{id}")
    public Customer getCustomerById(@PathVariable int id) {

        Optional<Customer> returnVal = repo.findById(id);
        if (returnVal.isPresent()) {
            return returnVal.get();
        } else {
            return null;
        }
    }

    @GetMapping("/customers/{state}")
    public List<Customer> getCustomersByState(@PathVariable String state) {

      //  List<Customer> customerList = new ArrayList<>();
        List<Customer> returnVal = repo.findByState(state);
        if (!returnVal.isEmpty()) {
            return returnVal;
        } else {
            return null;
        }
    }
}



