package com.github.nastyasivko.project_final.web.controller;

import com.github.nastyasivko.project_final.dao.UserDao;
import com.github.nastyasivko.project_final.model.LoginUser;
import com.github.nastyasivko.project_final.model.User;
import com.github.nastyasivko.project_final.service.SecurityLoginUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/login")
public class LoginController {
    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    private final SecurityLoginUser securityLoginUser;

    private final UserDao userDao;

    public LoginController(SecurityLoginUser securityLoginUser, UserDao userDao) {
        this.securityLoginUser = securityLoginUser;
        this.userDao = userDao;
    }

    @GetMapping()
    public String login() {
        return "login";
    }

    @PostMapping()
    public String login(HttpServletRequest rq) {
        String nameUser = rq.getParameter("name");
        String surname = rq.getParameter("surname");
        String phone = rq.getParameter("phone");
        String login = rq.getParameter("login");
        String password = rq.getParameter("password");
        if (login.equals("admin") || login.equals("Admin") || login.equals("ADMIN")) {
            rq.setAttribute("error", "This login is not write");
            return "login";
        }
        User newUser = new User(null, nameUser, surname, phone);
        LoginUser newLoginUser = new LoginUser(null, login, password, null);

        boolean result = securityLoginUser.dublicateLogin(login);

        if (nameUser.equals("") && surname.equals("") && login.equals("") && password.equals("")) {
            return "login";
        }

        if (nameUser.equals("") || surname.equals("") || login.equals("") || password.equals("")) {
            rq.setAttribute("error", "Please, write down all the forms");
            return "login";
        }

        if (!result) {
            rq.setAttribute("error", "This login is already exists");
            return "login";
        }
        userDao.saveLoginUser(newUser, newLoginUser);

        log.info("user {}{} save", newUser.getName(), newLoginUser.getLogin());

        return "signIn";
    }

}