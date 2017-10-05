/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.Model;

import java.io.IOException;
import java.util.Properties;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author Damian Stępniak
 */
public class MenuManager extends MenuLogic implements MenuInterface {

    public MenuManager() throws IOException {
        super();
    }

    @Override
    public void setState(Stage state) {
        super.setStage(state);
    }

    @Override
    public void setTitle(String title) {
        super.setTitle(title);
    }

    @Override
    public void setResource(String resoulce) throws IOException {
        super.setResource(resoulce);
        super.setScene();
    }

    @Override
    public void startStage() {
        super.startStage();
    }

    @Override
    public Stage getStage() {
        return super.getStage();
    }

    @Override
    public FXMLLoader getResource() {
        return super.getMyLoader();
    }

    @Override
    public void closeStage() {
        super.getStage().close();
    }

}
