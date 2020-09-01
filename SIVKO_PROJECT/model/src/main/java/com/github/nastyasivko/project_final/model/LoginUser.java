package com.github.nastyasivko.project_final.model;

public class LoginUser {

    private Long id;
    private String login;
    private String password;
    private Long userId;
    private Role role;

    public LoginUser(String login, String password, Role role) {
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public LoginUser(Long id, String login, String password, Long userId) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.userId = userId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
