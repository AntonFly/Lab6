package client;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;

public class ClienGui {
    static String name;
    Socket s;
     ClienGui(String name, Socket s){
         this.name=name;
         this.s=s;
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



}
