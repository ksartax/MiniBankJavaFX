/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.Model;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
/**
 *
 * @author Damian Stępniak
 */
public interface MenuInterface {
    
    public void setTitle(String title);
    public void setState(Stage state);
    public void setResource(String resoulce) throws IOException;
    public void startStage();
    public void closeStage();
    public Stage getStage();
    public FXMLLoader getResource();
    
}
