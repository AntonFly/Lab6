package Lab234;

import server.Parse;

import java.awt.*;
import java.io.Serializable;

public class portret implements Comparable<portret>,Serializable{
    public String eye,nose,lip,location,name,date;
public String DATE,LOCATION,NAME;
public String COLOUR;
public int SIZE;
public int X,Y;
    public Double size;
    public portret(String name, String Date, int size, String location,String COLOUR, int X, int Y){
        this.DATE=Date;
        this.NAME=name;
        this.SIZE=size;
        this.LOCATION=location;
        this.X=X;
        this.Y=Y;
        this.COLOUR= COLOUR;
    }
   /* public portret(String name, String eye,String nose,String lip){
        this.eye=eye;
        this.name=name;
        this.location=location;
        this.nose=nose;
        this.lip=lip;
    }*/


    @Override
    public int compareTo(portret p) {
           if(this.SIZE-p.SIZE>0){return +1;}
           else
                if(this.SIZE-p.SIZE<0){return -1;}
                else
                    if(this.SIZE-p.SIZE==0){return 0;}
                    else return 0;
    }
    public int compareTo1(portret p) {
         if( NAME.compareTo(p.NAME)==0){
              return compareTo(p);
         }else
             return NAME.compareTo(p.NAME) ;
    }
}