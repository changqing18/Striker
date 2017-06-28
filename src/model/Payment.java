package model;

/**
 * Created by peter on 2017/6/28.
 */
public class Payment {
    private String payment;
    private int orderid;

    public Payment(String payment, int orderid) {
        this.payment = payment;
        this.orderid = orderid;
    }

    public String getPayment() {
        return payment;
    }

    public int getGoodid() {
        return orderid;
    }
}
