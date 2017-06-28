package model;

/**
 * Created by peter on 2017/6/16.
 */
public class OrderSum {
    private int goodid;
    private int count;
    private double sprice;
    private int orderid;

    public OrderSum(int goodid, int count, double sprice, int orderid) {
        this.goodid = goodid;
        this.count = count;
        this.sprice = sprice;
        this.orderid = orderid;
    }

    public int getGoodid() {
        return goodid;
    }

    public int getCount() {
        return count;
    }

    public int getOrderid() {
        return orderid;
    }

    public double getSprice() {
        return sprice;
    }
}
