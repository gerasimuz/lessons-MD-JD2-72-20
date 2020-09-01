package com.github.nastyasivko.project_final.dao.repository;

import com.github.nastyasivko.project_final.dao.entity.DeniedOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeniedOrderRepository extends JpaRepository<DeniedOrderEntity, Long> {
    List<DeniedOrderEntity> findByIdUserOrder(Long userId);
}
