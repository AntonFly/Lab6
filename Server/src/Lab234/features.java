package Lab234;

public class features {
    enum eyeBrows{
        longArch
    }
    enum chin{
        withDimple
    }
    enum ears{
        smoll
    }
    enum hair{
        bigCurvy
    }
    enum neck{
        subtle
    }
}

class Eye{
   String characteristic;
   Eye(String a) {
       characteristic = a;
   }
}
class Lip{
    String characteristic;
    Lip(String a) {
        characteristic = a;
    }
}
class Nose{
    public String characteristic;
     Nose(String a) {
        characteristic = a;
    }
}