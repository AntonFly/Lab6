package Lab234;

public class Malichka extends man {
    String eye, nose, lip;
    features.neck neck= features.neck.subtle;
    features.hair hair= features.hair.bigCurvy;
    features.eyeBrows eyeBrows= features.eyeBrows.longArch;
    features.chin chin= features.chin.withDimple;
    features.ears ears= features.ears.smoll;
    Malichka(int i, String a, String b, String c) {
        sex = false;
        System.out.println("Персонаж: Малышка"+i+" создан");
        eye=a;
        nose=b;
        lip=c;
        Eye e=new Eye(a);
        Nose n=new Nose(b);
        Lip l=new Lip(c);
    }

    void wantPortret(String a, String b, String c) {
        System.out.println(toString());
        System.out.print("Хочу ");
        System.out.print(a+" нос ");
        System.out.print(b+" губы ");
        System.out.println(c+"глаза ");
        System.out.println();
    }







    @Override
    public int hashCode(){
        int hashcode =1;
            if (eye=="Большие"){
                hashcode*=2;
            }
            if (eye=="Маленькие"){
                hashcode*=3;
            }
        return hashcode;};



    @Override
    public String toString() {
        return ("Малышка хочет портрет:");
    }
}










