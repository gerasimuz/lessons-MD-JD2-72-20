package com.github.nastyasivko.project_final.dao;

import com.github.nastyasivko.project_final.model.HotelRoom;

import java.util.List;

public interface HotelRoomDao {

    Long saveHotelRoom(HotelRoom room);

    void deleteHotelRoom(HotelRoom room);

    void updateHotelRoom(HotelRoom oldHotelRoom, String name, String bed, String numberRoom);

    HotelRoom getHotelRoom(String numberRoom);

    List<String> getNumberRoom(String nameRoom, String bed, String dateStart, String dateEnd);

    String[] getArrayYearMonthDate(String date);
}
