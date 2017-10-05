/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.Model.transport;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

/**
 *
 * @author Damian Stępniak
 */
public class Request {

    private final String USER_AGENT = "Mozilla/5.0";
    private URL url;
    private HttpURLConnection httpCon;
    private BufferedReader in;
    protected DataOutputStream wr;

    public void setUrl(String url) throws IOException {
        this.url = new URL(url);
    }

    public void initConnection() throws IOException {
        httpCon = (HttpURLConnection) this.url.openConnection();
    }

    public void setRequestMethod(String request) throws ProtocolException {
        httpCon.setRequestMethod(request);
    }

    public void setRequestProperty(String val1, String val2) {
        httpCon.setRequestProperty(val1, val2);
    }

    protected void sendData(String data) throws IOException {
        httpCon.setDoOutput(true);
        wr = new DataOutputStream(httpCon.getOutputStream());
        wr.writeBytes(data);
        wr.flush();
        wr.close();
    }

    protected String getResponse() throws IOException {
        in = new BufferedReader(
                new InputStreamReader(httpCon.getInputStream()));
        String json = in.readLine();
        in.close();
        return json;
    }

}
