package com.geekday;


import com.geekday.common.DomainEventSubscriber;
import com.geekday.customer.CustomerService;
import com.geekday.events.CustomerCreated;

public class CustomerCreatedSubscriber extends DomainEventSubscriber<CustomerCreated> {

    public CustomerCreatedSubscriber() {
        super("CustomerCreated");
    }

    @Override
    public void on(CustomerCreated event) {
        CustomerService customerService = new CustomerService();
        customerService.newCustomer(event.getName(), event.getAddress());
    }
}
