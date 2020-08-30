package by.gera;

public class updateCommand implements Command{
    Database database;

    public updateCommand(Database database) {
        this.database = database;
    }

    @Override
    public void execute() {
        database.update();
    }
}
