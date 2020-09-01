package com.github.nastyasivko.project_final.dao;

import com.github.nastyasivko.project_final.model.HotelRoom;
import com.github.nastyasivko.project_final.model.Room;

import java.util.List;

public interface HotelDao {

    List<String> getNameRooms();

    List<Room> getRoomBeds();

    List<String> getNumberRoom();

    List<HotelRoom> getAllRoom();

    List<HotelRoom> getRoomForPage(int page);
}
