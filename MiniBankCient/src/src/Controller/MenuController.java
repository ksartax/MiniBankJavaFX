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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javax.swing.JOptionPane;
import src.Model.Authorizate;
import src.Model.AuthorizeInterface;
import src.Model.Entity.User;
import src.Model.MenuInterface;
import src.Model.transport.MappingUserProfil;
import src.Model.transport.RequestPost;

/**
 * FXML Controller class
 *
 * @author Damian Stępniak
 */
public class MenuController implements Initializable {

    private MenuInterface menuInterface;

    @FXML
    private Label accountNumber;
    
    @FXML
    private Label accountMoney;
    
    public void setPrevStage(MenuInterface menuInterface) {
        this.menuInterface = menuInterface;
    }
    
    private Authorizate authorizeInterface;
    
    @FXML
    public void logoutButton() throws IOException {
        menuInterface.closeStage();
        menuInterface.setTitle("Logowanie");
        menuInterface.setResource("login");
        LoginController controller = (LoginController) menuInterface.getResource().getController();
        controller.setPrevStage(menuInterface);
        menuInterface.startStage();
    }

    @FXML
    public void profilButton() throws IOException {
        menuInterface.closeStage();
        menuInterface.setTitle("Profil");
        menuInterface.setResource("profil");
        ProfilController controller = (ProfilController) menuInterface.getResource().getController();
        controller.setPrevStage(menuInterface);
        menuInterface.startStage();
    }

    @FXML
    public void toTransactionButton() throws IOException {
        menuInterface.closeStage();
        menuInterface.setTitle("Transakcje Przychodzące");
        menuInterface.setResource("toTransaction");
        ToTransactionController controller = (ToTransactionController) menuInterface.getResource().getController();
        controller.setPrevStage(menuInterface);
        menuInterface.startStage();
    }

    @FXML
    public void fromTransactionButton() throws IOException {
        menuInterface.closeStage();
        menuInterface.setTitle("Transakcje Wychodzące");
        menuInterface.setResource("fromTransaction");
        FromTransactionController controller = (FromTransactionController) menuInterface.getResource().getController();
        controller.setPrevStage(menuInterface);
        menuInterface.startStage();
    }

    @FXML
    public void removeMoneyButton() throws IOException {
        menuInterface.closeStage();
        menuInterface.setTitle("Wypłaty Z Konta");
        menuInterface.setResource("removeMoney");
        RemoveMoneyController controller = (RemoveMoneyController) menuInterface.getResource().getController();
        controller.setPrevStage(menuInterface);
        menuInterface.startStage();
    }

    @FXML
    public void receiveMoneyButton() throws IOException {
        menuInterface.closeStage();
        menuInterface.setTitle("Epłaty Na Konto");
        menuInterface.setResource("responseMoney");
        ResponseMoneyController controller = (ResponseMoneyController) menuInterface.getResource().getController();
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
            requestPost.InitPost("http://localhost:8080/api/user/profile/profile");
            System.out.println("T : " + this.authorizeInterface.getToken());
            requestPost.setRequestProperty("Authorization", "Basic " + this.authorizeInterface.getToken());
            requestPost.sendData("{\n"
                    + "	\"login\":\"Seba\"\n"
                    + "}");

            MappingUserProfil http = new MappingUserProfil();
            User user = http.mappeUser(requestPost.getResponse());
            
            accountNumber.setText(accountNumber.getText() + user.getFinance_account_user_id().getBank_account_number());
            accountMoney.setText(accountMoney.getText() + user.getFinance_account_user_id().getGrandtotal());
           
        } catch (IOException ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
