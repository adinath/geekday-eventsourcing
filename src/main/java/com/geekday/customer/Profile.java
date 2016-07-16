package com.geekday.customer;

public class Profile {
    private final String name;
    private final String email;
    private final String address;

    public Profile() {
        this(null, null);
    }

    public Profile(String name, String address) {
        this.email = "test@test.com";
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

}
