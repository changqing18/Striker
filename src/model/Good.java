package model;

/**
 * Created by 28713 on 2017/6/15.
 */
public class Good {
    private int id;
    private String name;
    private String cover;
    private double price;
    private int stock;
    private int type;
    private String des;

    public Good(int id, String name, String cover, double price, int stock, int type, String des) {
        this.id = id;
        this.name = name;
        this.cover = cover;
        this.price = price;
        this.stock = stock;
        this.type = type;
        this.des = des;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getStock() {
        return stock;
    }

    public int getType() {
        return type;
    }

    public String getDes() {
        return des;
    }
}
