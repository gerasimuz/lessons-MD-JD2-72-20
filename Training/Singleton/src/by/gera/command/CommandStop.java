package by.gera.command;

public class CommandStop implements Command{
    private Computer computer;

    public CommandStop (Computer computer){
        this.computer = computer;
    }

    @Override
    public void execute() {
        computer.stop();
    }
}
