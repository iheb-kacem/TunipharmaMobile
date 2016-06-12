/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tunipharma.service;

import com.tunipharma.midlet.TuniPharma;
import com.tunipharma.parsingxml.DisplayRecherche;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.Date;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;

/**
 * @author asma
 */
public class DisplayCommentaire implements CommandListener {

    String idUser;
    int idPharma;
    Form f = new Form("Commentaire");
    Command cmd = new Command("commenter", Command.SCREEN, 0);
    Command cmdback = new Command("Retour", Command.BACK, 0);
    Gauge g = new Gauge("note", true, 5, 0);
    TextField com = new TextField("rediger commentaire", null, 10000, TextField.ANY);
    Alert alerta = new Alert("Error", "Sorry", null, AlertType.ERROR);
    Alert alertaOK = new Alert("info", "votre commentaire a ete posté", null, AlertType.INFO);
    //server
    HttpConnection hc;
    DataInputStream dis;
    String url = "http://localhost/PIDEVV/ajout.php";
    StringBuffer sb = new StringBuffer();
    int ch;
    TuniPharma m;
    
    public DisplayCommentaire(TuniPharma m, int id, String fb_id) {
        this.m=m;
        this.idUser=fb_id;
        this.idPharma=id;
        f.append("ajouter commentaire");
        f.append(g);
        f.append(com);
        f.addCommand(cmd);
        f.addCommand(cmdback);
        f.setCommandListener(this);

        m.disp.setCurrent(f);
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
    }

    public void commandAction(Command c, Displayable d) {
        if (c == cmd) {
            new Thread(new Runnable() {
                public void run() {
                    // code
                    try {
                        int note=g.getValue();
                        String strCom=com.getString();
                        Date d = new Date();
                       String date= converter(d);
                        System.out.println(date);
                        
                        
                        String  Url=url+"?client="+idUser+"&pharmacie="+idPharma+"&jour="+date+"&evaluation="+note+"&com="+strCom ;
                        hc = (HttpConnection) Connector.open(Url.trim());
                        dis = new DataInputStream(hc.openDataInputStream());
                        while ((ch = dis.read()) != -1) {
                            sb.append((char) ch);
                        }
                        if ("successfully added".equalsIgnoreCase(sb.toString().trim())) {
                            m.disp.setCurrent(alertaOK);
                        } else {
                            m.disp.setCurrent(alerta);
                        }
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }).start();

        }
        if (c == cmdback){
            new DisplayService(m, idPharma);
        }

    }
    private String converter(Date D){
        String str=D.toString();
        String day;
        String month;
        String year;
        
        day=str.substring(8, 10);
        month=str.substring(4, 7);
        year=str.substring(24, 28);
   
        return year+"-"+getMoth(month)+"-"+day;
    
    }    
    
    private int getMoth(String tmp ){
        int month=0;
        
        if(tmp.equals("Jan")){
            month=1;  
        }else if(tmp.equals("Fev")){
            month=2;  
        }else if(tmp.equals("Mar")){
            month=3;  
        }
        else if(tmp.equals("Apr")){
            month=4;  
        }
        else if(tmp.equals("May")){
            month=5;  
        }
        else if(tmp.equals("Jun")){
            month=6;  
        }
        else if(tmp.equals("Jul")){
            month=7;  
        }
        else if(tmp.equals("Aug")){
            month=8;  
        }
        else if(tmp.equals("Sep")){
            month=9;  
        }
        else if(tmp.equals("Oct")){
            month=10;  
        }
        else if(tmp.equals("Nov")){
            month=11;  
        }
        else if(tmp.equals("Dec")){
            month=12;  
        }
        
        
    return month;
    }
    
    
    
}

