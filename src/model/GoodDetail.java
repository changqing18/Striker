package model;

import java.util.List;

/**
 * Created by peter on 2017/6/24.
 */
public class GoodDetail {
    private Good good;
    private List<GoodImage> goodimages;

    public GoodDetail(Good good, List<GoodImage> goodimages) {
        this.good = good;
        this.goodimages = goodimages;
    }

    public Good getGood() {
        return good;
    }

    public List<GoodImage> getGoodimages() {
        return goodimages;
    }
}
