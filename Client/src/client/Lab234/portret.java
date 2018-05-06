package client.Lab234;

public class portret implements Comparable<portret>{
    public String eye,nose,lip,location,name,date;

    public Double size;
    public portret(String name, String Date, Double size, String location){
        this.date=Date;
        this.name=name;
        this.size=size;
        this.location=location;
    }
    public portret(String name, String eye,String nose,String lip){
        this.eye=eye;
        this.name=name;
        this.location=location;
        this.nose=nose;
        this.lip=lip;
    }


    @Override
    public int compareTo(portret p) {
           if(this.size-p.size>0){return +1;}
           else
                if(this.size-p.size<0){return -1;}
                else
                    if(this.size-p.size==0){return 0;}
                    else return 0;
    }
    public int compareTo1(portret p) {
         return name.compareTo(p.name);
    }
}