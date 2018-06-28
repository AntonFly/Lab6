package server;
import Lab234.portret;
import com.google.gson.*;

import java.awt.*;
import java.time.ZonedDateTime;

public class Parse {
    public static final Gson GSON= new GsonBuilder().setPrettyPrinting().create();

    static String Name;
    static String Date;
    static int Size;
    static String Location;
    static String COLOUR;
    static int X,Y;
    static String creationTime;

    static public void deserializeXML(String inputStr) throws server.XmlExeption {
       // inputStr=inputStr.trim();
        inputStr=inputStr.replaceAll("[\\s]{2,}", " ");
        int lenght = inputStr.length();
        if (inputStr.charAt(0) == '<' && inputStr.charAt(lenght - 1) == '>') {
            inputStr = inputStr.substring(1, lenght - 2);
            String[] fields = inputStr.split(" ");
            for (String s: fields) {
                String[] parametrs = s.split("=");
                parametrs[1] = parametrs[1].replaceAll(" ", "");
                parametrs[1] = parametrs[1].substring(1, parametrs[1].length() - 1);
                switch (parametrs[0]) {
                    case "COLOUR":
                        COLOUR=parametrs[1];
                        break;
                    case "X":
                        X=Integer.valueOf(parametrs[1]);
                        break;
                    case "Y":
                        Y=Integer.valueOf(parametrs[1]);
                        break;
                    case "NAME":
                        Name = parametrs[1];
                        break;
                    case "DATE":
                        Date = parametrs[1];
                        break;
                    case "SIZE":
                        Size = Integer.valueOf(parametrs[1]);
                        break;
                    case "LOCATION":
                        Location = parametrs[1];
                        break;
                    case  "creationTime":
                        creationTime=(parametrs[1]);
                        break;

                }
                ;
            }


        } else
            throw new server.XmlExeption("RETARD ALERT!!!\nProblem with XML format.\nPleace, delete empty lines and spaces at the begining of the line.");


    }

    ;


    public static String serialize(portret e) {
        String str;
        double size=e.SIZE;
        if (e.SIZE % 1==0){
         str = "<NAME=\"" + e.NAME +  "\" SIZE=\"" + (int)(size) + "\" LOCATION=\"" + e.LOCATION +"\" COLOUR=\""+e.COLOUR+ "\" X=\""+e.X+"\" Y=\""+e.Y+"\" creationTime=\""+e.creationTime+"\"/>\n";
         }
        else{
            str = "<NAME=\"" + e.NAME +  "\" SIZE=\"" + e.SIZE + "\" LOCATION=\"" + e.LOCATION +"\" COLOUR=\""+e.COLOUR+ "\" X=\""+e.X+"\" Y=\""+e.Y+"\" creationTime=\""+e.creationTime+"\"/>\n";
        }

        return str;
    }
    public static String getColourName(Color cl){
        if (cl.equals(Color.red)){
            return "red";
        }
        if (cl.equals(Color.white)){
            return "white";
        }
        if (cl.equals(Color.green)){
            return "green";
        }
        if (cl.equals(Color.yellow)){
            return "yellow";
        }
        if (cl.equals(Color.black)){
            return "black";
        }
        if (cl.equals(Color.pink)){
            return "pink";
        }
        if (cl.equals(Color.blue)){
            return "blue";
        }
        return "null";
    }


//    @Override
//    public String toString() {
//        ;
//    }
    /*static public void deserialaize(String comma) throws server.JasonException {
        int length = comma.length();
        if (comma.charAt(0) == '{' && comma.charAt(length - 1) == '}') {
            comma = comma.substring(1, length - 1);
            String[] atrib = comma.split(",");

            if (atrib.length != 4)
                throw new server.JasonException("Smth wrong with Json comand format");

            String tempToken = "";
            String tempValue = "";

            for (int j = 0; j < atrib.length; j++) {
                length = atrib[j].length();
                tempToken = "";
                tempValue = "";
                int count = 0;

                for (int i = 0; i < length; i++) {
                    if (atrib[j].charAt(i) != '"') {
                        if (atrib[j].charAt(i) != ':') {
                            if (count > 2)
                                tempValue += atrib[j].charAt(i);
                            else tempToken += atrib[j].charAt(i);
                        }
                    } else {
                        count++;
                        if (count == 4) {
                            switch (tempToken) {
                                case "NAME":
                                    Name = tempValue;
                                    break;
                                case "SIZE":
                                    Size = Double.parseDouble(tempValue);
                                    break;
                                case "DATE":
                                    Date = tempValue;
                                    break;
                                case "LOCATION":
                                    Location = tempValue;
                                    break;
                                default:
                                    throw new server.JasonException("Smth wrong with Json comand format");
                            }
                        }
                    }
                }
            }

        } else throw new server.JasonException("Smth wrong with Json comand format");
    }*/



    public static Color getCOLOUR(String COLOUR) {
        switch (COLOUR){
            case "white":
                return Color.white;
            case "red":
                return Color.red;
            case "green":
                return Color.green;
            case "blue":
                return Color.blue;
            case "black":
                return Color.black;
            case "yellow":
                return Color.yellow;
            case "pink":
                return Color.pink;
            default: return Color.BLACK;
    }
    }
}