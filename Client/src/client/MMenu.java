package client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;

public class MMenu {
    public static JMenuBar JMbuild(Socket s){
    JMenuBar menuBar = new JMenuBar();
    JMenu file = new JMenu("Файл");
    JMenuItem help = new JMenuItem("Помощь");
        help.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            FramesPanels.Jhelp();
        }
    });
    JMenuItem save = file.add(new JMenuItem("Сохранить", 'С'));
        save.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                s.getOutputStream().write("save".getBytes("UTF-8"));
                Canvas.repaintCanvas();
            }catch (Exception y){
                JOptionPane.showMessageDialog(null, "You are banned!");
                System.exit(1);
//                y.printStackTrace();
            }
        }
    });
        save.setAccelerator(KeyStroke.getKeyStroke("ctrl S"));
        file.addSeparator();
    JMenuItem exit = file.add(new JMenuItem("Выход", 'В'));
        exit.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    });
        exit.setAccelerator(KeyStroke.getKeyStroke("ctrl D"));
    JMenu coll= new JMenu("Коллекция");
    JMenu SMenu =new JMenu("Сортировка");
    JMenuItem count=coll.add(new JMenuItem("Колличество эллементов"));
        count.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                s.getOutputStream().write("count".getBytes("UTF-8"));
                byte buf[] = new byte[1024 * 1024];
                s.getInputStream().read(buf);
                FramesPanels.Jcount(new String(buf,"UTF-8"));
            }catch (Exception y){
                JOptionPane.showMessageDialog(null, "You are banned!");
                System.exit(1);
//                y.printStackTrace();
            }
        }
    });

        SMenu.add("По имени").addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    s.getOutputStream().write("sortN".getBytes("UTF-8"));
                }catch (Exception s){
                    JOptionPane.showMessageDialog(null, "You are banned!");
                    System.exit(1);
//                    s.printStackTrace();
                }
            }
        });
        SMenu.add("По размеру").addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    s.getOutputStream().write("sort".getBytes("UTF-8"));
                }catch (Exception s){
                    JOptionPane.showMessageDialog(null, "You are banned!");
                    System.exit(1);
//                    s.printStackTrace();
                }
            }
        });
        JMenuItem remlast=(new JMenuItem("Удалить последний объект"));
        remlast.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    s.getOutputStream().write("remove_last".getBytes("UTF-8"));
                    Canvas.repaintCanvas();
                }catch (Exception s){
                    JOptionPane.showMessageDialog(null, "You are banned!");
                    System.exit(1);
//                    s.printStackTrace();
                }
            }
        });
        coll.add(SMenu);
        coll.addSeparator();
        coll.add(remlast);
        menuBar.add(file);
        menuBar.add(coll);
        menuBar.add(help);
        return  menuBar;
}}
