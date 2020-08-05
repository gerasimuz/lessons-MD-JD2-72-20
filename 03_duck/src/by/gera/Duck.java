package by.gera;

public abstract class Duck implements FlyBehavior, QuackBehavior{

    QuackBehavior quackBehavior;
    FlyBehavior flyBehavior;

    public Duck(){}

    public abstract void display();

    public void performFly(){
        flyBehavior.fly();
    }

    public void performQuack(){
        quackBehavior.quack();
    }

    public void swim(){
        System.out.println("all ducks float");
    }
}
