package by.gera;

public class MallardDuck extends Duck{
    public MallardDuck(){
        quackBehavior = new Quack();
        flyBehavior = new FlyWithWings();
    }
    public void display(){
        System.out.println("I'm really Mallard Duck");
    }

    @Override
    public void fly() {

    }

    @Override
    public void quack() {

    }
}
