package com.geekday.customer;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CustomerRepositoryTest {

    private CustomerRepository repository;

    @Before
    public void setUp() throws Exception {
        CustomerRepository.initialize();
        repository = new CustomerRepository();
    }

    @Test
    public void shouldSaveCustomer() throws Exception {
        repository.save(new Customer("name", "address"));
        Customer c = repository.get("name");
        assertEquals("name", c.getName());
        assertEquals("address", c.getAddress());
    }


}