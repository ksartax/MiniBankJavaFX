/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import src.Model.Authorizate;
import src.Model.AuthorizeInterface;
import src.Model.Entity.Finance_Account_User;
import src.Model.Entity.From_Bank_Account_Transaction;
import src.Model.Entity.To_Bank_Account_Transaction;
import src.Model.Entity.User;
import src.Model.MenuInterface;
import src.Model.transport.MappingFromBankAccountTransaction;
import src.Model.transport.MappingToBankAccountTransaction;
import src.Model.transport.MappingUserProfil;
import src.Model.transport.ObjectToJson;
import src.Model.transport.RequestPost;

/**
 * FXML Controller class
 *
 * @author Damian Stępniak
 */
public class ToTransactionController implements Initializable {

    private Authorizate authorizeInterface;

    private MenuInterface menuInterface;

    @FXML
    private TableView<Object> tableView;

    @FXML
    private TableView<Object> tableViewUser;

    public void setPrevStage(MenuInterface menuInterface) {
        this.menuInterface = menuInterface;
    }

    @FXML
    private TextField accountNumberSendTextField;

    @FXML
    private Label errorField;

    @FXML
    private TextField moneyToSendTextField;

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
    public void sendMoneyButton() {
        try {

            if (!moneyToSendTextField.getText().equals("")) {
                To_Bank_Account_Transaction deposit_Into_Account = new To_Bank_Account_Transaction();
                deposit_Into_Account.setPrice(Float.parseFloat(moneyToSendTextField.getText()));
                deposit_Into_Account.setPom(accountNumberSendTextField.getText());
                deposit_Into_Account.setCreateDate(new Date());

                RequestPost requestPost = new RequestPost();
                requestPost.InitPost("http://localhost:8080/api/user/transaction/sendMoney");
                requestPost.setRequestProperty("Authorization", "Basic " + this.authorizeInterface.getToken());
                requestPost.sendData(new ObjectToJson<To_Bank_Account_Transaction>().objectToJson(deposit_Into_Account));

                if (requestPost.getResponse().indexOf("success") >= 0) {
                    ObservableList<Object> data = tableView.getItems();
                    data.add(new TableData(
                            String.valueOf(accountNumberSendTextField.getText()),
                            String.valueOf(moneyToSendTextField.getText()),
                            deposit_Into_Account.getCreateDate().toLocaleString()
                    ));
                }

            }

            errorField.setText("");
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            errorField.setText("Błąd");
        } catch (java.lang.RuntimeException e) {
            System.err.println(e.getMessage());
            errorField.setText("Błąd");
        } catch (Exception E) {
            System.err.println(E.getMessage());
            errorField.setText("Błąd");
        }
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
            requestPost.InitPost("http://localhost:8080/api/user/finance/listToBankAccountTransaction");
            System.out.println("T : " + this.authorizeInterface.getToken());
            requestPost.setRequestProperty("Authorization", "Basic " + this.authorizeInterface.getToken());
            requestPost.sendData("{\n"
                    + "	\"login\":\"Seba\"\n"
                    + "}");

            MappingToBankAccountTransaction http = new MappingToBankAccountTransaction();
            Finance_Account_User fbat = http.mappeUser(requestPost.getResponse());

            ObservableList<Object> data = tableView.getItems();

            if (fbat.getTo_bank_account_transaction() != null) {
                for (To_Bank_Account_Transaction object : fbat.getTo_bank_account_transaction()) {
                    data.add(new TableData(
                            String.valueOf(object.getTo_finance_account_user_id().getBank_account_number()),
                            String.valueOf(object.getPrice()),
                            object.getCreateDate().toLocaleString()
                    ));
                }
            }

            RequestPost requestPostUser = new RequestPost();
            requestPostUser.InitPost("http://localhost:8080/api/user/profile/list");
            requestPostUser.setRequestProperty("Authorization", "Basic " + this.authorizeInterface.getToken());
            requestPostUser.sendData("{\n"
                    + "	\"login\":\"Seba\"\n"
                    + "}");

            MappingUserProfil httpUser = new MappingUserProfil();
            List<User> users = (List<User>) httpUser.mappeUsers(requestPostUser.getResponse());

            ObservableList<Object> dataUser = tableViewUser.getItems();

            for (User object : users) {
                System.err.println(object.getFinance_account_user_id().getBank_account_number());
                dataUser.add(new TableDataUsers(
                        String.valueOf(object.getFirstName()),
                        String.valueOf(object.getLastName()),
                        String.valueOf(object.getFinance_account_user_id().getBank_account_number())
                ));
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

        public String getFirstName() {
            return firstName.get();
        }

        public void setFirstName(String fName) {
            firstName.set(fName);
        }

        public String getData() {
            return data.get();
        }

        public void setData(String fName) {
            data.set(fName);
        }

        public String getLastName() {
            return lastName.get();
        }

        public void setLastName(String fName) {
            lastName.set(fName);
        }

    }

    public static class TableDataUsers {

        private final SimpleStringProperty firstName;
        private final SimpleStringProperty lastName;
        private final SimpleStringProperty accoutNr;

        private TableDataUsers(String fName, String lName, String accountNr) {
            this.firstName = new SimpleStringProperty(fName);
            this.lastName = new SimpleStringProperty(lName);
            this.accoutNr = new SimpleStringProperty(accountNr);
        }

        public String getFirstName() {
            return firstName.get();
        }

        public void setFirstName(String fName) {
            firstName.set(fName);
        }

        public String getAccoutNr() {
            return accoutNr.get();
        }

        public String getLastName() {
            return lastName.get();
        }

        public void setLastName(String fName) {
            lastName.set(fName);
        }

    }

}
