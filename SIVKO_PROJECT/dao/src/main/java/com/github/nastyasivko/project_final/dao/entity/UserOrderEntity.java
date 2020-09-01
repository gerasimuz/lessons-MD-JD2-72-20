package com.github.nastyasivko.project_final.dao.entity;

import javax.persistence.*;

@Cacheable
@Entity
@Table(name = "user_order")
public class UserOrderEntity {

    private Long id;

    private String userLogin;

    private String nameRoom;

    private String numberOfBeds;

    private String dateStart;

    private String dateEnd;

    public UserOrderEntity(Long id, String userLogin, String nameRoom, String numberOfBeds, String dateStart, String dateEnd) {
        this.id = id;
        this.userLogin = userLogin;
        this.nameRoom = nameRoom;
        this.numberOfBeds = numberOfBeds;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
    }

    public UserOrderEntity() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "user_login")
    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
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
