package client.Lab234;



public class Gvozdic extends man {
    Gvozdic() {
        System.out.println("Персонаж: Гвоздик создан");
    }
    Gvozdic(int a){}
    private String s;
    public void vozvrat() throws LogicExeption {
        double k = Math.random();
        if (k > 0.5) {
            System.out.println();
            throw new LogicExeption("гвоздик вернулся, а не должен");
        } else {
            System.out.println("\nГвоздик не вернулся\n");
        }


    }

    void work() {
        System.out.println("Гвоздик увлекся работой\n");
    }

    void climb(Tree a)  {
        if (a == null) {
            throw new NullPointerException("Дерева нет, а обязательно должно быть");
        } else {
            System.out.println("Гвоздик залез на дерево и начал работать пилой\n");
            getAdrenalin();
        }
    }
    void workBySaw(Tree a){

          Saw s=new Saw(){
            void print() {
                super.print();
            }
        };
        s.print();
    }
    class Saw{
        void print(){
            System.out.println("Вспомогательный предмет: \"пила\" создан\n");
            System.out.println("Гвоздик пилит дерево\n");
        }
    }

    void getAdrenalin() {
        class Dangerous{
            Dangerous(){
                System.out.println("Ведь это же опасно, а какая малышка устоит перед опасностью\n");
            }
        }
        new Dangerous();
    }

    public class Dragon {
        Dragon(String name) {

            if (name==""){
                this.name=null;
            }else
                this.name=name;
        }
        String name;


        void swallow(String a) {

            if (a != null) {
                System.out.println("\nПерсонаж: Дракон создан\n");
                throw new FantasyExeption("Дракон был лишь выдумкой и никого он не проглатывал");
            }

        }
    }
}

class LogicExeption extends Exception{
    LogicExeption(){

    }
    LogicExeption(String msg) {
        super(msg);
    }


}

class FantasyExeption extends RuntimeException {

    FantasyExeption(String s) {
        super(s);
    }

}

class Tree{
    Tree(){
        System.out.println("Предмет: \"дерево\" создан" );
    }
}

class DimensionException extends Exception {

    private int count;

    DimensionException(int number) {
        count = number;
    }

    void printMessage() {
        System.err.println("Не может быть " + count + " измерений!");
    }
}