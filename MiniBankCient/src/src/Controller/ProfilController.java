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
import javafx.scene.control.TextField;
import src.Model.Authorizate;
import src.Model.AuthorizeInterface;
import src.Model.Entity.User;
import src.Model.MenuInterface;
import src.Model.transport.MappingUserProfil;
import src.Model.transport.ObjectToJson;
import src.Model.transport.RequestPost;

/**
 * FXML Controller class
 *
 * @author Damian Stępniak
 */
public class ProfilController implements Initializable {

    private MenuInterface menuInterface;

    private Authorizate authorizeInterface;

     private ObjectToJson<User> uJson;
    
    private User user;

    @FXML
    private TextField firstNameTextField;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField telephoneTextField;

    @FXML
    private TextField cityTextField;

    @FXML
    private TextField countryTextField;

    @FXML
    private TextField homeNrTextField;

    @FXML
    private TextField postCodeTextField;

    @FXML
    private TextField streetTextField;

    @FXML
    private TextField bankAccountTextField;

    @FXML
    private TextField availableAccountMoney;

    //  @FXML
    //  private TextField spendMonthAccountMoney;
    //  @FXML
    //  private TextField receiveMonthAccountMoney;
    public void setPrevStage(MenuInterface menuInterface) {
        this.menuInterface = menuInterface;
    }

    @FXML
    public void returnMenuStage() throws IOException {
        menuInterface.closeStage();
        menuInterface.setTitle("Shop Management");
        menuInterface.setResource("menu");
        MenuController controller = (MenuController) menuInterface.getResource().getController();
        controller.setPrevStage(menuInterface);
        menuInterface.startStage();
    }

    @FXML
    public void updateProfil() {
            
        try {
            uJson = new ObjectToJson<>();
        
            user.getAddress_user_id().setCity(this.cityTextField.getText());
            user.getAddress_user_id().setStreet(this.streetTextField.getText());
            user.getAddress_user_id().setPost_code(this.postCodeTextField.getText());
            user.getAddress_user_id().setCountry(this.countryTextField.getText());
            user.getAddress_user_id().setHome_nr(this.homeNrTextField.getText());
            user.getContact_user_id().setTelephone(user.getContact_user_id().getTelephone());
            
            System.out.println(uJson.objectToJson(user));
            this.sendPost(uJson.objectToJson(user));
            
        } catch (IOException ex) {
            Logger.getLogger(ProfilController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void sendPost(String data) throws IOException {
        RequestPost requestPost = new RequestPost();
        requestPost.InitPost("http://localhost:8080/api/user/profile/update");
        requestPost.setRequestProperty("Authorization", "Basic " + this.authorizeInterface.getToken());
        requestPost.sendData(data);
        requestPost.getResponse();
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
            user = http.mappeUser(requestPost.getResponse());

            this.emailTextField.setText(user.getEmail());
            this.bankAccountTextField.setText(user.getFinance_account_user_id().getBank_account_number());
            this.cityTextField.setText(user.getAddress_user_id().getCity());
            this.firstNameTextField.setText(user.getFirstName());
            this.lastNameTextField.setText(user.getLastName());
            this.countryTextField.setText(user.getAddress_user_id().getCountry());
            this.streetTextField.setText(user.getAddress_user_id().getStreet());
            this.postCodeTextField.setText(user.getAddress_user_id().getPost_code());
            this.telephoneTextField.setText((String.valueOf(user.getContact_user_id().getTelephone())));
            this.homeNrTextField.setText(user.getAddress_user_id().getHome_nr());
            this.availableAccountMoney.setText(String.valueOf(user.getFinance_account_user_id().getGrandtotal()));

        } catch (Exception ex) {
            Logger.getLogger(ProfilController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
