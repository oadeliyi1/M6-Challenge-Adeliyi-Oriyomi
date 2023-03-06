package com.company.customerdataservice.repository;


import com.company.customerdataservice.model.Customer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerRepositoryTests {

    @Autowired
    CustomerRepository customerRepo;

    @Before
    public void setUp() throws Exception {
        customerRepo.deleteAll();
    }

    @Test
    public void addCustomer() {
        //Arrange...
        Customer customer = new Customer();
        customer.setFirstName("John");
        customer.setLastName("Doe");

        customer.setPhone("212-345-7891");
        customer.setAddress1("22 Melo Road");
        customer.setEmail("john.doe@gmail.com");
        customer.setCity("New Brunswick");
        customer.setState("New Jersey");
        customer.setPostalCode(07444);
        customer.setCountry("United States");

        //Act...
        customer = customerRepo.save(customer);

        //Assert...
        Optional<Customer> customer1 = customerRepo.findById(customer.getId());

        assertEquals(customer1.get(), customer);
    }


    @Test
    public void updateCustomer() {
        //Arrange...
        Customer customer = new Customer();
        customer.setFirstName("Joe");
        customer.setLastName("Smith");
        customer.setPhone("111-222-3456");
        customer.setCompany("BigCo");

        customerRepo.save(customer);

        //Act...
        customer.setFirstName("Sam");

        customerRepo.save(customer);

        //Assert...
        Optional<Customer> customer1 = customerRepo.findById(customer.getId());

        assertEquals(customer1.get(), customer);
    }

    @Test
    public void deleteCustomer() {
        Customer customer = new Customer();
        customer.setFirstName("John");
        customer.setLastName("Doe");

        customer.setPhone("212-345-7891");
        customer.setAddress1("22 Melo Road");
        customer.setEmail("john.doe@gmail.com");

        customer.setCity("New Brunswick");
        customer.setState("New Jersey");
        customer.setPostalCode(07444);
        customer.setCountry("United States");


        customer.setCompany("Walmart");

        customerRepo.save(customer);

        //Act...
        customerRepo.deleteById(customer.getId());

        //Assert...
        Optional<Customer> customer1 = customerRepo.findById(customer.getId());
        assertFalse(customer1.isPresent());
    }
    @Test
    public void shouldFindCustomerbyId() {

    }

    public void shouldFindCustomersbyState() {
        Customer customer = new Customer();
        customer.setFirstName("John");
        customer.setLastName("Doe");

        customer.setPhone("212-345-7891");
        customer.setAddress1("22 Melo Road");
        customer.setEmail("john.doe@gmail.com");

        customer.setCity("New Brunswick");
        customer.setState("New Jersey");
        customer.setPostalCode(07444);
        customer.setCountry("United States");


        customer.setCompany("Walmart");

        customerRepo.save(customer);


        Customer customer2 = new Customer();
        customer.setFirstName("Sarah");
        customer.setLastName("Johnson");

        customer.setPhone("973-298-0913");
        customer.setEmail("sarah.johnson@vangaurd.com");

        customer.setAddress1("514 Saxton Avenue");
        customer.setAddress2("Apt 9B");
        customer.setCity("Houston");
        customer.setState("Texas");
        customer.setPostalCode(97192);
        customer.setCountry("United States");
        customer.setCompany("Vanguard");
        customerRepo.save(customer2);



        Customer customer3 = new Customer();
        customer.setFirstName("Daniel");
        customer.setLastName("Smith");

        customer.setPhone("973-298-0913");
        customer.setEmail("daniel.smith@vangaurd.com");

        customer.setAddress1("4 Main Street");
        customer.setCity("Dallas");
        customer.setState("Texas");
        customer.setPostalCode(39012);
        customer.setCountry("United States");
        customer.setCompany("Vanguard");

        customerRepo.save(customer3);

        List<Customer> texasList = customerRepo.findByState("Texas");



    }
}
