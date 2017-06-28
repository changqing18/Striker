package model;

/**
 * Created by peter on 2017/6/28.
 */
public class CartShow {
   private Good good;
   private int number;

    public CartShow(Good good, int number) {
        this.good = good;
        this.number = number;
    }

    public Good getGood() {
        return good;
    }

    public int getNumber() {
        return number;
    }
}
