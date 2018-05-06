package client;

public class chicl {
    public static void main(String[] args) {
        try {
        //new Thread(new addcl()).start();
        for(int i=0;i<10;i++){

            new Thread(new dellcl()).start();
            new Thread(new addcl()).start();

        }
    }catch (Exception e){
            e.printStackTrace();
        }
    }
}
