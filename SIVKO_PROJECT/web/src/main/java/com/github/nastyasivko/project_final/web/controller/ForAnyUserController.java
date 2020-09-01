package com.github.nastyasivko.project_final.web.controller;

import com.github.nastyasivko.project_final.service.SecurityLoginUser;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping
public class ForAnyUserController {

    private final SecurityLoginUser securityLoginUser;

    public ForAnyUserController(SecurityLoginUser securityLoginUser) {
        this.securityLoginUser = securityLoginUser;
    }

    @GetMapping("/")
    public String startPage() {
        return "index";
    }

    @GetMapping("/viewroom")
    public String getForRoom() {
        return "viewRoom";
    }

    @GetMapping("/customlogout")
    public String doGet(HttpServletRequest rq) {
        SecurityContextHolder.clearContext();
        try {
            rq.logout();
        } catch (ServletException e) {
            throw new RuntimeException();
        }
        return "index";
    }

}
