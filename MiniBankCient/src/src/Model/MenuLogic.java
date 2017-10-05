/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.Model;

import java.io.IOException;
import java.util.Properties;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import src.Controller.MenuController;

/**
 *
 * @author Damian Stępniak
 */
public class MenuLogic extends PropFactory {
    
    private String url = "src/src/model/resources/menu.properties";
    protected Stage stage;
    protected FXMLLoader myLoader;
    protected Pane myPane;
    protected Scene myScene;
    
    protected MenuLogic() throws IOException {
        super.prop = new Properties();
        super.setUrl(url);
    }
    
    protected void setStage(Stage stage) {
        this.stage = stage;
    }
    
    protected Stage getStage() {
        return stage;
    }
    
    protected void setTitle(String title) {
        this.stage.setTitle(title);
    }
    
    protected void setResource(String view) throws IOException {
        myLoader = new FXMLLoader(MenuController.class.getResource(super.getValue(view)));
        myPane = (Pane) myLoader.load();
    }
    
    protected FXMLLoader getMyLoader() {
        return myLoader;
    }
    
    protected Pane getMyPane() {
        return myPane;
    }
    
    protected void setScene() {
        myScene = new Scene(myPane);
        this.stage.setScene(myScene);
    }
    
    protected void startStage() {
        stage.show();
    }
}
