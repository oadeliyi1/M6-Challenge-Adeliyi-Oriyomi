package com.company.customerdataservice.controller;


import com.company.customerdataservice.repository.CustomerRepository;
import com.company.customerdataservice.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;

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
    public void updateCustomer(@RequestBody Customer customer) {
        repo.save(customer);
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

  //  @GetMapping("/customers/{state}")
  @RequestMapping(value="/customers/{state}", method=RequestMethod.GET)
  @ResponseStatus(value = HttpStatus.OK)
  public List<Customer> getCustomersByState(@PathVariable String state) {

      //  List<Customer> customerList = new ArrayList<>();

        List<Customer> returnVal = repo.findByState(state);
        if (returnVal != null) {
            return returnVal;
        }
        throw new NotFoundException("Customers from that state not found.");


       // return repo.findByState(state);

    }

    @PostMapping("/customers")
    @ResponseStatus(HttpStatus.CREATED)
    public Customer addCustomer (@RequestBody Customer Customer) {
        return repo.save(Customer);
    }

    @GetMapping("/customers")
    public List<Customer> getCustomers() {
        return repo.findAll();
    }
}



