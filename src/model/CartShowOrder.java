package model;

import java.util.List;

/**
 * Created by peter on 2017/6/29.
 */
public class CartShowOrder {
    private List<CartShow> cartShows;
    private List<Address> addresslist;

    public CartShowOrder(List<CartShow> cartShows, List<Address> addresslist) {
        this.cartShows = cartShows;
        this.addresslist = addresslist;
    }

    public List<CartShow> getCartShows() {
        return cartShows;
    }

    public List<Address> getAddresslist() {
        return addresslist;
    }
}
