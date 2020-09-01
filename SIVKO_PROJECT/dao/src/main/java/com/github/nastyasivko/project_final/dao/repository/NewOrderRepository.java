package com.github.nastyasivko.project_final.dao.repository;

import com.github.nastyasivko.project_final.dao.entity.NewOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewOrderRepository extends JpaRepository<NewOrderEntity, Long> {

    List<NewOrderEntity> findByUserloginAndNameRoomAndNumberOfBeds(String userLogin, String numberRoom, String numberOfBeds);
}
