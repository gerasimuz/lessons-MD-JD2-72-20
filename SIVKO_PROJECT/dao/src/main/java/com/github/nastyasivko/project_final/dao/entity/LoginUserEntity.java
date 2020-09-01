package com.github.nastyasivko.project_final.dao.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Cacheable
@Entity
@Table(name = "login_user")
public class LoginUserEntity {

    private Long id;

    private String login;

    private String password;

    private UserEntity userEntity;

    private Long userId;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_order_room", joinColumns = {@JoinColumn(name = "user_login")},
            inverseJoinColumns = {@JoinColumn(name = "room_id")})
    private List<HotelRoomEntity> hotelRoomEntities = new ArrayList<>();

    public LoginUserEntity(Long id, String login, String password, UserEntity userEntity) {
        this.id = id;
        this.login = login;
        this.password = password;
    }

    public LoginUserEntity() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "user_login", unique = true)
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Column
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "user_id", updatable = false, insertable = false)
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }


}
