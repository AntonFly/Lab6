package client.Lab234;

public  class trafaret implements mindTraf {
    String eye,nose,lip;
    public void showTrafaret() {
        System.out.println();
        System.out.println("Мысли тюбика:");
        System.out.println("Трафарет готов, можно сделать парочку шаблонов, и тогда будет значительно проще выполнять заказы ");
        System.out.println();
    }
    trafaret(String a, String  b, String c){
            eye = a;
            lip =b;
            nose =c;
            System.out.println("Предмет: \"Трафарет\" создан!");

        }
    public void mkShavblon(String a, String b, String c, features.neck d, features.hair e, features.eyeBrows f, features.chin g, features.ears h){
       shablon sh=new shablon(a,b,c,d,e,f,g,h);



    }
}

class paper{
    paper(){
        System.out.println("Предмет: \"Бумага\" создан!");
    }
}

class shablon{
    shablon(String a, String b, String c, features.neck d, features.hair e, features.eyeBrows f, features.chin g, features.ears h){
        String eye, nose, lip;
        eye=a;
        nose=b;
        lip=c;
        features.neck neck=d;
        features.hair hair= e;
        features.eyeBrows eyeBrows= f;
        features.chin chin= g;
        features.ears ears= h;
        System.out.println("Предмет: \"Шаблон\" создан!\nсо следующими параметрами:");
        System.out.println(a+" глаза");
        System.out.println(b+" нос");
        System.out.println(c+" губы");
        switch (d){
            case subtle:
                System.out.println("Шея тонкая");
        }
        switch (e){
            case bigCurvy:
                System.out.println("Пышные волосы");
        }
        switch (f){
            case longArch:

        }
        switch (g){
            case withDimple:
                System.out.println("Подбородок с ямочкой");
        }
        switch (h){
            case smoll:
                System.out.println("Маленькие уши");
        }
    }

}
