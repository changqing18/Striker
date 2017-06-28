package model;

/**
 * Created by peter on 2017/6/28.
 */
public class Cart {
    private String email;
    private int goodid;
    private int number;

    public Cart(String email, int goodid, int number) {
        this.email = email;
        this.goodid = goodid;
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public int getGoodid() {
        return goodid;
    }

    public int getNumber() {
        return number;
    }
}
