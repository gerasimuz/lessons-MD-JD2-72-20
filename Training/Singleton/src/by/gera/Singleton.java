package by.gera;

public class Singleton {
    private static volatile Singleton instance;

    public static Singleton getInstance(){

            synchronized (Singleton.class) {

                if (instance == null) {
                instance = new Singleton();
            }
        }
        return instance;
    }
}
