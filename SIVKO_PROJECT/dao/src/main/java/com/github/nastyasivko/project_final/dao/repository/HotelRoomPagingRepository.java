package com.github.nastyasivko.project_final.dao.repository;

import com.github.nastyasivko.project_final.dao.entity.HotelRoomEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface HotelRoomPagingRepository extends PagingAndSortingRepository<HotelRoomEntity, Long> {
    Page<HotelRoomEntity> findAll();
}
