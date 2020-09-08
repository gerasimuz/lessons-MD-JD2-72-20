package fake;

import models.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FakeStorage {
    private static final FakeStorage storage;

    static {
        storage = new FakeStorage();
    }

    private List<User> users;

    private FakeStorage(){
        this.users=new ArrayList<>();
        User user1 = new User ("Gera", "gera1985", LocalDate.parse("1985-10-12"));
        User user2 = new User ("Marsel", "mars", LocalDate.parse("2014-04-20"));
        User user3 = new User ("Oksi", "geriha", LocalDate.parse("1988-11-23"));
    }

     public static FakeStorage storage(){
        return storage;
     }

     public List<User> users(){
        return users;
     }
}
