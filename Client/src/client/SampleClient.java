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

import static client.SampleClient.sockbusy;

public class SampleClient extends Thread
{
    public static boolean sockbusy=false;
    public static PortretList portretList;
    static Socket s;
    static OutputStream os;
    static InputStream ins;
    static byte buf[] = new byte[1024 * 1024];
    public static void main(String args[])

    {try {
        try {

            // открываем сокет и коннектимся к localhost:3128
            // получаем сокет сервера
            s = new Socket("localhost", 5682);
            os = s.getOutputStream();
            ins= s.getInputStream();
            new Thread(new checkSocket(s)).start();
            String name;
            Colltime clltm= new Colltime(os,ins);
            Thread NK = new Thread(clltm);

            while (true) {
                name = JOptionPane.showInputDialog("Ведите имя, под которым вы хотите отображаться в системе;");
                if (name.length() != 0) {
                    try {
                        send(name);
                        break;
                    } catch (IndexOutOfBoundsException e) {
                        send("noName");

                    }
                }
            }
            Colltime.getColl();
//            NK.start();
            class ShutdownHook extends Thread {

                public void run() {

                    try {
                        //System.out.println("exit");
                        send("closeClient");
                    } catch (Exception e) {
                    }

                }

            }
//            new Thread(new checkSocket(s)).start();
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
                while (sockbusy){}
                sockbusy=true;
                switch (input) {
                    case "":
                        //System.out.println("Unknown command");
                        send("unk");
                        break;
                    case "?":
                        Commands.help();
                        continue;
                    case "run":
                        Main.Lab34();
                        continue;
                    case "q":
                        send("closeClient");
                        System.exit(0);
                    default:
                        send(input);
                }

                // читаем ответ
//                System.out.println("Waiting for server answer...");
                String retstr =recieve();
                // выводим ответ в консоль
                System.out.println(retstr);
                Canvas.repaintCanvas();
                sockbusy=false;
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
     synchronized public  static void send(String stringToSend) throws Exception {
        os.write(stringToSend.getBytes("UTF-8"));
    }
    synchronized public static String recieve() throws IOException {
        buf= new byte[1024*1024];
        ins.read(buf);
        return new String(buf,"UTF-8");
    }
}
 class Colltime implements Runnable {
     Socket s;
     byte[] buf;
     static OutputStream os;
     static InputStream ins;
     static ObjectInputStream ois;
     Colltime(OutputStream os, InputStream ins) {
         this.os = os;
         this.ins = ins;
     }

     @Override
     public void run() {
         while (true) {
//             Canvas.repaintCanvas();
             while (sockbusy){}
             sockbusy=true;
             getColl();
             sockbusy=false;

             try {
                 Thread.sleep(5000);
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
             ois = new ObjectInputStream(ins);
             received = (ArrayList<portret>) ois.readObject();
             SampleClient.portretList.Mo.clear();
             SampleClient.portretList.Mo.addAll(received);
//             for (portret s : SampleClient.portretList.Mo) {
////                     System.out.println(s.NAME+" "+s.LOCATION);
//                 System.out.println("r");
//
//             }
             sockbusy=false;
         }catch (StreamCorruptedException e){
           e.printStackTrace();
         }
         catch (Exception e) {
             e.printStackTrace();
             JOptionPane.showMessageDialog(null, "You are banned!");
             System.exit(1);

         }

     }


 }
class checkSocket implements Runnable{
    private final Socket s;

    checkSocket(Socket s){
        this.s=s;
    }
    @Override
    public void run() {
        while (s.isConnected()){

        }
        JOptionPane.showMessageDialog(null, "You are banned!");
        System.exit(1);
    }
}