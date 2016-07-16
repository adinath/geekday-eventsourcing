package com.geekday.events;


public class CustomerCreated {
    private final String name;
    private final String address;

    public CustomerCreated() {
        this(null, null);
    }

    public CustomerCreated(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CustomerCreated that = (CustomerCreated) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return address != null ? address.equals(that.address) : that.address == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CustomerCreated{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
