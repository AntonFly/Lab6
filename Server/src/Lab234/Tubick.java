package Lab234;

public class Tubick extends man implements mindOrd {
    Tubick(){
        System.out.println("Персонаж: Тюбик создан!");

    }
    public void sitAtHome(){
        name = "Тюбик";
        System.out.println();
        System.out.print(name+" сидит дома ");
        sex=true;
        printPortret();
        System.out.println();
    }
    public void printPortret(){
        System.out.println("и пишет портреты");

    }
    public void useShablon(String a, String b, String c){
        System.out.println("\nСоздает портрет с помощью шаблона");

    }
    public void drow(String eye, String lip, String nose,int k){
        System.out.println("\nМалышка"+k+" хочет чего-то необычного");
        System.out.println("Тюбик Начинает рисовать");
        System.out.println("Глаза :: "+eye);
        System.out.println("Губы :: "+lip);
        System.out.println("Нос :: "+nose);
        //new portret(String.valueOf(k),eye,nose,lip);

    }
    public void rationsl(){
        System.out.println("Мысли Тюбика: \nВедь можно сделать рационализацию!\nСделаю-ка я трафарет\n");
    }
    public void askMal(Malichka... a ){
        System.out.println("\nТюбик спрашивает пожелания малышки, если они необычные он рисует портрет, если нет то ругается \n  ");
        int k= 1;
        trafaret T=null;
        for (Malichka i:a) {
            if (i.eye=="Большие")
                if (i.lip=="Тонкие")
                    if (i.nose=="Прямой")
                    {
                            if (T==null)
                            {
                                ordinary(k);
                                rationsl();
                                T = new trafaret(i.eye, i.lip, i.nose);
                                T.showTrafaret();
                                T.mkShavblon(i.eye,i.nose,i.lip,i.neck,i.hair,i.eyeBrows,i.chin,i.ears);
                                useShablon(i.eye,i.lip,i.nose);
                            }
                            else{
                                ordinary(k);
                                useShablon(i.eye,i.lip,i.nose);
                            }

                    }
                    else {
                        drow(i.eye, i.lip, i.nose,k);
                    }
                else {
                    drow(i.eye, i.lip, i.nose,k);
                }
            else {
                drow(i.eye, i.lip, i.nose,k);

            }
        k++;
        }
    }

    public  void ordinary(int i){
        System.out.println("\nМалишка"+i+" хочет стандартные параметры\n ");
        System.out.println("Мысли тюбика:");
        System.out.println("Надоели вы все, хотите одного и того же!");


    }

}


