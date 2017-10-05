/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.Model.Entity;

import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author Damian Stępniak
 */
public class Remove_Into_Account {

    private int remove_into_account_id;

    private float price;

    private Date createDate;

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    private Finance_Account_User finance_account_user;

    public int getRemove_into_account_id() {
        return remove_into_account_id;
    }

    public void setRemove_into_account_id(int remove_into_account_id) {
        this.remove_into_account_id = remove_into_account_id;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Finance_Account_User getFinance_account_user() {
        return finance_account_user;
    }

    public void setFinance_account_user(Finance_Account_User finance_account_user) {
        this.finance_account_user = finance_account_user;
    }

}
