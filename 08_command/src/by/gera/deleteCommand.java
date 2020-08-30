package by.gera;

public class deleteCommand implements Command{
    Database database;

    public deleteCommand(Database database) {
        this.database = database;
    }

    @Override
    public void execute(){
        database.delete();
    }
}
