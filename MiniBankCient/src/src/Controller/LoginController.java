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
import src.Model.Authorizate;
import src.Model.AuthorizeInterface;
import src.Model.Entity.User;
import src.Model.ImageInterface;
import src.Model.ImageManager;
import src.Model.MenuInterface;
import src.Model.transport.RequestPost;

/**
 * FXML Controller class
 *
 * @author Damian Stępniak
 */
public class LoginController implements Initializable {

    private User user;

    private Authorizate authorizeInterface;

    @FXML
    private ImageView logo;

    @FXML
    private Text actiontarget;

    @FXML
    private TextField passwordField;

    @FXML
    private Label errorField;

    private ImageInterface im;

    private MenuInterface menuInterface;

    public void setPrevStage(MenuInterface menuInterface) {
        this.menuInterface = menuInterface;
    }

    /**
     * Login action set text login error label if exeption
     */
    @FXML
    public void loginButtonAction() {

        try {

            this.getLogin();

            this.sendPost();

            this.goToMenuStage();

        } catch (IOException e) {
            errorField.setText("Błąd logowania");
        } catch (Exception e) {
            errorField.setText("Błąd logowania");
        }

    }

    private void getLogin() {
        //this.authorizeInterface.clear();
        if (this.passwordField.getText().trim().equals("")) {
            this.authorizeInterface.setPassword("ewqewq");
            this.authorizeInterface.setUsername("ewqewq");
        } else {
            this.authorizeInterface.setPassword(this.passwordField.getText());
            this.authorizeInterface.setUsername(this.passwordField.getText());
        }
    }

    private void sendPost() throws IOException {
        RequestPost requestPost = new RequestPost();
        requestPost.InitPost("http://localhost:8080/api/user/login");
        requestPost.setRequestProperty("Authorization", "Basic " + this.authorizeInterface.getToken());
        requestPost.sendData("");
        System.err.println(requestPost.getResponse());
    }

    private void goToMenuStage() throws IOException {
        menuInterface.closeStage();
        menuInterface.setTitle("Bank Menu");
        menuInterface.setResource("menu");
        MenuController controller = (MenuController) menuInterface.getResource().getController();
        controller.setPrevStage(menuInterface);
        menuInterface.startStage();
    }

    /**
     * Open register form
     */
    @FXML
    public void registerButtonAction() throws IOException {
        menuInterface.closeStage();
        menuInterface.setTitle("Bank Register");
        menuInterface.setResource("register");
        RegisterController controller = (RegisterController) menuInterface.getResource().getController();
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
            this.authorizeInterface.setUrlOutput();
            im = new ImageManager();
            logo.setImage(new Image(im.getLogo()));
        } catch (FileNotFoundException ex) {
            System.err.println(ex.getMessage());
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

}
