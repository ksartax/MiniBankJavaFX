/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import javafx.application.Application;
import javafx.stage.Stage;
import src.Controller.LoginController;
import src.Model.Authorizate;
import src.Model.AuthorizeInterface;
import src.Model.MenuInterface;
import src.Model.MenuManager;

/**
 *
 * @author Damian Stępniak
 */
public class MiniBankCient extends Application {

    private MenuInterface menuInterface;

    @Override
    public void start(Stage stage) throws Exception {

        menuInterface = new MenuManager();
        menuInterface.setState(stage);
        menuInterface.setTitle("Bank");
        menuInterface.setResource("login");

        LoginController controller = (LoginController) menuInterface.getResource().getController();
        controller.setPrevStage(menuInterface);

        menuInterface.startStage();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
