package com.github.nastyasivko.project_final.dao.entity;

import javax.persistence.*;

@Entity
@Table(name = "new_order")
public class NewOrderEntity {

    private Long id;

    private String userlogin;

    private String nameRoom;

    private String numberOfBeds;

    private String dateStart;

    private String dateEnd;

    public NewOrderEntity(Long id, String userlogin, String nameRoom, String numberOfBeds, String dateStart, String dateEnd) {
        this.id = id;
        this.userlogin = userlogin;
        this.nameRoom = nameRoom;
        this.numberOfBeds = numberOfBeds;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
    }

    public NewOrderEntity() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "user_login")
    public String getUserlogin() {
        return userlogin;
    }

    public void setUserlogin(String userlogin) {
        this.userlogin = userlogin;
    }

    @Column(name = "name_room")
    public String getNameRoom() {
        return nameRoom;
    }

    public void setNameRoom(String nameRoom) {
        this.nameRoom = nameRoom;
    }

    @Column(name = "number_of_beds")
    public String getNumberOfBeds() {
        return numberOfBeds;
    }

    public void setNumberOfBeds(String numberOfBeds) {
        this.numberOfBeds = numberOfBeds;
    }

    @Column(name = "date_start")
    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    @Column(name = "date_end")
    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }
}
