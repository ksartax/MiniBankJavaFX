/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.Model;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author Damian Stępniak
 */
public class Authorizate extends PropFactory implements AuthorizeInterface {

    private String username;
    private String password;

    public Authorizate() throws IOException{
        super();
        
    }
    
    public void setUrlOutput() throws IOException{
        super.setUrlOutput("src/src/model/resources/aut.properties");
    }
    
    public void setUrl() throws IOException{
        super.setUrl("src/src/model/resources/aut.properties");
    }
    
    @Override
    public void clear(){
        super.clear();
    };
    
    @Override
    public void setPassword(String password) {
        try {
            if(password == "")
                throw new NullPointerException();
            this.password = password;
            super.setValue("password", this.password);
        } catch (IOException ex) {
            Logger.getLogger(Authorizate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void setUsername(String username) {
        try {
            if(username == "")
                throw new NullPointerException();
            this.username = username;
            super.setValue("username", this.username);
        } catch (IOException ex) {
            Logger.getLogger(Authorizate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getToken() {
        return new String(Base64.encodeBase64(("" + super.getValue("password") + "" + ":" + "" + super.getValue("username") + "").getBytes()));
    }
    
}
