package client;
import Lab234.*;
import server.Commands;
import server.PortretList;

import javax.swing.*;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class SampleClient extends Thread
{
    public  static PortretList portretList;
    public static void main(String args[])
    {try {
        try {

            // открываем сокет и коннектимся к localhost:3128
            // получаем сокет сервера
            Socket s = new Socket("localhost", 5682);
            OutputStream os = s.getOutputStream();
            InputStream ins= s.getInputStream();
            String name;
            Colltime clltm= new Colltime(os,ins);
            Thread NK = new Thread(clltm);

            while (true) {
                name = JOptionPane.showInputDialog("Ведите имя, под которым вы хотите отображаться в системе;");
                if (name.length() != 0) {
                    try {
                        os.write(name.getBytes("UTF-8"));
                        break;
                    } catch (IndexOutOfBoundsException e) {
                        os.write("noName".getBytes("UTF-8"));

                    }
                }
            }
            Colltime.getColl();
//            NK.start();
            class ShutdownHook extends Thread {

                public void run() {

                    try {
                        //System.out.println("exit");
                        os.write("closeClient".getBytes("UTF-8"));
                    } catch (Exception e) {
                    }

                }

            }
            ShutdownHook shutdownHook = new ShutdownHook();
            Runtime.getRuntime().addShutdownHook(shutdownHook);
            // берём поток вывода и выводим туда первый аргумент
            // заданный при вызове, адрес открытого сокета и его порт
            ClienGui cgi = new ClienGui(name, s,portretList);
            cgi.buildGui();
          NK.start();
            Scanner in = new Scanner(System.in);

            String input;
            while (true) {
               // System.out.print("Enter command (enter \"?\" to call up a list of commands): ");
                input = in.nextLine();
                switch (input) {
                    case "":
                        //System.out.println("Unknown command");
                        os.write("unk".getBytes("UTF-8"));
                        break;
                    case "?":
                        Commands.help();
                        continue;
                    case "run":
                        Main.Lab34();
                        continue;
                    case "q":
                        os.write("closeClient".getBytes("UTF-8"));
                        System.exit(0);
                    default:
                        os.write(input.getBytes("UTF-8"));
                }


                // читаем ответ
                byte buf[] = new byte[1024 * 1024];
                ins.read(buf);
                System.out.println("Waiting for server answer...");
                String retstr = new String(buf, "UTF-8");
                // выводим ответ в консоль
                System.out.println(retstr);
                Canvas.repaintCanvas();
            }
        } catch (SocketException e) {
            JOptionPane.showMessageDialog(null, "You are banned!");
            System.out.println("Server is not avaliable");
            System.exit(1);
        } catch (NoSuchElementException e) {
        } catch (Exception e) {
            //System.out.println("init error: "+e);
        }
    }catch (Exception e){
        //e.printStackTrace();
    }
    }
}
 class Colltime implements Runnable {
     Socket s;
     static OutputStream os;
     static InputStream ins;

     Colltime(OutputStream os, InputStream ins) {
         this.os = os;
         this.ins = ins;
     }

     @Override
     public void run() {
         while (true) {
             getColl();
             try {
                 Thread.sleep(1000);
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
         }
     }

     public static void getColl() {
         try {

             byte buf[] = new byte[1024 * 1024];
             ArrayList<portret> received;
             os.write("getPortList".getBytes("UTF-8"));
             ObjectInputStream ois = new ObjectInputStream(ins);
             received = (ArrayList<portret>) ois.readObject();
             SampleClient.portretList.Mo.clear();
             SampleClient.portretList.Mo.addAll(received);
             for (portret s : SampleClient.portretList.Mo) {
//                     System.out.println(s.NAME+" "+s.LOCATION);
                 System.out.println("r");

             }
         } catch (Exception e) {
             JOptionPane.showMessageDialog(null, "You are banned!");
             System.exit(1);
//             e.printStackTrace();
         }

     }


 }
