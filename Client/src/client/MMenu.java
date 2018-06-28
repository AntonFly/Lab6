package client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import static client.ClienGui.*;
import static client.FramesPanels.*;
import static client.SampleClient.*;

public class MMenu {
    private static JMenu file;
    private static JMenuItem save;
    private static JMenuItem exit;
    private static JMenu set;
    private static JMenuItem help;
    private static JMenu lang;
    private static JMenu coll;
    private static JMenuItem sname;
    private static JMenuItem ssize;
    private static JMenu SMenu;
    private static JMenuItem count;
    private static JMenuItem remlast;
    static JMenuBar JMbuild(Socket s) throws UnsupportedEncodingException {
    JMenuBar menuBar = new JMenuBar();
    file = new JMenu(new String(bundle.getString("filemenu").getBytes("ISO-8859-1"),"UTF-8"));
    set= new JMenu(new String(bundle.getString("setmenu").getBytes("ISO-8859-1"),"UTF-8"));
    help = set.add(new JMenuItem(new String(bundle.getString("setmenu.help").getBytes("ISO-8859-1"),"UTF-8")));
        help.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                FramesPanels.Jhelp();
            } catch (UnsupportedEncodingException e1) {
                e1.printStackTrace();
            }
        }
    });

    lang= new JMenu(new String(bundle.getString("setmenu.lang").getBytes("ISO-8859-1"),"UTF-8"));
        JMenuItem ru = lang.add(new JMenuItem(new String(bundle.getString("setmenu.lang.ru").getBytes("ISO-8859-1"), "UTF-8")));
            ru.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    bundle= ResourceBundle.getBundle("resources");
                    try {
                        rename();
                    } catch (UnsupportedEncodingException e1) {
                        e1.printStackTrace();
                    }
//                    JMain.repaint();
//                    JMain.revalidate();

                }
            });
        JMenuItem de = lang.add(new JMenuItem(new String(bundle.getString("setmenu.lang.de").getBytes("ISO-8859-1"), "UTF-8")));
        de.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bundle= ResourceBundle.getBundle("resources_de",new Locale("de"));
                try {
                    rename();
                } catch (UnsupportedEncodingException e1) {
                    e1.printStackTrace();
                }

            }
        });
        JMenuItem lv = lang.add(new JMenuItem(new String(bundle.getString("setmenu.lang.lv").getBytes("ISO-8859-1"), "UTF-8")));
        lv.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bundle= ResourceBundle.getBundle("resources_lv",new Locale("lv"));
                try {
                    rename();
                } catch (UnsupportedEncodingException e1) {
                    e1.printStackTrace();
                }
            }
        });
        JMenuItem es = lang.add(new JMenuItem(new String(bundle.getString("setmenu.lang.es").getBytes("ISO-8859-1"), "UTF-8")));
        es.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bundle= ResourceBundle.getBundle("resources_es",new Locale("es"));
                try {
                    rename();
                } catch (UnsupportedEncodingException e1) {
                    e1.printStackTrace();
                }
            }
        });
    save = file.add(new JMenuItem(new String(bundle.getString("filemenu.save").getBytes("ISO-8859-1"),"UTF-8")));
        save.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if(lock.tryLock(500, TimeUnit.MILLISECONDS)){try{
                lock.lock();
                send("save");
                Canvas.repaintCanvas();
            }catch (Exception y){
                JOptionPane.showMessageDialog(null, "You are banned!");
                System.exit(1);
//                y.printStackTrace();
            }
            finally {
                lock.unlock();
            }
        }
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }});
        save.setAccelerator(KeyStroke.getKeyStroke("ctrl S"));
        file.addSeparator();
    exit = file.add(new JMenuItem(new String(bundle.getString("filemenu.exit").getBytes("ISO-8859-1"),"UTF-8")));
        exit.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    });
        exit.setAccelerator(KeyStroke.getKeyStroke("ctrl D"));
    coll= new JMenu(new String(bundle.getString("collmenu").getBytes("ISO-8859-1"),"UTF-8"));
    SMenu =new JMenu(new String(bundle.getString("collmenu.sort").getBytes("ISO-8859-1"),"UTF-8"));
    count=coll.add(new JMenuItem(new String(bundle.getString("collmenu.count").getBytes("ISO-8859-1"),"UTF-8")));
        count.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if(lock.tryLock(500, TimeUnit.MILLISECONDS)){
                    try{
                send("count");
                FramesPanels.Jcount(recieve());
            }catch (Exception y){
                JOptionPane.showMessageDialog(null, "You are banned!");
                System.exit(1);
//                y.printStackTrace();
            }
            finally {
                lock.unlock();
            }
        }
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }});

        sname=new JMenuItem(new String(bundle.getString("collmenu.sort.name").getBytes("ISO-8859-1"),"UTF-8"));
        sname.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if(lock.tryLock(500, TimeUnit.MILLISECONDS)){try{
                    send("sortN");
                }catch (Exception s){
                    JOptionPane.showMessageDialog(null, "You are banned!");
                    System.exit(1);
//                    s.printStackTrace();
                }
                finally {
                    lock.unlock();
                }
            }
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }});
        SMenu.add(sname);
        ssize=new JMenuItem(new String(bundle.getString("collmenu.sort.size").getBytes("ISO-8859-1"),"UTF-8"));
        ssize.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    if(lock.tryLock(500, TimeUnit.MILLISECONDS)){
                        try{
                    send("sort");
                }catch (Exception s){
                    JOptionPane.showMessageDialog(null, "You are banned!");
                    System.exit(1);
//                    s.printStackTrace();
                }finally {
                    lock.unlock();
                }
            }
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }});
        SMenu.add(ssize);
        remlast=(new JMenuItem(new String(bundle.getString("collmenu.dell").getBytes("ISO-8859-1"),"UTF-8")));
        remlast.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if(lock.tryLock(1000, TimeUnit.MILLISECONDS)){
                    try{
                        send("remove_last");
                        Canvas.repaintCanvas();
                    }catch (Exception s){
                        JOptionPane.showMessageDialog(null, "You are banned!");
                        System.exit(1);
    //                    s.printStackTrace();
                    }
                    finally {
                        lock.unlock();
                    }
                }
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }});
        coll.add(SMenu);
        coll.addSeparator();
        coll.add(remlast);
        set.add(lang);
        menuBar.add(file);
        menuBar.add(coll);
        menuBar.add(set);
        return  menuBar;
}
public static void rename() throws UnsupportedEncodingException {
    comLable.setText(new String(bundle.getString("com").getBytes("ISO-8859-1"),"UTF-8"));
    ta.setText(new String(bundle.getString("res").getBytes("ISO-8859-1"),"UTF-8"));
    colourlb.setText(new String(bundle.getString("colour").getBytes("ISO-8859-1"),"UTF-8"));
    sizelb.setText(new String(bundle.getString("size").getBytes("ISO-8859-1"),"UTF-8"));
    sizelbm.setText(new String(bundle.getString("sizem").getBytes("ISO-8859-1"),"UTF-8"));
    Xlb.setText(new String(bundle.getString("x").getBytes("ISO-8859-1"),"UTF-8"));
    Xmlb.setText(new String(bundle.getString("xm").getBytes("ISO-8859-1"),"UTF-8"));
    Ylb.setText(new String(bundle.getString("y").getBytes("ISO-8859-1"),"UTF-8"));
    Ymlb.setText(new String(bundle.getString("ym").getBytes("ISO-8859-1"),"UTF-8"));
    start.setText(new String(bundle.getString("looking").getBytes("ISO-8859-1"),"UTF-8"));
    file.setText(new String(bundle.getString("filemenu").getBytes("ISO-8859-1"),"UTF-8"));
    save.setText(new String(bundle.getString("filemenu.save").getBytes("ISO-8859-1"),"UTF-8"));
    exit.setText(new String(bundle.getString("filemenu.exit").getBytes("ISO-8859-1"),"UTF-8"));
    coll.setText(new String(bundle.getString("collmenu").getBytes("ISO-8859-1"),"UTF-8"));
    SMenu.setText(new String(bundle.getString("collmenu.sort").getBytes("ISO-8859-1"),"UTF-8"));
    sname.setText(new String(bundle.getString("collmenu.sort.name").getBytes("ISO-8859-1"),"UTF-8"));
    ssize.setText(new String(bundle.getString("collmenu.sort.size").getBytes("ISO-8859-1"),"UTF-8"));
    set.setText(new String(bundle.getString("setmenu").getBytes("ISO-8859-1"),"UTF-8"));
    lang.setText(new String(bundle.getString("setmenu.lang").getBytes("ISO-8859-1"),"UTF-8"));
    count.setText(new String(bundle.getString("collmenu.count").getBytes("ISO-8859-1"),"UTF-8"));
    remlast.setText(new String(bundle.getString("collmenu.dell").getBytes("ISO-8859-1"),"UTF-8"));
    help.setText(new String(bundle.getString("setmenu.help").getBytes("ISO-8859-1"),"UTF-8"));


}
}
