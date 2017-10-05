/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.Controller;

import java.io.IOException;
import java.net.URL;
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
import src.Model.Entity.Finance_Account_User;
import src.Model.Entity.From_Bank_Account_Transaction;
import src.Model.Entity.Remove_Into_Account;
import src.Model.Entity.To_Bank_Account_Transaction;
import src.Model.MenuInterface;
import src.Model.transport.MappingFromBankAccountTransaction;
import src.Model.transport.MappingUserProfil;
import src.Model.transport.ObjectToJson;
import src.Model.transport.RequestPost;

/**
 * FXML Controller class
 *
 * @author Damian Stępniak
 */
public class FromTransactionController implements Initializable {

    private MenuInterface menuInterface;

    private Authorizate authorizeInterface;

    @FXML
    private TableView<Object> tableView;

    public void setPrevStage(MenuInterface menuInterface) {
        this.menuInterface = menuInterface;
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

    @FXML
    public void removeMoneyButton() {

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
            requestPost.InitPost("http://localhost:8080/api/user/finance/listFromBankAccountTransaction");
            requestPost.setRequestProperty("Authorization", "Basic " + this.authorizeInterface.getToken());
            requestPost.sendData("{\n"
                    + "	\"login\":\"Seba\"\n"
                    + "}");

            MappingFromBankAccountTransaction http = new MappingFromBankAccountTransaction();
            Finance_Account_User fbat = http.mappeUser(requestPost.getResponse());

            ObservableList<Object> data = tableView.getItems();

            if (fbat.getFrom_bank_account_transaction() != null) {
                for (From_Bank_Account_Transaction object : fbat.getFrom_bank_account_transaction()) {
                    data.add(new TableData(
                            String.valueOf(object.getTo_finance_account_user_id().getBank_account_number()),
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

        private final SimpleStringProperty firstName;
        private final SimpleStringProperty lastName;
        private final SimpleStringProperty data;

        private TableData(String fName, String lName, String data) {
            this.firstName = new SimpleStringProperty(fName);
            this.lastName = new SimpleStringProperty(lName);
            this.data = new SimpleStringProperty(data);
        }

        public String getData() {
            return data.get();
        }

        public void setData(String fName) {
            data.set(fName);
        }
        
        public String getFirstName() {
            return firstName.get();
        }

        public void setFirstName(String fName) {
            firstName.set(fName);
        }

        public String getLastName() {
            return lastName.get();
        }

        public void setLastName(String fName) {
            lastName.set(fName);
        }

    }

}
