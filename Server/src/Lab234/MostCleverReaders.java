package Lab234;

public class MostCleverReaders extends man {
    MostCleverReaders(){
        name="Самый умный читатель";
        System.out.println("Побочный персонаж: Самый умный читатель создан!");
    }
    public static class Reality {
        private static int Dimensions = 2;
        public static void setDimensions(int dimensions) throws DimensionException {
            if (dimensions < 1)
                throw new DimensionException(dimensions);
            else
                Dimensions = dimensions;
        }
    }

    public void guess(){
        Gvozdic g=new Gvozdic(1);
        Gvozdic.Dragon drag= g.new Dragon("");
        drag.swallow(drag.name);
        System.out.println(name+" догадался почему не вернулся Гвоздик, он не был проглочен драконом да и дракона никакого не было\n");

    }

}
