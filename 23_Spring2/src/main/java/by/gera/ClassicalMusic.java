package by.gera;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope("Singleton")
public class ClassicalMusic implements Music{
    public String getSong() {
        return "CHungarian rapsody";
    }
}
