package client;


import Lab234.portret;
import server.PortretList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.CopyOnWriteArrayList;

public class ClienGui {
    static String name;
    Socket s;
    static JFrame JMain;
    static JPanel Jdialog;
    static JPanel Jparam;

//    static ResourceBundle bundle;
    static CopyOnWriteArrayList<portret> listToDraw;
    public static CopyOnWriteArrayList<PortretButton> buttons = new CopyOnWriteArrayList<>();
     ClienGui(String name, PortretList pl){
         this.name=name;
         this.s=s;
         this.listToDraw=pl.Mo;
     }
    public void buildGui() throws UnsupportedEncodingException {

        JMain = FramesPanels.getJmain(name);

        int X=JMain.getWidth();
        int Y=JMain.getHeight();
        JMain.setResizable(false);
        JMain.setLayout(new BoxLayout(JMain.getContentPane(),BoxLayout.Y_AXIS));
        JMenuBar menuBar =client.MMenu.JMbuild(s);
        JMain.setJMenuBar(menuBar);

        JMain.add(
                Jparam=FramesPanels.Jparam(s,X,Y));
        JMain.add(Jdialog=FramesPanels.Jdiolog(s,X,Y));
        JMain.revalidate();

    }

    public static void initButtons(){
        buttons.clear();
        listToDraw.forEach((item)->{
            buttons.add(new PortretButton(item));
        });
    }




}
