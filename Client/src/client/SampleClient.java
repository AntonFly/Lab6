package client;
import Lab234.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import server.Commands;
import server.PortretList;

import javax.swing.*;
import java.io.*;
import java.lang.reflect.Type;
import java.net.*;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import static client.SampleClient.lock;
import static client.SampleClient.recieve;
import static sun.rmi.transport.DGCAckHandler.received;

public class SampleClient extends Thread
{
    static Lock lock= new ReentrantLock();
    public static PortretList portretList;
    static Socket s;
    static OutputStream os;
    static InputStream ins;
    static byte buf[] = new byte[1024 * 1024];
    static ResourceBundle bundle;
    static ClienGui cgi;
    public static void main(String args[])

    {bundle= ResourceBundle.getBundle("resources");
        try {
        try {

            // открываем сокет и коннектимся к localhost:3128
            // получаем сокет сервера
            //lock.unlock();
            s = new Socket("localhost", 5682);
            os = s.getOutputStream();
            ins= s.getInputStream();
            String name;
            Colltime clltm= new Colltime(os,ins);
            Thread NK = new Thread(clltm);
            JFrame frame = new Login();
//            while (true) {
//                name = JOptionPane.showInputDialog("Ведите имя, под которым вы хотите отображаться в системе;");
//                if (name.length() != 0) {
//                    try {
//                        lock.lock();
//                        send(name);
//                        break;
//                    } catch (IndexOutOfBoundsException e) {
//                        send("noName");
//                    }
//                    finally {
//                        lock.unlock();
//                    }
//                }
//            }
            class ShutdownHook extends Thread {

                public void run() {

                    try {
                        //System.out.println("exit");
                        send("closeClient");
                    } catch (Exception e) {
                    }

                }

            }
            ShutdownHook shutdownHook = new ShutdownHook();
            Runtime.getRuntime().addShutdownHook(shutdownHook);
            // берём поток вывода и выводим туда первый аргумент
            // заданный при вызове, адрес открытого сокета и его порт
            Colltime.getColl();
//            cgi = new ClienGui(name, s,portretList);
//            cgi.buildGui();
            NK.start();
            Scanner in = new Scanner(System.in);
            String input;
            while (true) {
               // System.out.print("Enter command (enter \"?\" to call up a list of commands): ");
                input = in.nextLine();
                try {
                    if(lock.tryLock()) {
                        lock.lock();

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

                        String retstr = recieve();
                        System.out.println(retstr);
                    }
                }
                finally {
                lock.unlock();
                }
                Canvas.repaintCanvas();
            }
        } catch (SocketException e) {
            JOptionPane.showMessageDialog(null, "You are banned!");
            System.out.println("Server is not avaliable");
            System.exit(1);
        } catch (NoSuchElementException e) {
        } catch (Exception e) {
            e.printStackTrace();
        }
    }catch (Exception e){
        e.printStackTrace();
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
     static final Gson GSON= new GsonBuilder().setLenient().setPrettyPrinting().create();
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
             try {
                 getColl();
                 FramesPanels.canvas.repaintCanvas();
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }

             try {
                 Thread.sleep(5000);
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
         }
     }

     public static void getColl() throws InterruptedException {

         if (lock.tryLock(500, TimeUnit.MILLISECONDS)) {
             try {

                 byte buf[] = new byte[1024 * 1024];
                 ArrayList<portret> recieved = new ArrayList<>();
                 os.write("getPortList".getBytes("UTF-8"));
                 //ois = new ObjectInputStream(ins);
                 String str = recieve();
                 str = str.trim();
//                 str=str.split("");
                 Type type = new TypeToken<ArrayList<portret>>() {
                 }.getType();
                 recieved = GSON.fromJson(str, type);
                 SampleClient.portretList.Mo.clear();
                 SampleClient.portretList.Mo.addAll(recieved);

             } catch (StreamCorruptedException e) {
                 e.printStackTrace();
             } catch (Exception e) {
                 e.printStackTrace();
                 JOptionPane.showMessageDialog(null, "You are banned!");
                 System.exit(1);

             } finally {
                 lock.unlock();

             }
         }
     }

 }
