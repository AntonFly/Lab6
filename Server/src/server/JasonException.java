package server;

public class JasonException extends Exception{
    String msg;
       JasonException(String msg){
        this.msg= msg;

    }
           String Jsret(){
            return msg;
           }
}

class FormatEx extends Exception{
    public void rem_ex(){
        System.out.println("No such element.");
        //System.out.println((char) 27 + "[31mNo such element. " + (char)27 + "[0m");
    }
    public void form_ex(){
        System.out.println((char) 27 + "[31mERROR command's format. " + (char)27 + "[0m");
    }
}

class CountEx extends Exception{
    public void coun_err(){
        System.out.println((char) 27 + "[31mERROR!!! Unknown command " + (char)27 + "[0m");
    }
}