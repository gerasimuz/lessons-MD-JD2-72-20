package com.github.nastyasivko.project_final.web.controller;


import com.github.nastyasivko.project_final.dao.LoginUserDao;
import com.github.nastyasivko.project_final.model.LoginUser;
import com.github.nastyasivko.project_final.model.Role;
import com.github.nastyasivko.project_final.service.SecurityLoginUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping
public class SignInController {
    private static final Logger log = LoggerFactory.getLogger(SignInController.class);

    private final SecurityLoginUser securityLoginUser;

    private final LoginUserDao loginUserDao;

    public SignInController(SecurityLoginUser securityLoginUser, LoginUserDao loginUserDao) {
        this.securityLoginUser = securityLoginUser;
        this.loginUserDao = loginUserDao;
    }

    @GetMapping("/signin")
    public String pageSignIn(HttpServletRequest rq) {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || "anonymousUser".equals(authentication.getPrincipal())) {
            return "signIn";
        }
        LoginUser user = (LoginUser) rq.getSession().getAttribute("authUser");
        if (user.getLogin().equals("admin")) {
            return "admin";
        }
        return "pageUser";
    }

    @PostMapping("/signin")
    public String login(HttpServletRequest rq) {
        String login = rq.getParameter("login");
        String password = rq.getParameter("password");
        if (login.equals("admin") && password.equals("admin")) {
            rq.getSession().setAttribute("authUser", new LoginUser(login, password, Role.ADMIN));
            Authentication auth = new UsernamePasswordAuthenticationToken(new LoginUser(login, password, Role.ADMIN), null, getAuthorities(Role.ADMIN));
            SecurityContextHolder.getContext().setAuthentication(auth);
            return "admin";
        }

        LoginUser user = loginUserDao.findLoginUser(login);

        if (login.equals("") && password.equals("")) {
            return "signIn";
        }

        if (user == null) {
            rq.setAttribute("error", "You don't sign up");
            return "signIn";
        } else {
            if
            (!user.getPassword().equals(password)) {
                rq.setAttribute("error", "Wrong password");
                return "signIn";
            } else {
                log.info("user {}{} logged", user.getLogin());
                user.setRole(Role.USER);
                rq.getSession().setAttribute("authUser", user);
                Authentication auth = new UsernamePasswordAuthenticationToken(user, null, getAuthorities(Role.USER));
                SecurityContextHolder.getContext().setAuthentication(auth);

                return "pageUser";
            }
        }
    }

    private List<GrantedAuthority> getAuthorities(Role admin) {
        if (admin == Role.ADMIN){
            return Collections.singletonList((GrantedAuthority) () -> "ROLE_ADMIN");
        } else {
            return Collections.singletonList((GrantedAuthority) () -> "ROLE_USER");
        }
    }
}
