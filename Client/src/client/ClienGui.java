package client;


import Lab234.portret;
import server.PortretList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ClienGui {
    static String name;
    Socket s;
    static CopyOnWriteArrayList<portret> listToDraw;
    public static CopyOnWriteArrayList<PortretButton> buttons = new CopyOnWriteArrayList<>();
     ClienGui(String name, Socket s, PortretList pl){
         this.name=name;
         this.s=s;
         this.listToDraw=pl.Mo;
     }
    public void buildGui() {
        JFrame JMain = FramesPanels.getJmain(name);
        int X=JMain.getWidth();
        int Y=JMain.getHeight();
        JMain.setLayout(new BoxLayout(JMain.getContentPane(),BoxLayout.Y_AXIS));
        JMenuBar menuBar =client.MMenu.JMbuild(s);
        JMain.setJMenuBar(menuBar);
        //JPanel Jp1=new JPanel();
        //Jp1.setSize((int)(X/2),(int)(Y/2));
        JPanel Jp2=new JPanel();

        //Jp2.setSize((int)(X/2),(int)(Y/2));
        //Jp1.add(new JButton("kn1"));
        //Jp2.add(new JButton("kn2"));
        //JMain.add(Jp1);
        //JMain.add(new JSeparator(SwingConstants.HORIZONTAL));
        JMain.add(FramesPanels.Jparam(s,X,Y));
     //JMain.add(new JSeparator(SwingConstants.HORIZONTAL));
        JMain.add(FramesPanels.Jdiolog(s,X,Y));
        JMain.revalidate();

    }

    public static void initButtons(){
        buttons.clear();
        listToDraw.forEach((item)->{
            buttons.add(new PortretButton(item));
        });
    }




}
