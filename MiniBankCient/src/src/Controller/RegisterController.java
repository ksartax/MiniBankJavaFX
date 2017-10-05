/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.Controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import src.Model.AuthorizeInterface;
import src.Model.Entity.Address_User;
import src.Model.Entity.Contact_User;
import src.Model.Entity.Finance_Account_User;
import src.Model.Entity.User;
import src.Model.ImageInterface;
import src.Model.ImageManager;
import src.Model.MenuInterface;
import src.Model.transport.ObjectToJson;
import src.Model.transport.RequestPost;

/**
 * FXML Controller class
 *
 * @author Damian Stępniak
 */
public class RegisterController implements Initializable {

    private AuthorizeInterface authorizeInterface;

    private ObjectToJson<User> uJson;

    public void setAuthorize(AuthorizeInterface authorizeInterface) {
        this.authorizeInterface = authorizeInterface;
    }

    @FXML
    private ImageView logo;

    @FXML
    private Text actiontarget;

    @FXML
    private Label errorField;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField passwordField;

    private ImageInterface im;

    private MenuInterface menuInterface;

    public void setPrevStage(MenuInterface menuInterface) {
        this.menuInterface = menuInterface;
    }

    @FXML
    public void registerButtonAction() {

        try {
            uJson = new ObjectToJson<>();

            User user = new User();
            user.setFirstName(firstNameField.getText());
            user.setLastName(lastNameField.getText());
            user.setPassword(passwordField.getText());
            user.setEmail(emailField.getText());

            this.sendPost(uJson.objectToJson(user));

            this.goToLoginStage();

        } catch (Exception e) {
            errorField.setText("Błąd rejestracji");
        }

    }

    @FXML
    public void redirectLoginButton() throws IOException {
        this.goToLoginStage();
    }

    private void sendPost(String data) throws IOException {
        RequestPost requestPost = new RequestPost();
        requestPost.InitPost("http://localhost:8080/api/register");
        requestPost.sendData(data);
        requestPost.getResponse();
    }

    private void goToLoginStage() throws IOException {
        menuInterface.closeStage();
        menuInterface.setTitle("Logowanie");
        menuInterface.setResource("login");
        LoginController controller = (LoginController) menuInterface.getResource().getController();
        controller.setPrevStage(menuInterface);
        menuInterface.startStage();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            im = new ImageManager();
            logo.setImage(new Image(im.getLogo()));
        } catch (FileNotFoundException ex) {
            System.err.println(ex.getMessage());
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

}
