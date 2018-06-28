package Lab234;


import server.PrimaryKey;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
@Table(name = "Portrets")
public class portret implements Comparable<portret>,Serializable{
    @Column(name = "Location")
    public String LOCATION;

    @PrimaryKey
    @Column(name= "Name")
    public String NAME;

    @Column(name = "Color")
    public String COLOUR;
    @Column(name ="Size")
    public int SIZE;
    @Column(name ="X")
    public int X;
    @Column(name ="Y")
    public int Y;
    @Column(name ="CreationTime")
    transient public LocalDateTime creationTime;
    public portret(){};
//    public portret(String name, String Date, int size, String location,String COLOUR, int X, int Y){
//        this.DATE=Date;
//        this.NAME=name;
//        this.SIZE=size;

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public int getY() {

        return Y;
    }

    public void setY(int y) {
        Y = y;
    }

    public int getX() {

        return X;
    }

    public void setX(int x) {
        X = x;
    }

    public int getSIZE() {

        return SIZE;
    }

    public void setSIZE(int SIZE) {
        this.SIZE = SIZE;
    }

    public String getCOLOUR() {

        return COLOUR;
    }

    public void setCOLOUR(String COLOUR) {
        this.COLOUR = COLOUR;
    }

    public String getNAME() {

        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getLOCATION() {

        return LOCATION;
    }

    public void setLOCATION(String LOCATION) {
        this.LOCATION = LOCATION;
    }



//        this.LOCATION=location;
//        this.X=X;
//        this.Y=Y;
//        this.COLOUR= COLOUR;
//        this.creationTime= ZonedDateTime.now();
//    }

    public portret(String name, int size, String location, String COLOUR, int X, int Y, String create){
        this.NAME=name;
        this.SIZE=size;
        this.LOCATION=location;
        this.X=X;
        this.Y=Y;
        this.COLOUR= COLOUR;
        if(create==null)
            this.creationTime= LocalDateTime.now();
        else
            this.creationTime= LocalDateTime.parse(create);
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