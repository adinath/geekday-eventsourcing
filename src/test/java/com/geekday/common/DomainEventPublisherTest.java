package com.geekday.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.geekday.events.CustomerCreated;
import com.geekday.messaging.Consumer;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class DomainEventPublisherTest {

    @Test
    public void shouldPublishEvents() throws InterruptedException, IOException {
        //create this before creating sub socket
        DomainEventPublisher domainEventPublisher = new DomainEventPublisher();
        Consumer domainEventSubscriber = new Consumer("CustomerCreated");

        Thread.sleep(2000);//

        CustomerCreated event = new CustomerCreated("name", "address");
        domainEventPublisher.publish(event);


        CustomerCreated customerCreated = new ObjectMapper().readValue(domainEventSubscriber.readMessage(), CustomerCreated.class);

        assertEquals(event, customerCreated);

    }

}