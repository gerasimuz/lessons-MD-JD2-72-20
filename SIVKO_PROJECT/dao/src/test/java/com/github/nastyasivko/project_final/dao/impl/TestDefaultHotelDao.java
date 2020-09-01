package com.github.nastyasivko.project_final.dao.impl;

import com.github.nastyasivko.project_final.dao.HotelDao;
import com.github.nastyasivko.project_final.dao.HotelRoomDao;
import com.github.nastyasivko.project_final.dao.config.DaoConfig;
import com.github.nastyasivko.project_final.model.HotelRoom;
import com.github.nastyasivko.project_final.model.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DaoConfig.class)
@Transactional
public class TestDefaultHotelDao {
    public static final int PAGE_FOR_ROOM = 1;

    @Autowired
    private HotelRoomDao dao;

    @Autowired
    private HotelDao hotelDao;

    @BeforeEach
    public void init() {
        HotelRoom roomStandartOne = new HotelRoom(null, "standart", "2", "101");
        HotelRoom roomStandartTwo = new HotelRoom(null, "standart", "3", "101");
        HotelRoom roomFamilyRoom = new HotelRoom(null, "family room", "1", "101");
        HotelRoom roomHoneyMoon = new HotelRoom(null, "honeymoon", "4", "101");
        HotelRoom roomSuite = new HotelRoom(null, "suite", "2", "101");
        dao.saveHotelRoom(roomStandartOne);
        dao.saveHotelRoom(roomStandartTwo);
        dao.saveHotelRoom(roomHoneyMoon);
        dao.saveHotelRoom(roomFamilyRoom);
        dao.saveHotelRoom(roomSuite);
    }

    @Test
    void testGetListRooms() {

        List<String> listRooms = hotelDao.getNameRooms();

        assertNotNull(listRooms);
    }

    @Test
    void testGetRoomBeds() {
        List<Room> listRoom = hotelDao.getRoomBeds();

        assertNotNull(listRoom);
        assertEquals(listRoom.size(), 4);
    }

    @Test
    void testGetNumberRoom() {

        List<String> listRooms = hotelDao.getNameRooms();

        assertNotNull(listRooms);
    }

    @Test
    void testGetPage() {
        List<HotelRoom> list = hotelDao.getRoomForPage(PAGE_FOR_ROOM);

        assertEquals(list.size(), 5);
    }

    @Test
    void testGetAllRooms() {

        List<HotelRoom> hotelRooms = hotelDao.getAllRoom();

        assertNotNull(hotelRooms);
    }

}
