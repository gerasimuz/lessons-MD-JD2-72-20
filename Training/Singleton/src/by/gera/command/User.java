package by.gera.command;

public class User {
    Command start;
    Command stop;

    public User (Command start, Command stop){
            this.start = start;
            this.stop = stop;
    }

    void CommandSart(){
        start.execute();
    }

    void CommandStop(){
        stop.execute();
    }
}
