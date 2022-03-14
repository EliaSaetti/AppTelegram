/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Telegram;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.json.*;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

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

    public void setUpXML(String luogo) throws FileNotFoundException, IOException {
        BufferedReader in = null;
        PrintWriter out;

        out = new PrintWriter("valute.xml");
        URL url;
        // System.setProperty("java.protocol.handler.pkgs", "com.sun.net.ssl.internal.www.protocol");
        // url =  new URL("https://www.agenziaentrate.gov.it/portale/documents/20143/296358/Provvedimento+30+marzo+2017+Distributori+automatici_Allegato+Api+REST+Dispositivi+%28v.3.0%29.pdf/7cfe447f-5823-5873-e55d-d3fa825877fd");
        url = new URL("https://nominatim.openstreetmap.org/search?q=" + luogo + "&format=xml&addressdetails=1");
        in = new BufferedReader(new InputStreamReader(url.openStream()));
        String line;
        while ((line = in.readLine()) != null) {
            out.println(line);
        }
        in.close();
        out.close();

        CCoordinate c = new CCoordinate();
        List<CCoordinate> dati = new ArrayList<>();
        String myFileName = "valute.xml";
        dati = c.parseDocument(myFileName);

    }

    public void getCoordinates(String filename) throws ParserConfigurationException, SAXException, IOException {
        String longi, lati;
        List<CCoordinate> tmp = new ArrayList();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File(filename));

        NodeList nList = document.getElementsByTagName("place");

        NodeList childs = nList.item(0).getChildNodes();
        for (int j = 1; j < childs.getLength(); j++) {
            CCoordinate objtmp = new CCoordinate(childs.item(j).getNodeName(), childs.item(j).getTextContent());
            tmp.add(objtmp);

        }
        return tmp;
    }

}
