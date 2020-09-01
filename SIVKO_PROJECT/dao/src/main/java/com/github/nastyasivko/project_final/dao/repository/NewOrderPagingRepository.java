package com.github.nastyasivko.project_final.dao.repository;

import com.github.nastyasivko.project_final.dao.entity.NewOrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface NewOrderPagingRepository extends PagingAndSortingRepository<NewOrderEntity, Long> {
    Page<NewOrderEntity> findAll();
}
