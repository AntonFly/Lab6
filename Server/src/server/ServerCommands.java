package server;

import java.net.Socket;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ServerCommands implements Runnable {
    PortretList pl;
    Socket s;
    List<NewClient> Clients;
    //List<Thread> threads;
    Map<Thread, StackTraceElement[]> threads ;
    ServerCommands(PortretList pl, List<NewClient> Clients,Map<Thread, StackTraceElement[]> threads ){
        this.pl=pl;
        //this.s=s;
        this.Clients=Clients;
        this.threads=threads;
    }
    @Override
    public  synchronized void run() {
        String input;
        class ShutdownHook extends Thread {

            public void run() {
                Commands.write(pl.Mo);

            }
        }
        ShutdownHook shutdownHook = new ShutdownHook();
        Runtime.getRuntime().addShutdownHook(shutdownHook);
        Scanner sc=new Scanner(System.in);
        while (true){
            System.out.println("Enter the server comand(enter \"?\" fo help):");
            input=sc.nextLine();

            switch (input){
                case "q":
                    System.out.println("Do you want to turn off the server?[y/n]");
                    input=sc.nextLine();
                    switch (input){

                        case "y":
                            System.exit(0);
                        case "n":
                            break;
                    }
               /* case "get_all":
                    for (Thread thread : threads.keySet()) {
                        System.out.println(thread);}
                    break;*/
                    case "?":
                    System.out.println("\"q\" to turn of the server\n" +
                            /*"\"stop\" to suspend all client\n" +
                            "\"cont\" to continue all client\n" +*/
                            "\"get_all_clients\" to show all connected clients");
                    break;
                case "cont":
                        notifyAll();
                        break;
                /*case "stop":
                    try{
                       synchronized (this){for (Thread thread : threads) {
                            thread.wait();}
                    }
                    }catch (InterruptedException e){}
                    System.out.println("All client is suspend");
                    break;*/
                case "get_all_clients":
                    for (NewClient cl : Clients) {
                        System.out.println(cl.getName());

                    }
                    break;
                    default:
                        System.out.println("Unknown command");

            }
            Commands.write(pl.Mo);
        }

    }
}
