/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import src.Model.Authorizate;
import src.Model.AuthorizeInterface;
import src.Model.Entity.Deposit_Into_Account;
import src.Model.Entity.Finance_Account_User;
import src.Model.Entity.From_Bank_Account_Transaction;
import src.Model.MenuInterface;
import src.Model.transport.MappingDeposit;
import src.Model.transport.MappingToBankAccountTransaction;
import src.Model.transport.ObjectToJson;
import src.Model.transport.RequestPost;

/**
 * FXML Controller class
 *
 * @author Damian Stępniak
 */
public class ResponseMoneyController implements Initializable {

    private Authorizate authorizeInterface;

    private MenuInterface menuInterface;

    @FXML
    private TableView<Object> tableView;

    public void setPrevStage(MenuInterface menuInterface) {
        this.menuInterface = menuInterface;
    }

    @FXML
    private TextField moneyToSendTextField;

    @FXML
    public void responseMoneyButton() {
        try {

            if (!moneyToSendTextField.getText().equals("")) {
                Deposit_Into_Account deposit_Into_Account = new Deposit_Into_Account();
                deposit_Into_Account.setPrice(Float.parseFloat(moneyToSendTextField.getText()));
                deposit_Into_Account.setCreateDate(new Date());

                RequestPost requestPost = new RequestPost();
                requestPost.InitPost("http://localhost:8080/api/user/transaction/depositMoney");
                requestPost.setRequestProperty("Authorization", "Basic " + this.authorizeInterface.getToken());
                requestPost.sendData(new ObjectToJson<Deposit_Into_Account>().objectToJson(deposit_Into_Account));

                if (requestPost.getResponse().indexOf("success") >= 0) {
                    ObservableList<Object> data = tableView.getItems();
                    data.add(new TableData(
                            String.valueOf(moneyToSendTextField.getText()),
                            deposit_Into_Account.getCreateDate().toLocaleString()
                    ));
                }
            }

        } catch (IOException ex) {
            Logger.getLogger(ResponseMoneyController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void returnMenuStage() throws IOException {
        menuInterface.closeStage();
        menuInterface.setTitle("Cos");
        menuInterface.setResource("menu");
        MenuController controller = (MenuController) menuInterface.getResource().getController();
        controller.setPrevStage(menuInterface);
        menuInterface.startStage();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {

            this.authorizeInterface = new Authorizate();
            authorizeInterface.setUrl();

            RequestPost requestPost = new RequestPost();
            requestPost.InitPost("http://localhost:8080/api/user/finance/listDeposit");
            requestPost.setRequestProperty("Authorization", "Basic " + this.authorizeInterface.getToken());
            requestPost.sendData("{\n"
                    + "	\"login\":\"Seba\"\n"
                    + "}");

            MappingDeposit http = new MappingDeposit();
            Finance_Account_User fbat = http.mappeUser(requestPost.getResponse());

            ObservableList<Object> data = tableView.getItems();

            if (fbat.getDeposit_into_account() != null) {
                for (Deposit_Into_Account object : fbat.getDeposit_into_account()) {
                    data.add(new TableData(
                            String.valueOf(object.getPrice()),
                            object.getCreateDate().toLocaleString()
                    ));
                }
            }

        } catch (IOException ex) {
            Logger.getLogger(FromTransactionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static class TableData {

        private final SimpleStringProperty lastName;
        private final SimpleStringProperty firstName;

        private TableData(String lName, String data) {
            this.firstName = new SimpleStringProperty(data);
            this.lastName = new SimpleStringProperty(lName);
        }

        public String getLastName() {
            return lastName.get();
        }

        public void setLastName(String fName) {
            lastName.set(fName);
        }

        public String getFirstName() {
            return firstName.get();
        }

        public void seFirstName(String fName) {
            firstName.set(fName);
        }
    }

}
