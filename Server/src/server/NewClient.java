package server;

import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.Iterator;
import java.util.List;

public class   NewClient implements Runnable{
    PortretList pl;
    Socket s;
    String name;
    int num;
    List<NewClient> Clients;
    public String getName(){
        return name;
    };
    //boolean f;
    NewClient(int num,PortretList pl, Socket s,List<NewClient> Clients){
        this.pl=pl;
        this.s=s;
        this.num=num;
        this.Clients=Clients;
    }
   // public boolean close(){
     //   return this.f;
    //};


    public synchronized void run ()
    {
       // System.out.println("run 1");
            try
            {
               // System.out.println("run 2");
                // из сокета клиента берём поток входящих данных
                InputStream is = s.getInputStream();
                // и оттуда же - поток данных от сервера к клиенту
                OutputStream os = s.getOutputStream();

                // буффер данных в 64 килобайта
                byte buf[];
                buf = new byte[1024*1024];
                is.read(buf);
                name=new String(buf,"UTF-8");
                name=name.trim();
                if (name.equals("noName")) name=String.valueOf(num);
                System.out.println("Client "+name+" is connected");
                ServerGui.addRawClient(this);
                while(true)
                {
                    buf = new byte[64*1024];
                    // читаем 64кб от клиента, результат - кол-во реально принятых данных
                    is.read(buf);
                    // создаём строку, содержащую полученную от клиента информацию
                    String inStr = new String(buf, "UTF-8");

                    //System.out.println(inStr);
                    String command;
                    String perem = "";

                    inStr=inStr.trim();
                    inStr=inStr.replaceAll("[\\s]{2,}", " ");
                    if((inStr.indexOf('{') != -1) && (inStr.lastIndexOf('}') != -1) && inStr.indexOf('{') != 0)
                    {
                        command = inStr.substring(0,inStr.indexOf('{'));
                        perem = inStr.substring(inStr.indexOf('{'),inStr.lastIndexOf('}')+1);
                    }
                    else
                    if(inStr.contains(" ")){
                        String[] com= inStr.split(" ");

                        if (com[1].equals("")){
                            os.write("ERROR command's format.".getBytes());
                            continue;
                        }


                        command=com[0];
                        perem=com[1];
                    }
                    else
                        switch (inStr){
                            case "get":
                            case "remove":
                                os.write("ERROR command's format.".getBytes());
                                continue;

                            default:
                                command=inStr;

                        };




                    try {


                        switch (command) {
                            case "closeClient":
                                Commands.write(pl.Mo);
                                Iterator<NewClient> iter =Clients.iterator();
                                /*for(int i =0;i<Clients.size();i++){
                                    NewClient cl=Clients.get(i);
                                    if (cl.name.equals(this.name)){
                                        Clients.remove(i);
                                    }*/
                                while (iter.hasNext()){
                                    NewClient cl=iter.next();
                                    if (cl.name.equals(name)){
                                        iter.remove();
                                    System.out.println("Client "+name+" is disconnected");}
                                }
//                                System.out.println("Client "+name+" is disconnected");
                                ServerGui.initClientTable();
                                break;
                            case "getPortList":
                                ObjectOutputStream oos= new ObjectOutputStream(os);
                                oos.writeObject(pl);
                                //oos.flush();
                                //oos.close();
                                break;
                            case "get_all1":
                                Commands.get_all1(pl.Mo,perem);
                                //System.out.println(Commands.get_all(pl.Mo,perem));
                                break;
                            case "get_all":
                                os.write(Commands.get_all(pl.Mo,perem).getBytes());
                              //System.out.println(Commands.get_all(pl.Mo,perem));
                                break;
                            case "get":
                                os.write(Commands.get(pl.Mo, perem).getBytes());
                                break;
                            case "sort":
                                Commands.sort(pl.Mo).getBytes();
                                break;
                            case "sortN":
                                Commands.sortN(pl.Mo).getBytes();
                                break;
                            case "remove":
                                os.write(Commands.remove(pl.Mo, perem).getBytes());
                                break;
                            case "count":
                                os.write(Commands.count(pl.Mo, perem).getBytes());
                                break;
                            case "remove_last":
                                Commands.removeLast(pl.Mo).getBytes();
                                break;
                            case "remove_all":
                                os.write(Commands.removeAll(pl.Mo, perem).getBytes());
                                break;
                            case "add":
                               os.write(Commands.add(pl.Mo,perem).getBytes());
                                break;
                            case "add_if_min":
                                os.write(Commands.add_if_min(pl.Mo,perem).getBytes());
                                break;
                            case "save":
                                Commands.write(pl.Mo).getBytes();
                                break;

                            default:
                                //System.out.println((char) 27 + "[31mERROR!!! Unknown command " + (char)27 + "[0m");
                                os.write("Unknown command".getBytes());

                        }
                        if(command.equals("closeClient"))break;

                    }
                    catch (SocketException e){


                    }
                    catch (ArrayIndexOutOfBoundsException e){
                        os.write("No such element(ArrayIndexOutOfBoundsException)".getBytes());
                    }
                    catch (JasonException e){
                        os.write(e.Jsret().getBytes());
                    }
                    catch (FormatEx e){
                        os.write("smth wrong with comand format".getBytes());
                    }
                    catch (Exception e){e.printStackTrace();}
                }




            }catch (SocketException e){}
            catch(Exception e) {
                e.printStackTrace();
                //System.out.println("init error: "+e); // вывод исключений
            }
    }
}
