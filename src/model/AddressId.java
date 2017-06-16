package model;

/**
 * Created by 28713 on 2017/6/15.
 */
public class AddressId {
    private int id;
    private String email;

    public AddressId(int id, String email) {
        this.id = id;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }
}
