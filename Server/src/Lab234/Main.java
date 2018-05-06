package Lab234;

public class Main {
    public static void Lab34() {
        try {
            MostCleverReaders.Reality.setDimensions(3);
        }
        catch (DimensionException exc) {
            exc.printMessage();
        }
        paper p = new paper();
        Tree tree= new Tree();
        Tubick Tubick=new Tubick();
        Gvozdic Gvozdic=new Gvozdic();
        //reflect(G);
        Malichka M1=new Malichka(1,"Большие","Прямой","Пышные");
        Malichka M2=new Malichka(2,"Большие","С горбинкой","Тонкие");
        Malichka M3=new Malichka(3,"Большие","Прямой","Тонкие");
        Malichka M4=new Malichka(4,"Большие","Прямой","Пышные");
        Malichka M5 =new Malichka(5,"Большие","Прямой","Тонкие");
        MostCleverReaders Reader= new MostCleverReaders();
        try{
        Gvozdic.vozvrat();}
        catch (LogicExeption e){
            System.out.println(e);
        }
        Reader.guess();
        Gvozdic.work();
        try{
            //tree=null;
        Gvozdic.climb(tree);}
        catch (NullPointerException e){
            System.err.println("Дерева нет");
        }
        Gvozdic.workBySaw(tree);
        Tubick.sitAtHome();
        Tubick.askMal(M1,M2,M3,M4,M5);
    }

}
