/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maintelegram;

import Telegram.*;
import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.json.*;
import org.xml.sax.SAXException;

/**
 *
 * @author saetti_elia
 */
public class MainTelegram {

    static API prova = new API();
    static ArrayList<CMessage> newMessages = new ArrayList<CMessage>();
    static long ultimaChiamata = Instant.now().getEpochSecond();
    static int tempoAgg = 2;
    //ArrayList<CMessage> tmpUpdates = new ArrayList<CMessage>();

    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException, InterruptedException {
        // TODO code application logic here

        CCoordinate co = new CCoordinate();
        String paese = "";

        //Ottiene le coordinate da paese
        /*paese = prova.getLuogo();
        co = prova.setUpXML(paese);
        System.out.println(co.toString());*/
        //
        while (true) {
            ultimaChiamata = Instant.now().getEpochSecond();
            newMessages = prova.getNewMessages(ultimaChiamata, tempoAgg);
            System.out.println(newMessages.toString());
            TimeUnit.SECONDS.sleep(tempoAgg);
        }
        
        

        //System.out.println(paese);
        //prova.setUpXML("cabiate");
    }

}
