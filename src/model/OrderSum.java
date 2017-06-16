package model;

/**
 * Created by peter on 2017/6/16.
 */
public class OrderSum {
    private int goodid;
    private int count;
    private int orderid;
    private double sprice;

    public OrderSum(int goodid, int count, int orderid, double sprice) {
        this.goodid = goodid;
        this.count = count;
        this.orderid = orderid;
        this.sprice = sprice;
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
