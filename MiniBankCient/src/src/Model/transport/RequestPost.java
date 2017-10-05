/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.Model.transport;

import java.io.IOException;
import java.net.ProtocolException;

/**
 *
 * @author Damian Stępniak
 */
public class RequestPost extends Request {

    public void InitPost(String url) throws ProtocolException, IOException {
        super.setUrl(url);
        super.initConnection();
        super.setRequestMethod("POST");
        super.setRequestProperty("Accept", "application/json");
        super.setRequestProperty("Content-Type", "application/json");
    }

    public void sendData(String data) throws IOException {
        super.sendData(data);
    }

    public String getResponse() throws IOException {
        return super.getResponse();
    }

}
