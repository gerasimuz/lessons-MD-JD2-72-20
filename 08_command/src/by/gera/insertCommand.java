package by.gera;

public class insertCommand implements Command{
    Database database;

    public insertCommand (Database database){
        this.database=database;
    }
    @Override
    public void execute() {
        database.insert();
    }
}
