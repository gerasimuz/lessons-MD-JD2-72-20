package by.gera;

public class DatabaseRunner {
    Database database = new Database();
    Developer developer = new Developer(
            new insertCommand(database),
            new updateCommand(database),
            new selectCommand(database),
            new deleteCommand(database)
    );
}
