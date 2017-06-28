package model;

import java.util.List;

/**
 * Created by peter on 2017/6/27.
 */
public class MakeOrderModel {
    private Good good;
    private List<Address> addressList;

    public MakeOrderModel(Good good, List<Address> addressList) {
        this.good = good;
        this.addressList = addressList;
    }

    public Good getGood() {
        return good;
    }

    public List<Address> getAddressList() {
        return addressList;
    }
}
