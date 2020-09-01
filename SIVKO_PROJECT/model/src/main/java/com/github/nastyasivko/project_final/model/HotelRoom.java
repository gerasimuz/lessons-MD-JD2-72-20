package com.github.nastyasivko.project_final.model;

public class HotelRoom {

    private Long id;
    private String name;
    private String bed;
    private String numberRoom;

    public HotelRoom(Long id, String name, String bed, String numberRoom) {
        this.id = id;
        this.name = name;
        this.bed = bed;
        this.numberRoom = numberRoom;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBed() {
        return bed;
    }

    public void setBed(String bed) {
        this.bed = bed;
    }

    public String getNumberRoom() {
        return numberRoom;
    }

    public void setNumberRoom(String numberRoom) {
        this.numberRoom = numberRoom;
    }
}
