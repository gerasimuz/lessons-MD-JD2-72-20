package com.github.nastyasivko.project_final.dao.repository;

import com.github.nastyasivko.project_final.dao.entity.CostRoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CostRepository extends JpaRepository<CostRoomEntity, Long> {
    List<CostRoomEntity> findByOrderByCostAsc();

    CostRoomEntity findByCost(Integer cost);
}
