package com.geekday.common;

import com.geekday.CustomerCreatedSubscriber;

public class Listeners {
    public static void initialize() {
        new CustomerCreatedSubscriber();
    }
}
