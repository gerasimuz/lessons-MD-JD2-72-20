package by.gera;

public class selectCommand implements Command{
    Database database;

    public selectCommand(Database database) {
        this.database = database;
    }

    @Override
    public void execute() {
        database.select();
    }
}
