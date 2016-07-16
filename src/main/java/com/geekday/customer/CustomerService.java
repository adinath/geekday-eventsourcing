package com.geekday.customer;

import java.util.Optional;

public class CustomerService {
    public void newCustomer(String name, String address) {
        System.out.println("saving customer " + name);
        saveCustomer(new Customer(name, address));
    }


    private void saveCustomer(Customer customer) {
        CustomerRepository repository = new CustomerRepository();
        repository.save(customer);
    }

    public Optional<Customer> getCustomerByName(String name) {
        CustomerRepository customers = new CustomerRepository();
        return Optional.ofNullable(customers.get(name));
    }
}
