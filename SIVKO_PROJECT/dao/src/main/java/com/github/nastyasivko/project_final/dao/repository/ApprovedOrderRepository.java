package com.github.nastyasivko.project_final.dao.repository;

import com.github.nastyasivko.project_final.dao.entity.ApprovedOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ApprovedOrderRepository extends JpaRepository<ApprovedOrderEntity, Long> {
    List<ApprovedOrderEntity> findByIdUserOrder(Long userId);

    List<ApprovedOrderEntity> findByNumberRoom(Integer numberRoom);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("update ApprovedOrderEntity set payAnswer= :payAnswer where idUserOrder = :idUserOrder")
    void updatePayAnswer(@Param("idUserOrder") Long idUserOrder, @Param("payAnswer") String payAnswer);
}
