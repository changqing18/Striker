package model;

/**
 * Created by 28713 on 2017/6/14.
 */
public class GoodInfo {
    private int id;
    private String name;
    private String cover;
    private double price;

    public GoodInfo(int id, String name, String cover, double price) {
        this.id = id;
        this.name = name;
        this.cover = cover;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCover() {
        return cover;
    }

    public double getPrice() {
        return price;
    }
}
