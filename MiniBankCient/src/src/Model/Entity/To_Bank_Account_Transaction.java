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
public class To_Bank_Account_Transaction {

    private int to_bank_account_transaction_id;

    private Finance_Account_User to_finance_account_user_id;

    private float price;

    private Finance_Account_User finance_account_user_id;

    private String pom;

    public String getPom() {
        return pom;
    }
private Date createDate;

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    public void setPom(String pom) {
        this.pom = pom;
    }
    
    public int getTo_bank_account_transaction_id() {
        return to_bank_account_transaction_id;
    }

    public void setTo_bank_account_transaction_id(int to_bank_account_transaction_id) {
        this.to_bank_account_transaction_id = to_bank_account_transaction_id;
    }

    public Finance_Account_User getTo_finance_account_user_id() {
        return to_finance_account_user_id;
    }

    public void setTo_finance_account_user_id(Finance_Account_User to_finance_account_user_id) {
        this.to_finance_account_user_id = to_finance_account_user_id;
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
