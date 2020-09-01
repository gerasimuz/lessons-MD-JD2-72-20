package com.github.nastyasivko.project_final.dao.converter;

import com.github.nastyasivko.project_final.dao.entity.HotelRoomEntity;
import com.github.nastyasivko.project_final.model.HotelRoom;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestHotelRoomConverter {
    @Test
    void fromEntityNull() {
        final HotelRoom hotelRoom = HotelRoomConverter.fromEntity(null);
        assertNull(hotelRoom);
    }

    @Test
    void fromEntityNotNull() {
        final HotelRoomEntity hotelRoomEntity = new HotelRoomEntity();
        hotelRoomEntity.setId(1L);
        hotelRoomEntity.setName("standart");
        hotelRoomEntity.setBed("1");
        hotelRoomEntity.setNumberRoom("111");

        final HotelRoom hotelRoom = HotelRoomConverter.fromEntity(hotelRoomEntity);

        assertNotNull(hotelRoom);
        assertEquals(hotelRoom.getId(), hotelRoomEntity.getId());
        assertEquals(hotelRoom.getName(), hotelRoomEntity.getName());
        assertEquals(hotelRoom.getBed(), hotelRoomEntity.getBed());
        assertEquals(hotelRoom.getNumberRoom(), hotelRoomEntity.getNumberRoom());
    }

    @Test
    void toEntityNull() {
        final HotelRoomEntity hotelRoomEntity = HotelRoomConverter.toEntity(null);
        assertNull(hotelRoomEntity);
    }

    @Test
    void toEntityNotNull() {
        final HotelRoom hotelRoom = new HotelRoom(1L, "standart", "1", "111");

        final HotelRoomEntity hotelRoomEntity = HotelRoomConverter.toEntity(hotelRoom);

        assertNotNull(hotelRoomEntity);
        assertEquals(hotelRoom.getId(), hotelRoomEntity.getId());
        assertEquals(hotelRoom.getName(), hotelRoomEntity.getName());
        assertEquals(hotelRoom.getBed(), hotelRoomEntity.getBed());
        assertEquals(hotelRoom.getNumberRoom(), hotelRoomEntity.getNumberRoom());
    }
}
