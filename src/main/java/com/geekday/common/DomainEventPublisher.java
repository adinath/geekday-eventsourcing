package com.geekday.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.geekday.EventStore;
import com.geekday.messaging.Producer;

import java.io.IOException;

public class DomainEventPublisher {
    private Producer producer = Producer.getInstance();
    private EventStore eventStore = EventStore.initialize();
    private ObjectMapper mapper = new ObjectMapper();

    public void publish(Object event) throws IOException {
        String json = mapper.writeValueAsString(event);
        String eventType = event.getClass().getSimpleName();
        DomainEvent eventMessage = new DomainEvent(eventType, json);

        System.out.println(String.format("Adding event {} in event store", event));
        eventStore.addEvent(eventMessage);
        producer.publish(eventType, json);
    }
}
