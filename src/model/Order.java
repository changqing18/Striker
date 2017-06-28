package model;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by peter on 2017/6/8.
 */
public class Order {
    private int orderid;
    private int addressid;
    private String payment;
    private boolean payornot;
    private String email;
    private String memo;
    private Timestamp dat;
    private boolean tag;
    private double sumprice;

    public Order(int orderid, int addressid, String payment, boolean payornot, String email, String memo, Timestamp dat, boolean tag, double sumprice) {
        this.orderid = orderid;
        this.addressid = addressid;
        this.payment = payment;
        this.payornot = payornot;
        this.email = email;
        this.memo = memo;
        this.dat = dat;
        this.tag = tag;
        this.sumprice = sumprice;
    }

    public int getOrderid() {
        return orderid;
    }

    public int getAddressid() {
        return addressid;
    }

    public String getPayment() {
        return payment;
    }

    public boolean isPayornot() {
        return payornot;
    }

    public String getEmail() {
        return email;
    }

    public String getMemo() {
        return memo;
    }

    public Timestamp dat() {
        return dat;
    }

    public boolean isTag() {
        return tag;
    }

    public double getSumprice() {
        return sumprice;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }
}
