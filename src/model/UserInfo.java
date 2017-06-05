package model;

/**
 * Created by 28713 on 2017/5/31.
 */
public class UserInfo {
    private String name;
    private String email;
    private String password;

    public UserInfo(String name, String email, String password) {

        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
