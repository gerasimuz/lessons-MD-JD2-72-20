package com.github.nastyasivko.project_final.model;

import java.util.List;

public class Room {

    private String name;
    private List<Integer> listBeds;

    public Room(String name, List<Integer> listBeds) {
        this.name = name;
        this.listBeds = listBeds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getListBeds() {
        return listBeds;
    }

    public void setListBeds(List<Integer> listBeds) {
        this.listBeds = listBeds;
    }
}
