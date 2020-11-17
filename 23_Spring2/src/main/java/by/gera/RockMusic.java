package by.gera;

import org.springframework.stereotype.Component;

@Component("someRockMusic")
public class RockMusic implements Music{
    public String getSong() {
        return "Wind cries Mary";
    }
}
