package model;

/**
 * Created by peter on 2017/6/28.
 */
public class CartFind {
    private String email;
    private int goodid;

    public CartFind(String email, int goodid) {
        this.email = email;
        this.goodid = goodid;
    }

    public String getEmail() {
        return email;
    }

    public int getGoodid() {
        return goodid;
    }
}
