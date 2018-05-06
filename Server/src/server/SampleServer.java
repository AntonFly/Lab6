package server;
import java.net.*;
import java.util.*;

public  class SampleServer //extends Thread
{
   static Socket s;
   static int num=0;
    public static List<NewClient> Clients = new ArrayList();
    //public  static List<Thread> threads = new ArrayList<>();
     public static Map<Thread, StackTraceElement[]> threads = Thread.getAllStackTraces();
    public static void main(String args[])
    {
        //создание коллекции и заполнение
        PortretList pl= new PortretList();

        try {
            Commands.read(pl.Mo);
        }
        catch (XmlExeption e){
            System.out.println(e);
        }
        try
        {   //инициализация сокета
            ServerSocket server = new ServerSocket(5682, 0,
                    InetAddress.getByName("localhost"));

            System.out.println("Server is started");
            //поток обработки команд сервера
            //ServerCommands SC=new ServerCommands(pl,Clients,threads);
            new Thread(new ServerCommands(pl,Clients,threads)).start();
            new Thread(new TimeSave(pl)).start();

            while(true)
            {

                // ждём нового подключения, после чего запускаем обработку клиента
                // в новый вычислительный поток
                new SampleServer(num, pl, server.accept());
                num++;

            }
        }
        catch(Exception e)
        {System.out.println("init error: "+e);} // вывод исключений

    }

    public SampleServer(int num, PortretList pl, Socket s)
    {

        this.num = num;
        this.s = s;

        // и запускаем новый вычислительный поток (см. ф-ю run())
        //  setDaemon(true);
        // setPriority(NORM_PRIORITY);
        //System.out.println("run 0");
        NewClient nk= new NewClient(num,pl,s,Clients);
        Clients.add(nk);
        Thread NK = new Thread(nk);
        NK.start();
       // threads.add(NK);


    }


}

