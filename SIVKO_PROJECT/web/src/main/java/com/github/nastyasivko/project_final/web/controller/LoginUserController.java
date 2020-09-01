package com.github.nastyasivko.project_final.web.controller;

import com.github.nastyasivko.project_final.dao.*;
import com.github.nastyasivko.project_final.model.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

import static java.lang.Integer.parseInt;

@Controller
@RequestMapping("/pageuser")
public class LoginUserController {

    private final HotelDao hotelDao;
    private final UserOrderDao userOrderDao;
    private final NewOrderDao newOrderDao;
    private final UserDao userDao;
    private final LoginUserDao loginUserDao;

    public LoginUserController(HotelDao hotelDao, UserOrderDao userOrderDao, NewOrderDao newOrderDao, UserDao userDao, LoginUserDao loginUserDao) {
        this.hotelDao = hotelDao;
        this.userOrderDao = userOrderDao;
        this.newOrderDao = newOrderDao;
        this.userDao = userDao;
        this.loginUserDao = loginUserDao;
    }

    @GetMapping()
    public String startPage(HttpServletRequest rq) {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || "anonymousUser".equals(authentication.getPrincipal())) {
            return "signIn";
        }
        LoginUser user = (LoginUser) rq.getSession().getAttribute("authUser");
        if (user.getLogin().equals("admin")) {
            return "admin";
        } else {
            return "pageUser";
        }
    }

    @PostMapping()
    public String updatePassword(HttpServletRequest rq, UsernamePasswordAuthenticationToken authentication) {
        String passwordOld = rq.getParameter("oldPassword");
        String password = rq.getParameter("password");
        LoginUser user = (LoginUser) rq.getSession().getAttribute("authUser");
        loginUserDao.updatePassword(user.getLogin(), password);
        LoginUser userNew = new LoginUser(user.getId(), user.getLogin(), password, user.getUserId());
        rq.getSession().setAttribute("authUser", userNew);
        rq.setAttribute("donePassword", "You password was update");
        return "pageUser";
    }

    @GetMapping("/userbuy")
    public String getUserOrder(HttpServletRequest rq) {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || "anonymousUser".equals(authentication.getPrincipal())) {
            return "signIn";
        }
        List<Room> listRoom = hotelDao.getRoomBeds();
        rq.setAttribute("listRooms", listRoom);
        return "userBuy";
    }

    @PostMapping("/userbuy")
    public String setUserOrder(HttpServletRequest rq, UsernamePasswordAuthenticationToken authentication) {
        String[] roomName = rq.getParameterValues("listName");
        String[] roomBed = rq.getParameterValues("listBed");
        String dateStart = rq.getParameter("dateStart");
        String dateEnd = rq.getParameter("dateEnd");
        String[] strStart;
        String[] strEnd;
        String delimeter = "-";
        strStart = dateStart.split(delimeter);
        strEnd = dateEnd.split(delimeter);
        if ((parseInt(strStart[1]) == parseInt(strEnd[1])) && (parseInt(strStart[2]) >= parseInt(strEnd[2]))) {
            List<Room> listRoom = hotelDao.getRoomBeds();
            rq.getSession().setAttribute("listRooms", listRoom);
            rq.setAttribute("error", "Your Date Start is later or equals your Date End");
            return "userBuy";
        }
        LoginUser user = (LoginUser) rq.getSession().getAttribute("authUser");
        UserOrder userOrder = new UserOrder(null, user.getLogin(), roomName[0], roomBed[0], dateStart, dateEnd);

        userOrderDao.saveUserOrder(userOrder);
        newOrderDao.saveNewOrder(userOrder);
        rq.setAttribute("done", "You send order");
        return "pageUser";
    }

    @GetMapping("/myorder")
    public String getAllUserOrders(HttpServletRequest rq) {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || "anonymousUser".equals(authentication.getPrincipal())) {
            return "signIn";
        }
        try {
            LoginUser user = (LoginUser) rq.getSession().getAttribute("authUser");
            List<UserOrder> userOrders = userDao.getAllUserOrder(user.getLogin());
            if (userOrders.size() == 0) {
                rq.setAttribute("noanswer", "You didn't send order. Please, place your order.");
                return "userOrders";
            }
            rq.setAttribute("allOrder", userOrders);
            return "userOrders";
        } catch (NullPointerException e) {
            rq.setAttribute("noanswer", "You don't sign in");
            return "userOrders";
        }
    }

    @GetMapping("/myorderwithanswer")
    public String getUserOrders(HttpServletRequest rq) {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || "anonymousUser".equals(authentication.getPrincipal())) {
            return "signIn";
        }

        LoginUser user = (LoginUser) rq.getSession().getAttribute("authUser");
        List<AnswerUserOrder> userOrders = userDao.getUserOrders(user.getLogin());
        if (userOrders.size() == 0) {
            rq.setAttribute("noanswer", "You order without answer. Please, try again later.");
            return "userOrdersWithAnswer";
        }
        rq.setAttribute("userOrders", userOrders);
        return "userOrdersWithAnswer";
    }

    @PostMapping("/myorderwithanswer")
    public String setPayOrders(HttpServletRequest rq, UsernamePasswordAuthenticationToken authentication) {

        String nameRoom = rq.getParameter("nameRoom");
        String bed = rq.getParameter("bed");
        String dateStart = rq.getParameter("dateStart");
        String dateEnd = rq.getParameter("dateEnd");
        Answer answer = Answer.valueOf(rq.getParameter("answer"));
        String numberRoom = rq.getParameter("numberRoom");
        String costRoom = rq.getParameter("cost");
        String payAnswer = rq.getParameter("payAnswer");

        LoginUser user = (LoginUser) rq.getSession().getAttribute("authUser");
        String userLogin = user.getLogin();
        List<AnswerUserOrder> userOrders = userDao.getUserOrders(userLogin);
        AnswerUserOrder userOrder = new AnswerUserOrder(null, userLogin, nameRoom, bed, dateStart, dateEnd, answer, parseInt(numberRoom), parseInt(costRoom), payAnswer);
        for (int i = 0; i < userOrders.size(); i++) {
            if (userOrders.get(i).getUserLogin().equals(userOrder.getUserLogin()) &&
                    userOrders.get(i).getNameRoom().equals(userOrder.getNameRoom()) &&
                    userOrders.get(i).getBeds().equals(userOrder.getBeds()) &&
                    userOrders.get(i).getDateStart().equals(userOrder.getDateStart()) &&
                    userOrders.get(i).getDateEnd().equals(userOrder.getDateEnd()) &&
                    userOrders.get(i).getAnswer().equals(userOrder.getAnswer()) &&
                    userOrders.get(i).getNumberRoom() == (userOrder.getNumberRoom()) &&
                    userOrders.get(i).getCost().equals(userOrder.getCost()) &&
                    userOrders.get(i).getPayAnswer().equals(userOrder.getPayAnswer())) {
                userOrder.setIdUserOrder(userOrders.get(i).getIdUserOrder());
                rq.getSession().setAttribute("done", "You pay order :)");
                userDao.updatePayAnswerOrder(userOrder);
                break;
            }
        }
        return "redirect:/pageuser/myorderwithanswer";
    }
}
