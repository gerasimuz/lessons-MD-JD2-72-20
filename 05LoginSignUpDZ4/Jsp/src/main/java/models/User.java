package models;

import java.time.LocalDate;

public class User {
    private String Name;
    private String Password;
    private LocalDate birthDate;

    public User(String name, String password, LocalDate birthDate) {
        Name = name;
        Password = password;
        this.birthDate = birthDate;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
}
