package model;

/**
 * Created by 28713 on 2017/6/1.
 */
public class Address {
    private String email;
    private String name;
    private int phone;
    private int postcode;
    private String province;
    private String city;
    private String county;
    private String detail;

    public Address(String email, String name, int phone, int postcode,
                   String province, String city, String county, String detail) {
        this.email = email;
        this.name = name;

        this.phone = phone;
        this.postcode = postcode;
        this.province = province;
        this.city = city;
        this.county = county;
        this.detail = detail;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public int getPhone() {
        return phone;
    }

    public int getPostcode() {
        return postcode;
    }

    public String getProvince() {
        return province;
    }

    public String getCity() {
        return city;
    }

    public String getCounty() {
        return county;
    }

    public String getDetail() {
        return detail;
    }
}
