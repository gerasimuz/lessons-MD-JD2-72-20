package com.github.nastyasivko.project_final.dao.converter;

import com.github.nastyasivko.project_final.dao.entity.HotelRoomEntity;
import com.github.nastyasivko.project_final.model.HotelRoom;

public class HotelRoomConverter {
    public static HotelRoomEntity toEntity(HotelRoom hotelRoom) {
        if (hotelRoom == null) {
            return null;
        }
        final HotelRoomEntity hotelRoomEntity = new HotelRoomEntity();
        hotelRoomEntity.setId(hotelRoom.getId());
        hotelRoomEntity.setName(hotelRoom.getName());
        hotelRoomEntity.setBed(hotelRoom.getBed());
        hotelRoomEntity.setNumberRoom(hotelRoom.getNumberRoom());
        return hotelRoomEntity;
    }

    public static HotelRoom fromEntity(HotelRoomEntity hotelRoomEntity) {
        if (hotelRoomEntity == null) {
            return null;
        }
        return new HotelRoom(
                hotelRoomEntity.getId(),
                hotelRoomEntity.getName(),
                hotelRoomEntity.getBed(),
                hotelRoomEntity.getNumberRoom());
    }
}
