package server;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;

public class MMenu {
    public static JMenuBar JMbuild(PortretList pl){
    JMenuBar menuBar = new JMenuBar();
    JMenu file = new JMenu("Файл");
    JMenuItem load = new JMenuItem("Загрузить");
        load.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog(null, "Загрузка коллекции с диска приведет к удалению\n" +
                    "всех изменений за последнии 30 секунд.\n" +
                    "Вы хотите продолжить?", "Загрузика коллекции", dialogButton);
            if(dialogResult == 0) {
                try {
                    Commands.read(pl.Mo);
                } catch (XmlExeption exeption) {
                    exeption.printStackTrace();
                }
            } else {

            }
        }
    });
    JMenuItem save = file.add(new JMenuItem("Сохранить", 'С'));
        save.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                Commands.write(pl.Mo);
            }catch (Exception y){
                y.printStackTrace();
            }
        }
    });
        save.setAccelerator(KeyStroke.getKeyStroke("ctrl S"));
        file.addSeparator();
    JMenuItem exit = file.add(new JMenuItem("Выход", 'В'));
        exit.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog(null, "Вы уверены, что хотите закрыть сервер?", "Выход", dialogButton);
            if(dialogResult == 0) {
                    System.exit(0);
                }
            else {}
        }
    });
        exit.setAccelerator(KeyStroke.getKeyStroke("ctrl D"));
        file.add(load);
        file.add(save);
        file.addSeparator();
        file.add(exit);
        menuBar.add(file);
        return  menuBar;
}}
