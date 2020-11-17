package by.gera.command;


public class CommandStart implements Command{

    private Computer computer;

    public CommandStart (Computer computer){
        this.computer = computer;
    }
    @Override
    public void execute() {
        computer.start();
    }


}
