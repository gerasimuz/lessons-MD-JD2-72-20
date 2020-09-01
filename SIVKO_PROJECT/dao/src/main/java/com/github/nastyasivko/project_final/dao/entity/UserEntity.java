package com.github.nastyasivko.project_final.dao.entity;

import javax.persistence.*;

@Cacheable
@Entity
@Table(name = "user")
public class UserEntity {

    private Long id;

    private String name;

    private String surname;

    private String phone;

    private LoginUserEntity loginUserEntity;

    public UserEntity(Long id, String name, String surname, String phone, LoginUserEntity loginUserEntity) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
    }

    public UserEntity() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Column
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @OneToOne(mappedBy = "userEntity", fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    public LoginUserEntity getLoginUserEntity() {
        return loginUserEntity;
    }

    public void setLoginUserEntity(LoginUserEntity loginUserEntity) {
        this.loginUserEntity = loginUserEntity;
    }

}
