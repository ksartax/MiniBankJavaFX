/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.Model.Entity;

/**
 *
 * @author Damian Stępniak
 */
public class Address_User {

    private int address_user_id;

    private String city;

    private String country;

    private String post_code;

    private String street;

    private String home_nr;

    public int getAddress_user_id() {
        return address_user_id;
    }

    public void setAddress_user_id(int address_user_id) {
        this.address_user_id = address_user_id;
    }

    public String getCity() {
        if (city == null) {
            return " ";
        }
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        if (country == null) {
            return " ";
        }
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPost_code() {
        if (post_code == null) {
            return " ";
        }
        return post_code;
    }

    public void setPost_code(String post_code) {
        this.post_code = post_code;
    }

    public String getStreet() {
        if (street == null) {
            return " ";
        }
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHome_nr() {
        if (home_nr == null) {
            return " ";
        }
        return home_nr;
    }

    public void setHome_nr(String home_nr) {
        this.home_nr = home_nr;
    }

}
