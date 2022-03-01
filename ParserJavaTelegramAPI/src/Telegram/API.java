/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Telegram;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.*;

/**
 *
 * @author saetti_elia
 */
public class API {

    public void sendMessage() throws MalformedURLException, IOException {
        String idUtente = "806004064";
        String text = "prova";
        String message = "https://api.telegram.org/bot5275424943:AAGvWbXeK3tJPsArFxwW9qFTMf1DDsstpfA/sendMessage?chat_id=" + idUtente + "&text=" + text;
        URL url = new URL(message);
        URLConnection conn = url.openConnection(); //apre la conn
        InputStream inSt = new BufferedInputStream(conn.getInputStream()); //apre l'url
        System.out.println("[JAVA]: Messaggio inviato");
    }

    public JSONObject getUpdates() throws MalformedURLException, IOException {
        String message = "https://api.telegram.org/bot5275424943:AAGvWbXeK3tJPsArFxwW9qFTMf1DDsstpfA/getUpdates";
        InputStream is = new URL(message).openStream();
        try {
            BufferedReader buffer = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            StringBuilder sb = new StringBuilder();
            int cp;
            while ((cp = buffer.read()) != -1) {
                sb.append((char) cp);
            }
            String jsonText = sb.toString();
            JSONObject json = new JSONObject(jsonText);
            System.out.println(jsonText);
            return json;
        } finally {
            is.close();
        }
    }
    
    
    
}
