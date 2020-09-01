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
import java.util.List;

import static java.lang.Integer.parseInt;

@Controller
@RequestMapping("/admin")
public class AdminController {

    public static final int NUMBER_ELEMENTS_FOR_ROOM = 10;
    public static final int NUMBER_ELEMENTS_FOR_ORDER = 3;
    private final UserAdministratorDao userAdministratorDao;
    private final CostDao costDao;
    private final HotelRoomDao hotelRoomDao;
    private final UserOrderDao userOrderDao;
    private final HotelDao hotelDao;

    public AdminController(UserAdministratorDao userAdministratorDao, CostDao costDao, HotelRoomDao hotelRoomDao, UserOrderDao userOrderDao, HotelDao hotelDao) {
        this.userAdministratorDao = userAdministratorDao;
        this.costDao = costDao;
        this.hotelRoomDao = hotelRoomDao;
        this.userOrderDao = userOrderDao;
        this.hotelDao = hotelDao;
    }

    @GetMapping()
    public String getPageAdmin(HttpServletRequest rq) {
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

    @GetMapping("/vieworder")
    public String getOrder(HttpServletRequest rq) {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || "anonymousUser".equals(authentication.getPrincipal())) {
            return "signIn";
        }
        rq.removeAttribute("message");
        List<UserOrder> userOrders = userAdministratorDao.getUsersOrders();
        if (userOrders.size() == 0) {
            rq.setAttribute("message", "No new orders");
            return "viewOrder";
        }
        int count = userOrders.size();
        if (count % NUMBER_ELEMENTS_FOR_ORDER == 0) {
            rq.setAttribute("pageCount", (count / NUMBER_ELEMENTS_FOR_ORDER));
        } else {
            rq.setAttribute("pageCount", ((count / NUMBER_ELEMENTS_FOR_ORDER) + 1));
        }
        int pageNumber;
        if (rq.getParameter("page") != null) {
            pageNumber = parseInt(rq.getParameter("page"));
        } else {
            pageNumber = 1;
        }
        List<UserOrder> orderList = userAdministratorDao.getNewOrdersForPage(pageNumber);
        rq.setAttribute("orderList", orderList);
        rq.setAttribute("currentPage", pageNumber);
        return "viewOrder";
    }

    @PostMapping("/vieworder")
    public String setOrder(HttpServletRequest rq, UsernamePasswordAuthenticationToken authentication) {
        String userLogin = rq.getParameter("userLogin");
        String nameRoom = rq.getParameter("nameRoom");
        String bed = rq.getParameter("bed");
        String dateStart = rq.getParameter("dateStart");
        String dateEnd = rq.getParameter("dateEnd");

        UserOrder userOrder = new UserOrder(null, userLogin, nameRoom, bed, dateStart, dateEnd);
        rq.getSession().setAttribute("userOrderAnswer", userOrder);

        return "redirect:/admin/answerfororder";
    }

    @GetMapping("/answerfororder")
    public String getAnswer(HttpServletRequest rq) {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || "anonymousUser".equals(authentication.getPrincipal())) {
            return "signIn";
        }
        try {
            UserOrder userOrder = (UserOrder) rq.getSession().getAttribute("userOrderAnswer");
            List<String> listNumber = hotelRoomDao.getNumberRoom(userOrder.getNameRoom(), userOrder.getBeds(), userOrder.getDateStart(), userOrder.getDateEnd());
            List<Cost> costList = costDao.getListCosts();
            rq.setAttribute("number", listNumber);
            rq.setAttribute("costList", costList);
            return "answerForOrder";
        } catch (NullPointerException e) {
            rq.setAttribute("message", "You didn't choose user order");
            rq.getSession().removeAttribute("userOrderAnswer");
            return "answerForOrder";
        }
    }

    @PostMapping("/answerfororder")
    public String setAnswer(HttpServletRequest rq, UsernamePasswordAuthenticationToken authentication) {
        String userLogin = rq.getParameter("userLogin");
        String nameRoom = rq.getParameter("nameRoom");
        String bed = rq.getParameter("bed");
        String dateStart = rq.getParameter("dateStart");
        String dateEnd = rq.getParameter("dateEnd");
        String[] answer = rq.getParameterValues("answer");
        String[] numberRoom = rq.getParameterValues("numberRoom");
        String[] costRoom = rq.getParameterValues("costs");

        UserOrder userOrderNew = userOrderDao.getUserOrder(new UserOrder(null, userLogin, nameRoom, bed, dateStart, dateEnd));

        if (answer[0].equals("YES")) {
            userAdministratorDao.saveApprovedOrder(userOrderNew, parseInt(numberRoom[0]), new Cost(null, parseInt(costRoom[0])));
            userAdministratorDao.deleteNewOrders(userOrderNew);
        }

        if (answer[0].equals("NO")) {
            userAdministratorDao.saveDeniedOrder(userOrderNew);
            userAdministratorDao.deleteNewOrders(userOrderNew);
        }

        rq.setAttribute("message", "Answer was send");
        rq.getSession().removeAttribute("userOrderAnswer");
        return "answerForOrder";
    }

    @GetMapping("/savecost")
    public String pageUpdateCost(HttpServletRequest rq) {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || "anonymousUser".equals(authentication.getPrincipal())) {
            return "signIn";
        }
        List<Cost> costList = costDao.getListCosts();
        rq.getSession().setAttribute("costList", costList);
        return "saveCost";
    }

    @PostMapping("/savecost")
    public String setSaveCost(HttpServletRequest rq, UsernamePasswordAuthenticationToken authentication) {
        String[] costList = rq.getParameterValues("costList");
        String cost = rq.getParameter("cost");
        if (parseInt(cost) < 0) {
            rq.getSession().setAttribute("message", "Cost can't be negative!");
            return "redirect:/admin/savecost";
        }

        costDao.saveCost(new Cost(null, parseInt(cost)));

        rq.getSession().setAttribute("message", "Save done");
        return "redirect:/admin/savecost";
    }

    @GetMapping("/updateroom")
    public String pageUpdateRoom(HttpServletRequest rq) {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || "anonymousUser".equals(authentication.getPrincipal())) {
            return "signIn";
        }
        rq.removeAttribute("message");
        List<String> numberRoomList = hotelDao.getNumberRoom();
        rq.getSession().setAttribute("numberRoomList", numberRoomList);
        return "updateRooms";
    }

    @PostMapping("/updateroom/save")
    public String setSaveRoom(HttpServletRequest rq, UsernamePasswordAuthenticationToken authentication) {
        String numberRoom = rq.getParameter("numberRoom");
        String nameRoom = rq.getParameter("nameRoom");
        String bed = rq.getParameter("bed");

        HotelRoom room = new HotelRoom(null, nameRoom, bed, numberRoom);
        hotelRoomDao.saveHotelRoom(room);

        rq.getSession().setAttribute("message", "Save done");
        return "redirect:/admin/updateroom";
    }

    @PostMapping("/updateroom/update")
    public String setUpdateRoom(HttpServletRequest rq, UsernamePasswordAuthenticationToken authentication) {
        String[] numberRoomOld = rq.getParameterValues("listNumberOld");
        String numberRoom = rq.getParameter("numberRoomUpdate");
        String nameRoom = rq.getParameter("nameRoomUpdate");
        String bed = rq.getParameter("bedUpdate");

        HotelRoom oldHotelRoom = hotelRoomDao.getHotelRoom(numberRoomOld[0]);
        hotelRoomDao.updateHotelRoom(oldHotelRoom, nameRoom, bed, numberRoom);

        rq.getSession().setAttribute("message", "Update done");
        return "redirect:/admin/updateroom";
    }

    @PostMapping("/updateroom/delete")
    public String setDeleteRoom(HttpServletRequest rq, UsernamePasswordAuthenticationToken authentication) {
        String[] numberRoomOld = rq.getParameterValues("numberRoomOld");

        HotelRoom oldHotelRoom = hotelRoomDao.getHotelRoom(numberRoomOld[0]);
        hotelRoomDao.deleteHotelRoom(oldHotelRoom);

        rq.getSession().setAttribute("message", "Delete done");
        return "redirect:/admin/updateroom";
    }

    @GetMapping("/allroom")
    public String getAllRoom(HttpServletRequest rq) {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || "anonymousUser".equals(authentication.getPrincipal())) {
            return "signIn";
        }
        List<HotelRoom> hotelRooms = hotelDao.getAllRoom();
        int count = hotelRooms.size();
        if (count % NUMBER_ELEMENTS_FOR_ROOM == 0) {
            rq.setAttribute("pageCount", (count / NUMBER_ELEMENTS_FOR_ROOM));
        } else {
            rq.setAttribute("pageCount", ((count / NUMBER_ELEMENTS_FOR_ROOM) + 1));
        }
        int pageNumber;
        if (rq.getParameter("page") != null) {
            pageNumber = parseInt(rq.getParameter("page"));
        } else {
            pageNumber = 1;
        }
        List<HotelRoom> hotelRoomList = hotelDao.getRoomForPage(pageNumber);
        rq.setAttribute("hotelRooms", hotelRoomList);
        rq.setAttribute("currentPage", pageNumber);
        return "allRoom";
    }

    @GetMapping("/approvedorder")
    public String getApprovedOrders(HttpServletRequest rq) {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || "anonymousUser".equals(authentication.getPrincipal())) {
            return "signIn";
        }
        List<AnswerUserOrder> userOrders = userAdministratorDao.getApprovedOrder();
        if (userOrders.size() == 0) {
            rq.setAttribute("noanswer", "Not approved orders");
            return "approvedOrder";
        }
        rq.setAttribute("userOrders", userOrders);
        return "approvedOrder";
    }
}
