package server;

public class TimeSave implements Runnable {
    PortretList pl;
    TimeSave(PortretList pl){
        this.pl=pl;
    }
    @Override

    public synchronized void run() {
        while (true) {
            try {
                Thread.sleep(30000);
            } catch (InterruptedException e) {
            }
            Commands.write(pl.Mo);
        }
    }
}
