package client;

import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class dellcl implements Runnable {
    public  void run()
    {try {
        try {

            // открываем сокет и коннектимся к localhost:3128
            // получаем сокет сервера
            Socket s = new Socket("localhost", 5682);
            OutputStream os = s.getOutputStream();
            os.write("noName".getBytes("UTF-8"));

            class ShutdownHook extends Thread {

                public void run() {

                    try {

                        os.write("closeClient".getBytes("UTF-8"));
                    } catch (Exception e){}

                }

            }
            String str="remove 1";
            ShutdownHook shutdownHook = new ShutdownHook();
            Runtime.getRuntime().addShutdownHook(shutdownHook);
            // берём поток вывода и выводим туда первый аргумент
            // заданный при вызове, адрес открытого сокета и его порт
            Scanner in = new Scanner(System.in);
            String input;

                Thread.sleep(1000);
                os.write(str.getBytes("UTF-8"));


                // читаем ответ
                byte buf[] = new byte[64 * 1024];
                s.getInputStream().read(buf);
                System.out.println("Waiting for server answer...");
                String retstr = new String(buf, "UTF-8");
                // выводим ответ в консоль
                System.out.println(retstr);

        } catch (SocketException e) {
            //System.out.println(e);
            System.out.println("Server is not avaliable");
            System.exit(1);
        }

    }catch (Exception e){
        e.printStackTrace();
    }
    }
}
