package com.github.nastyasivko.project_final.dao.impl;

import com.github.nastyasivko.project_final.dao.CostDao;
import com.github.nastyasivko.project_final.dao.HotelRoomDao;
import com.github.nastyasivko.project_final.dao.UserAdministratorDao;
import com.github.nastyasivko.project_final.dao.UserOrderDao;
import com.github.nastyasivko.project_final.dao.config.DaoConfig;
import com.github.nastyasivko.project_final.model.Cost;
import com.github.nastyasivko.project_final.model.HotelRoom;
import com.github.nastyasivko.project_final.model.UserOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DaoConfig.class)
@Transactional
public class TestDefaultHotelRoomDao {

    @Autowired
    private HotelRoomDao dao;
    @Autowired
    private UserOrderDao userOrderDao;
    @Autowired
    private CostDao costDao;
    @Autowired
    private UserAdministratorDao administratorDao;


    @BeforeEach
    void init() {
        dao.saveHotelRoom(new HotelRoom(null, "standart", "2", "101"));
        dao.saveHotelRoom(new HotelRoom(null, "suite", "1", "203"));
        dao.saveHotelRoom(new HotelRoom(null, "standart", "3", "103"));
        dao.saveHotelRoom(new HotelRoom(null, "standart", "2", "302"));
        dao.saveHotelRoom(new HotelRoom(null, "honeymoon suite", "2", "110"));

        UserOrder userOrderNew = new UserOrder(null, "usertest", "standart", "2", "2020-10-07", "2020-10-10");
        userOrderDao.saveUserOrder(userOrderNew);
        Cost cost = new Cost(null, 1000);

        costDao.saveCost(new Cost(null, cost.getCost()));

        UserOrder userOrderFromDb = userOrderDao.getUserOrder(new UserOrder(null, userOrderNew.getUserLogin(), userOrderNew.getNameRoom(), userOrderNew.getBeds(), userOrderNew.getDateStart(), userOrderNew.getDateEnd()));

        Long id = administratorDao.saveApprovedOrder(userOrderFromDb, 101, cost);
    }

    @Test
    void testSaveHotelRoom() {
        HotelRoom hotelRoom = new HotelRoom(null, "room", "5", "11111");
        dao.saveHotelRoom(hotelRoom);

        HotelRoom hotelRoomFromEntity = dao.getHotelRoom("11111");

        assertNotNull(hotelRoomFromEntity);
        assertEquals(hotelRoom.getName(), hotelRoomFromEntity.getName());
        assertEquals(hotelRoom.getBed(), hotelRoomFromEntity.getBed());
        assertEquals(hotelRoom.getNumberRoom(), hotelRoomFromEntity.getNumberRoom());
    }

    @Test
    void testUpdateHotelRoom() {
        HotelRoom hotelRoom = new HotelRoom(null, "room", "5", "333333");
        final long id = dao.saveHotelRoom(hotelRoom);
        HotelRoom hotelRoomNew = dao.getHotelRoom("333333");
        dao.updateHotelRoom(hotelRoomNew, "new", "1", "3");
        final HotelRoom newRoom = dao.getHotelRoom("3");

        assertNotNull(id);
        assertEquals("new", newRoom.getName());
        assertEquals("1", newRoom.getBed());
    }

    @Test
    void testUpdateHotelRoomNameNull() {
        HotelRoom hotelRoom = new HotelRoom(null, "room", "5", "1");
        final long id = dao.saveHotelRoom(hotelRoom);
        dao.updateHotelRoom(hotelRoom, "", "1", "");
        final HotelRoom newRoom = dao.getHotelRoom("1");

        assertNotNull(id);
        assertEquals(hotelRoom.getName(), newRoom.getName());
        assertEquals(hotelRoom.getNumberRoom(), newRoom.getNumberRoom());
    }

    @Test
    void testUpdateHotelRoomAllNull() {
        HotelRoom hotelRoom = new HotelRoom(null, "room", "5", "3");
        final long id = dao.saveHotelRoom(hotelRoom);
        dao.updateHotelRoom(hotelRoom, "", "", "");
        final HotelRoom newRoom = dao.getHotelRoom("3");

        assertNotNull(id);
        assertEquals(hotelRoom.getName(), newRoom.getName());
        assertEquals(hotelRoom.getBed(), newRoom.getBed());
        assertEquals(hotelRoom.getNumberRoom(), newRoom.getNumberRoom());
    }

    @Test
    void testGetHotelRoom() {
        HotelRoom hotelRoom = new HotelRoom(null, "room", "5", "4");
        final long id = dao.saveHotelRoom(hotelRoom);

        final HotelRoom hotelRoomFromEntity = dao.getHotelRoom("4");

        assertNotNull(hotelRoomFromEntity);
        assertEquals(hotelRoom.getName(), hotelRoomFromEntity.getName());
        assertEquals(hotelRoom.getBed(), hotelRoomFromEntity.getBed());
        assertEquals(hotelRoom.getNumberRoom(), hotelRoomFromEntity.getNumberRoom());
    }

    @Test
    void getNumberRoomExistOne() {
        UserOrder userOrder = new UserOrder(null, "user", "standart", "2", "2020-10-06", "2020-10-10");


        List<String> numberRoom = dao.getNumberRoom(userOrder.getNameRoom(), userOrder.getBeds(), userOrder.getDateStart(), userOrder.getDateEnd());

        for (int i = 0; i < numberRoom.size(); i++) {
            System.out.println(numberRoom.get(i));
        }
        assertEquals(numberRoom.get(0), "302");
    }

    @Test
    void getNumberRoomExistTwo() {
        UserOrder userOrder = new UserOrder(null, "user", "standart", "2", "2020-10-06", "2020-10-07");


        List<String> numberRoom = dao.getNumberRoom(userOrder.getNameRoom(), userOrder.getBeds(), userOrder.getDateStart(), userOrder.getDateEnd());

        for (int i = 0; i < numberRoom.size(); i++) {
            System.out.println(numberRoom.get(i));
        }
        assertEquals(numberRoom.size(), 2);
    }

    @Test
    void getNumberRoomExistThree() {
        UserOrder userOrder = new UserOrder(null, "user", "standart", "2", "2020-10-10", "2020-10-17");


        List<String> numberRoom = dao.getNumberRoom(userOrder.getNameRoom(), userOrder.getBeds(), userOrder.getDateStart(), userOrder.getDateEnd());

        for (int i = 0; i < numberRoom.size(); i++) {
            System.out.println(numberRoom.get(i));
        }
        assertEquals(numberRoom.size(), 2);
    }

    @Test
    void getNumberRoomExistFour() {
        UserOrder userOrder = new UserOrder(null, "user", "standart", "2", "2020-10-11", "2020-10-13");


        List<String> numberRoom = dao.getNumberRoom(userOrder.getNameRoom(), userOrder.getBeds(), userOrder.getDateStart(), userOrder.getDateEnd());

        for (int i = 0; i < numberRoom.size(); i++) {
            System.out.println(numberRoom.get(i));
        }
        assertEquals(numberRoom.size(), 2);
    }

    @Test
    void getNumberRoomNotExist() {
        UserOrder userOrder = new UserOrder(null, "user", "presidential suite", "2", "2020-10-07", "2020-10-10");
        List<String> numberRoom = dao.getNumberRoom(userOrder.getNameRoom(), userOrder.getBeds(), userOrder.getDateStart(), userOrder.getDateEnd());
        System.out.println(numberRoom);

        assertEquals(numberRoom.size(), 0);
    }


}
