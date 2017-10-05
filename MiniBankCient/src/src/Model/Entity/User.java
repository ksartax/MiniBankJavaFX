/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.Model.Entity;

import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 * @author Damian Stępniak
 */
public class User implements Serializable {

    private int user_id;

    private Address_User address_user_id;

    private Contact_User contact_user_id;

    private Finance_Account_User finance_account_user_id;

    private String address_ip;

    private String firstName;

    private String lastName;

    private String password;

    private String email;

    private String protect_code;

    private int active;

    private LocalDate joiningData;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public Address_User getAddress_user_id() {
        if(address_user_id == null){
            return new Address_User();
        }
        return address_user_id;
    }

    public void setAddress_user_id(Address_User address_user_id) {
        this.address_user_id = address_user_id;
    }

    public Contact_User getContact_user_id() {
        if(contact_user_id == null){
            return new Contact_User();
        }
        return contact_user_id;
    }

    public void setContact_user_id(Contact_User contact_user_id) {
        this.contact_user_id = contact_user_id;
    }

    public Finance_Account_User getFinance_account_user_id() {
        if(finance_account_user_id == null){
            return new Finance_Account_User();
        }
        return finance_account_user_id;
    }

    public void setFinance_account_user_id(Finance_Account_User finance_account_user_id) {
        this.finance_account_user_id = finance_account_user_id;
    }

    public String getAddress_ip() {
        if(address_ip == null){
            return " ";
        }
        return address_ip;
    }

    public void setAddress_ip(String address_ip) {
        this.address_ip = address_ip;
    }

    public String getFirstName() {
        if(firstName == null){
            return  " ";
        }
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        if(lastName == null){
            return " ";
        }
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        if(lastName == null){
            return " ";
        }
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        if(email == " "){
            return " ";
        }
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProtect_code() {
        if(protect_code == null){
            return " ";
        }
        return protect_code;
    }

    public void setProtect_code(String protect_code) {
        this.protect_code = protect_code;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public LocalDate getJoiningData() {
        return joiningData;
    }

    public void setJoiningData(LocalDate joiningData) {
        this.joiningData = joiningData;
    }

}
