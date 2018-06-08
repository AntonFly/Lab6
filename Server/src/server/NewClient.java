package server;

import Lab234.portret;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static server.ServerGui.modeltable1;
import static server.ServerGui.table1;

public class   NewClient implements Runnable{
     static  ObjectOutputStream oos;
    PortretList pl;
    public Socket s;
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



    public synchronized void run ()
    {
//       int i = s.getPort();
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
                                    for(int i=0;modeltable1.getRowCount()>=i;i++){
                                        if (Integer.valueOf((String) table1.getValueAt(i,0))==s.getPort())
                                        modeltable1.removeRow(i);
                                        modeltable1.fireTableDataChanged();
                                    }
                                    NewClient cl=iter.next();
                                    if (cl.name.equals(name)){
                                        iter.remove();
                                    System.out.println("Client "+name+" is disconnected");}
                                }
//                                System.out.println("Client "+name+" is disconnected");
                                ServerGui.initClientTable();
                                break;
                            case "getPortList":
                                ServerGui.changeColour(s, Color.green);
                                ArrayList<portret> listToSend= new ArrayList<>();
                                listToSend.addAll(pl.Mo);
                                oos= new ObjectOutputStream(os);
                                oos.writeObject(listToSend);
                                //oos.flush();
                                //oos.close();
                                Thread.sleep(500);
                                ServerGui.changeColour(s, Color.white);
                                break;
                            case "get_all1":
                                ServerGui.changeColour(s, Color.green);
                                Commands.get_all1(pl.Mo,perem);
                                //System.out.println(Commands.get_all(pl.Mo,perem));
                                Thread.sleep(500);
                                ServerGui.changeColour(s, Color.white);
                                break;
                            case "get_all":
                                ServerGui.changeColour(s, Color.green);
                                os.write(Commands.get_all(pl.Mo,perem).getBytes());
                              //System.out.println(Commands.get_all(pl.Mo,perem));
                                Thread.sleep(500);
                                ServerGui.changeColour(s, Color.white);
                                break;
                            case "get":
                                ServerGui.changeColour(s, Color.green);
                                os.write(Commands.get(pl.Mo, perem).getBytes());
                                Thread.sleep(500);
                                ServerGui.changeColour(s, Color.white);
                                break;
                            case "sort":
                                ServerGui.changeColour(s, Color.green);
                                Commands.sort(pl.Mo).getBytes();
                                Thread.sleep(500);
                                ServerGui.changeColour(s, Color.white);
                                break;
                            case "sortN":
                                ServerGui.changeColour(s, Color.green);;
                                Commands.sortN(pl.Mo).getBytes();
                                Thread.sleep(500);
                                ServerGui.changeColour(s, Color.white);
                                break;
                            case "remove":
                                ServerGui.changeColour(s, Color.green);
                                os.write(Commands.remove(pl.Mo, perem).getBytes());
                                Thread.sleep(500);
                                ServerGui.changeColour(s, Color.white);
                                break;
                            case "count":
                                ServerGui.changeColour(s, Color.green);
                                os.write(Commands.count(pl.Mo, perem).getBytes());
                                Thread.sleep(500);
                                ServerGui.changeColour(s, Color.white);
                                break;
                            case "remove_last":
                                ServerGui.changeColour(s, Color.green);
                                Commands.removeLast(pl.Mo).getBytes();
                                Thread.sleep(500);
                                ServerGui.changeColour(s, Color.white);
                                break;
                            case "remove_all":
                                ServerGui.changeColour(s, Color.green);
                                os.write(Commands.removeAll(pl.Mo, perem).getBytes());
                                Thread.sleep(500);
                                ServerGui.changeColour(s, Color.white);
                                break;
                            case "add":
                                ServerGui.changeColour(s, Color.green);
                               os.write(Commands.add(pl.Mo,perem).getBytes());
                                Thread.sleep(500);
                                ServerGui.changeColour(s, Color.white);
                                break;
                            case "add_if_min":
                                ServerGui.changeColour(s, Color.green);
                                os.write(Commands.add_if_min(pl.Mo,perem).getBytes());
                                Thread.sleep(500);
                                ServerGui.changeColour(s, Color.white);
                                break;
                            case "save":
                                ServerGui.changeColour(s, Color.green);
                                Commands.write(pl.Mo).getBytes();
                                Thread.sleep(500);
                                ServerGui.changeColour(s, Color.white);
                                break;

                            default:
                                ServerGui.changeColour(s, Color.green);
                                os.write("Unknown command".getBytes());
                                Thread.sleep(500);
                                ServerGui.changeColour(s, Color.white);

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
    public  void banCl(){
        try {
            s.close();
            //JOptionPane.showMessageDialog(null,"Вы забанены!","Ошибка", JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,String.valueOf(e.getStackTrace()),"Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }
}

