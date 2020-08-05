package by.gera;

public class MuteQuack implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("silence quack");
    }
}
