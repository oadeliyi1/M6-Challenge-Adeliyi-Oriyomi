package com.company.customerdataservice.repository;


import com.company.customerdataservice.model.Customer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
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
    public void shouldAddCustomer() {
        //Arrange...
        Customer customer = new Customer();
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setCompany("Disney");

        customer.setPhone("212-345-7891");
        customer.setAddress1("22 Melo Road");
        customer.setAddress2("apt1");
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
    public void shouldUpdateCustomer() {
        //Arrange...
        Customer customer = new Customer();
        customer.setFirstName("Joe");
        customer.setLastName("Smith");
        customer.setAddress1("22 Melo Road");
        customer.setAddress2("apt1");
        customer.setCity("New Brunswick");
        customer.setState("New Jersey");
        customer.setPostalCode(07444);
        customer.setCountry("United States");
        customer.setPhone("111-222-3456");
        customer.setCompany("BigCo");
        customer.setEmail("joe.smith@email.org");

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
        customer2.setFirstName("Sarah");
        customer2.setLastName("Johnson");

        customer2.setPhone("973-298-0913");
        customer2.setEmail("sarah.johnson@vangaurd.com");

        customer2.setAddress1("514 Saxton Avenue");
        customer2.setAddress2("Apt 9B");
        customer2.setCity("Houston");
        customer2.setState("Texas");
        customer2.setPostalCode(97192);
        customer2.setCountry("United States");
        customer2.setCompany("Vanguard");
        customerRepo.save(customer2);



        Customer customer3 = new Customer();
        customer3.setFirstName("Daniel");
        customer3.setLastName("Smith");

        customer3.setPhone("973-298-0913");
        customer3.setEmail("daniel.smith@vangaurd.com");

        customer3.setAddress1("4 Main Street");
        customer3.setCity("Dallas");
        customer3.setState("Texas");
        customer3.setPostalCode(39012);
        customer3.setCountry("United States");
        customer3.setCompany("Vanguard");

        customerRepo.save(customer3);

        List<Customer> texasList = customerRepo.findByState("Texas");
        assertEquals(texasList.size(),2);



    }
    @Test
    @Transactional
    public void shouldFindByID(){
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

        customer = customerRepo.save(customer);

        Customer fromRepo = customerRepo.findById(customer.getId()).get();
        assertEquals(customer,fromRepo);
    }
}
