package com.github.nastyasivko.project_final.dao.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "room")
public class HotelRoomEntity {

    private Long id;

    private String name;

    private String bed;

    private String numberRoom;

    @ManyToMany(mappedBy = "hotelRoomEntities", cascade = CascadeType.ALL)
    private List<LoginUserEntity> loginUsersEntities = new ArrayList<>();

    public HotelRoomEntity(Long id, String name, String bed, String numberRoom) {
        this.id = id;
        this.name = name;
        this.bed = bed;
        this.numberRoom = numberRoom;
    }

    public HotelRoomEntity() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "bed")
    public String getBed() {
        return bed;
    }

    public void setBed(String bed) {
        this.bed = bed;
    }

    @Column(name = "number_room")
    public String getNumberRoom() {
        return numberRoom;
    }

    public void setNumberRoom(String numberRoom) {
        this.numberRoom = numberRoom;
    }

}
