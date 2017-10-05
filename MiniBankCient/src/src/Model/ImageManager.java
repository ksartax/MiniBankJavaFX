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
import java.io.OutputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import src.Model.ImageInterface;

/**
 *
 * @author Damian Stępniak
 */
public class ImageManager extends PropFactory implements ImageInterface {

    private String url = "src/src/model/resources/image.properties";

    public ImageManager() throws IOException {
        super.prop = new Properties();
        super.setUrl(url);
    }

    @Override
    public String getLogo() {
        return getValue("logo").trim();
    }

}
