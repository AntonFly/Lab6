package server;

import Lab234.portret;
import com.google.gson.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

//import java.util.CopyOnWriteArrayList;

public  class Commands {
    public static final Gson GSON= new Gson();
    public static String get_all(CopyOnWriteArrayList<portret> Pl, String data){
        String output="";
        sortN(Pl);
        if (data.equals("")){
        for (portret pl:Pl) {
             output=output+Parse.serialize(pl);

        }
            output=output+"\nTotal:"+Pl.size()+".";

    }else{
             output= "ERROR command's format.";
        }
        sort(Pl);
        return output;
    }
    public static String get_all1(CopyOnWriteArrayList<portret> Pl, String data){
        String output="";
        sortN(Pl);
        try{Iterator<portret> iter = Pl.iterator();
        if (data.equals("")){
            while (iter.hasNext()){
                output=output+Parse.serialize(iter.next());

            }
            output=output+"\nTotal:"+Pl.size()+".";

        }else{
            output= "ERROR command's format.";
        }
        sort(Pl);
        ;}
        catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }
    public static String get(CopyOnWriteArrayList<portret> Pl, String data)throws FormatEx{

        if (Commands.checkString(data)){
            if (Integer.valueOf(data)>Pl.size()){
                throw new FormatEx();
            }else
        return Parse.serialize(Pl.get(Integer.valueOf(data)-1));
    }
    else
        throw new FormatEx();
    }
    public synchronized static String sort(CopyOnWriteArrayList<portret> Pl){
        Pl.sort(portret::compareTo);
        return "Collection is sorted.";
    }
    public synchronized static String sortN(CopyOnWriteArrayList<portret> Pl){
        Pl.sort(portret::compareTo1);
        return "Collection is sorted by name.";
    }
    public synchronized static String remove(CopyOnWriteArrayList<portret> Pl, String data) throws FormatEx{
        if( Commands.checkString(data)){if (Integer.valueOf(data)>Pl.size()){
               throw new FormatEx();
        }else{
        //System.out.println(Parse.serialize(Pl.get(Integer.valueOf(data)-1)));
        portret o=Pl.get(Integer.valueOf(data)-1);
        Pl.remove(o);
        ServerGui.initPrtTable();
        return "Object number "+Integer.valueOf(data)+" is deleted";}
        }
        else
            throw new FormatEx();

    }
    public static String count(CopyOnWriteArrayList<portret> Pl, String data){
        boolean f=true;
        if(data.equals(null)||data.equals("")){f=false;}
        if (f){
            return ("ERROR command's format.");
        }else{
            ServerGui.initClientTable();
            return String.valueOf(Pl.size());
        }
    }
    /**
     * Заполняет коллекцию объектами типа portret 
     * 
     * @param Pl - заполняемая коллекция
     * @throws XmlExeption -исключечение, вызываемое при неверном формате входного файла (формат файла XML)
     */
    public synchronized static String read(CopyOnWriteArrayList<portret> Pl) throws XmlExeption{
       // System.out.println("1");
        Pl.clear();
            try  {
                BufferedReader br = new BufferedReader(new FileReader(System.getenv("objects")));
                String s1;

                while ((s1 = br.readLine()) != null) {
                    Parse.deserializeXML(s1);
                    portret portret=new portret(Parse.Name, Parse.Date, Parse.Size, Parse.Location,Parse.COLOUR, Parse.X, Parse.Y );
                    Pl.add(portret);
//                    String[] data={
//                            portret.NAME,
//                            portret.DATE,
//                            String.valueOf(portret.SIZE),
//                            portret.LOCATION,
//                            portret.COLOUR,
//                            String.valueOf(portret.X),
//                            String.valueOf(portret.Y)
//                    };
//                    FramesPanels.model.addRow(data);
//                    FramesPanels.AddRow(portret);
                }
            }

            /*catch (IOException e){
                System.out.println("Не удается найти указанный файл");
                System.exit(0);
            }*/
            catch (Exception e){
                e.printStackTrace();
            }



       Pl.sort(portret::compareTo);
        return "";

    }

    /**
     *  Удалаяет из коллекции все объекты пораметры которых соответствуют заданным
     *  
     * @param Pl - коллекция, из которой происходит удаление
     * @param data - параметры удаляемых объектов
     * @throws JasonException - исключение, вызываемое при неправильном формате параметров (Формат параметров Json)
     */
    public synchronized static  String removeAll(CopyOnWriteArrayList<portret> Pl , String data) throws JasonException{
        boolean f= false;
        if (data.equals("")){
            return "Unknown command.";
        }
        else {
            portret pr=GSON.fromJson(data, portret.class);
                    for (int i=0;i<Pl.size();){
                    portret ptr=Pl.get(i);
                    if (ptr.NAME.equals(pr.NAME)&& ptr.SIZE==(pr.SIZE)&&ptr.DATE.equals(pr.DATE)&&ptr.LOCATION.equals(pr.LOCATION) ) {
                        Pl.remove(i);
                     f = true;
                    }else
                        i++;


                    }
                    if (f==true){
                        ServerGui.initPrtTable();
                        return ("Elements are removed");
                    }
                    else
                        return ("No such elements");
        }
    }

    /**
     * Добавляет в коллекцию объект с заданными параметрами
     * 
     * @param Pl - коллекция, в которую добавляется объект 
     * @param data - параметры объекта
     * @throws JasonException - исключение, вызываемое при неправильном формате параметров (Формат параметров Json)
     */
    public synchronized static  String add(CopyOnWriteArrayList<portret> Pl, String data) throws JasonException {
        String output;
            portret pr=GSON.fromJson(data, portret.class);
           // Parse.deserialaize(data);
            //portret prt=new portret(Parse.Name,Parse.Date,Parse.Size,Parse.Location);
            //System.out.println(pr.NAME+" "+pr.DATE+" "+pr.SIZE+" "+pr.LOCATION);
            Pl.add(pr);
            output="Object is added";
            //Pl.sort(portret::compareTo);
            ServerGui.AddRowPrt(pr);
        return output;

    }

    /**
     * Добавляет объект в коллекцию при условии, если его размер наименьший
     * @param Pl - коллекция, в которую добавляется объект 
     * @param data - параметры объекта
     */
    public synchronized static String add_if_min(CopyOnWriteArrayList<portret> Pl, String data) {
        String output="null";


            portret pr=GSON.fromJson(data, portret.class);
            Double min=2147483647.;
            for(int i=0; i<Pl.size();i++){
                if (Pl.get(i).SIZE<min){
                    min=(double)Pl.get(i).SIZE;
                }

            }
            if(pr.SIZE<min){
                Pl.add(pr);
                output= "Object is added";
              //  Pl.sort(portret::compareTo);
                ServerGui.AddRowPrt(pr);

            }
            else {
                output="Object is not min";
            }



        return output;
    }

    /**
     * Сохраняет коллекцию в файл 
     * @param Pl -сохраняемая коллекция
     */
    public synchronized static String write(CopyOnWriteArrayList<portret> Pl){
        //System.out.println("1");
        try(PrintWriter pw = new PrintWriter(System.getenv("objects")))
        {   int i=0;
            for (portret o: Pl) {

                pw.print(Parse.serialize(o));

            }
            return "Collection has saved";

        }
        catch(IOException ex){
            //System.out.println("ex");
            System.out.println(ex.getMessage());
        }
        //System.out.println("1");
        return "Collection has saved";
    }

    /**
     * Удаляет последний объект коллекции
     * @param Pl -коллекция из которой удаляется объект
     */
    public synchronized static String removeLast(CopyOnWriteArrayList<portret> Pl){
        int length=Pl.size();
        Pl.remove(length-1);
        ServerGui.initPrtTable();
        return ("Element is removed");
    }

    /**
     * Вызов помощи
     */
    public synchronized static void help(){
        System.out.println("Press 'q' to save and exit\n");
        System.out.println("\"save\" to save current collection\n");
        System.out.println('"'+"add_if_min {element}"+'"'+" to add element if value of size is minimal\n");
        System.out.println('"'+"remove_all {element}"+'"'+" to delete all elements equal to given\n");
        System.out.println('"'+"remove_last"+'"'+" to delete last element of collection\n");
        System.out.println('"'+"start"+'"'+" to launch a fairytale program\n");
        System.out.println("\"count\" to show a number of items in the collection\n");
        System.out.println("\"get №\" to show an element with introduced index\n");
        System.out.println("\"get_all\" to show all elements of collection\n");
        System.out.println("\"remove №\" to delete an element with introduced index\n");
        System.out.println("\"sort\" to sort collection\n");
        System.out.println("\"sortN\" to sort collection by name\n");
        System.out.println("\"add\" to sort collection by name\n");

    }
    public static boolean checkString(String string) {
        if (string == null) return false;
        return string.matches("^-?\\d+$");
    }

}




