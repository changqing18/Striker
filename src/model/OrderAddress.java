package model;

/**
 * Created by peter on 2017/6/16.
 */
public class OrderAddress {
    private Order order;
    private Address address;

    public OrderAddress(Order order, Address address) {
        this.order = order;
        this.address = address;
    }

    public Order getOrder() {
        return order;
    }

    public Address getAddress() {
        return address;
    }
}
