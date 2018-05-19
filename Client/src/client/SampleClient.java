package client;
import Lab234.*;
import server.Commands;

import java.io.IOException;
import java.io.OutputStream;
import java.net.*;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class SampleClient extends Thread
{

    public static void main(String args[])
    {try {
        try {

            // открываем сокет и коннектимся к localhost:3128
            // получаем сокет сервера
            Socket s = new Socket("localhost", 5682);
            OutputStream os = s.getOutputStream();
            try {
                os.write(args[0].getBytes("UTF-8"));
            }catch (IndexOutOfBoundsException e){
                os.write("noName".getBytes("UTF-8"));
            }
            class ShutdownHook extends Thread {

                public void run() {

                       try {

                           os.write("closeClient".getBytes("UTF-8"));
                       } catch (Exception e){}

                }

            }
            ShutdownHook shutdownHook = new ShutdownHook();
            Runtime.getRuntime().addShutdownHook(shutdownHook);
            // берём поток вывода и выводим туда первый аргумент
            // заданный при вызове, адрес открытого сокета и его порт
            Scanner in = new Scanner(System.in);
            String input;
            while (true) {
                System.out.print("Enter command (enter \"?\" to call up a list of commands): ");
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
                s.getInputStream().read(buf);
                System.out.println("Waiting for server answer...");
                String retstr = new String(buf, "UTF-8");
                // выводим ответ в консоль
                System.out.println(retstr);
            }
        } catch (SocketException e) {
            //System.out.println(e);
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