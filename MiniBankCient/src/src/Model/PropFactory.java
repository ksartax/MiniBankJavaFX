/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.Model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author Damian Stępniak
 */
public class PropFactory {

    protected Properties prop;
    protected FileInputStream input;
    protected FileOutputStream output;

    protected PropFactory() throws IOException {
        this.prop = new Properties();
    }

    protected void setUrl(String url) throws FileNotFoundException, IOException {
        input = new FileInputStream(url);
        prop.load(input);
    }

    protected void setUrlOutput(String url) throws FileNotFoundException, IOException {
        output = new FileOutputStream(url);
    }

    protected String getValue(String key) {
        return this.prop.getProperty(key);
    }

    protected void setValue(String key, String value) throws IOException {
        this.prop.setProperty(key, value);
        prop.store(output, null);
    }
    
    protected void clear(){
        prop.clear();
    }

    protected void close() throws IOException {
        input.close();
    }

}
