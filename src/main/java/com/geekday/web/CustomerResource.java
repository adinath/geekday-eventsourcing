package com.geekday.web;


import com.geekday.common.DomainEventPublisher;
import com.geekday.customer.Customer;
import com.geekday.customer.CustomerService;
import com.geekday.customer.Profile;
import com.geekday.events.CustomerCreated;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.net.URI;
import java.util.Optional;

@Path("/")
public class CustomerResource {
    @POST
    @Path("customer")
    public Response createCustomer(@FormParam("name") String name, @FormParam("address") String address) {
        System.out.println("creating customer");
        try {
            new DomainEventPublisher().publish(new CustomerCreated(name, address));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Response.created(URI.create("/profile/" + name)).build();
    }

    @GET
    @Path("profile/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Profile profile(@PathParam("name") String name) {
        Optional<Customer> customer = new CustomerService().getCustomerByName(name);

        Optional<Profile> profile = customer.map(cust -> new Profile(cust.getName(), cust.getAddress()));

        return profile.get();
    }
}