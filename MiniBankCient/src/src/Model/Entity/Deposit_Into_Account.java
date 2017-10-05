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
public class Deposit_Into_Account {

    private int deposit_into_account_id;

    private float price;

    private Finance_Account_User finance_account_user_id;

    private Date createDate;

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public int getDeposit_into_account_id() {
        return deposit_into_account_id;
    }

    public void setDeposit_into_account_id(int deposit_into_account_id) {
        this.deposit_into_account_id = deposit_into_account_id;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Finance_Account_User getFinance_account_user_id() {
        return finance_account_user_id;
    }

    public void setFinance_account_user_id(Finance_Account_User finance_account_user_id) {
        this.finance_account_user_id = finance_account_user_id;
    }

}
