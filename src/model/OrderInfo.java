package model;

import java.util.List;

/**
 * Created by peter on 2017/6/16.
 */
public class OrderInfo {
    private Order order;
    private Address address;
    private List<OrderSum> ordersum;

    public OrderInfo(Order order, Address address, List<OrderSum> ordersum) {
        this.order = order;
        this.address = address;
        this.ordersum = ordersum;
    }

    public Order getOrder() {
        return order;
    }

    public Address getAddress() {
        return address;
    }

    public List<OrderSum> getOrdersum() {
        return ordersum;
    }
}
