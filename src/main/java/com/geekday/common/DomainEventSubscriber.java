package com.geekday.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.geekday.messaging.Consumer;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;

public abstract class DomainEventSubscriber<T> {

    private final ObjectMapper mapper;
    private final Class<T> eventType;
    private Consumer consumer;
    private String topic;

    protected DomainEventSubscriber(String topic) {
        mapper = new ObjectMapper();
        this.eventType = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
        this.topic = topic;
        this.consumer = new Consumer(topic);
        Thread listenerThread = new Thread(new Listener());
        listenerThread.start();
    }

    class Listener implements Runnable {

        @Override
        public void run() {
            while (true) {
                try {
                    on(receive());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public abstract void on(T event);

    protected T receive() throws IOException {
        String content = consumer.readMessage();
        return mapper.readValue(content, eventType);
    }

}
