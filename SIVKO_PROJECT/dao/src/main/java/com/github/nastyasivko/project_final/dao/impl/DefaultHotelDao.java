package com.github.nastyasivko.project_final.dao.impl;

import com.github.nastyasivko.project_final.dao.HotelDao;
import com.github.nastyasivko.project_final.dao.converter.HotelRoomConverter;
import com.github.nastyasivko.project_final.dao.entity.HotelRoomEntity;
import com.github.nastyasivko.project_final.dao.repository.HotelRoomPagingRepository;
import com.github.nastyasivko.project_final.dao.repository.HotelRoomRepository;
import com.github.nastyasivko.project_final.model.HotelRoom;
import com.github.nastyasivko.project_final.model.Room;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DefaultHotelDao implements HotelDao {
    private static final Logger log = LoggerFactory.getLogger(DefaultLoginUserDao.class);


    private final HotelRoomRepository repository;
    private final HotelRoomPagingRepository pagingRepository;

    public DefaultHotelDao(HotelRoomRepository repository, HotelRoomPagingRepository pagingRepository) {
        this.repository = repository;
        this.pagingRepository = pagingRepository;
    }

    @Override
    public List<String> getNameRooms() {
        List<String> listRooms = repository.getGroupByName();
        return listRooms;
    }

    @Override
    public List<Room> getRoomBeds() {
        List<String> listRooms = DefaultHotelDao.this.getNameRooms();
        List<Room> listRoom = new ArrayList<>();
        for (int i = 0; i < listRooms.size(); i++) {
            List<Integer> listBeds = repository.getRoomBed(listRooms.get(i));
            Room room = new Room(listRooms.get(i), listBeds);
            listRoom.add(room);
        }
        return listRoom;
    }

    @Override
    public List<String> getNumberRoom() {
        List<String> listRooms = repository.getNumberRoom();
        return listRooms;
    }

    @Override
    public List<HotelRoom> getRoomForPage(int page) {
        int pageSize = 10;
        Page<HotelRoomEntity> hotelRoomEntityPage = pagingRepository.findAll(PageRequest.of((page - 1), pageSize));
        List<HotelRoomEntity> hotelRoomEntities = new ArrayList<>();
        hotelRoomEntityPage.getContent().forEach(hotelRoomEntities::add);
        List<HotelRoom> hotelRooms = new ArrayList<>();
        try {
            for (int i = 0; i < hotelRoomEntities.size(); i++) {
                hotelRooms.add(HotelRoomConverter.fromEntity(hotelRoomEntities.get(i)));
            }
        } catch (NullPointerException e) {
            log.info("no new order");
            hotelRooms = null;
        }
        return hotelRooms;
    }

    @Override
    public List<HotelRoom> getAllRoom() {
        List<HotelRoomEntity> hotelRoomEntities = repository.findAll();
        return hotelRoomEntities.stream()
                .map(HotelRoomConverter::fromEntity)
                .collect(Collectors.toList());
    }
}
