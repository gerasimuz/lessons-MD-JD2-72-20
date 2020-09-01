package com.github.nastyasivko.project_final.dao.repository;

import com.github.nastyasivko.project_final.dao.entity.HotelRoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface HotelRoomRepository extends JpaRepository<HotelRoomEntity, Long> {

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("update HotelRoomEntity set name= :name, bed =:bed, numberRoom =:numberRoom where id = :id")
    void updateHotelRoom(@Param("name") String name, @Param("bed") String bed, @Param("numberRoom") String numberRoom, @Param("id") Long id);

    List<HotelRoomEntity> findByNumberRoom(String numberRoom);

    List<HotelRoomEntity> findByNameAndBed(String name, String beds);

    @Query("select h.name from HotelRoomEntity h group by h.name")
    List<String> getGroupByName();

    @Query("select h.bed from HotelRoomEntity h where h.name = :name group by h.bed order by h.bed asc")
    List<Integer> getRoomBed(@Param("name") String name);

    @Query("select h.numberRoom from HotelRoomEntity h order by h.numberRoom asc")
    List<String> getNumberRoom();
}
