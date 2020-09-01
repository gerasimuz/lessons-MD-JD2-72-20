package com.github.nastyasivko.project_final.dao.impl;

import com.github.nastyasivko.project_final.dao.CostDao;
import com.github.nastyasivko.project_final.dao.converter.CostConverter;
import com.github.nastyasivko.project_final.dao.entity.CostRoomEntity;
import com.github.nastyasivko.project_final.dao.repository.CostRepository;
import com.github.nastyasivko.project_final.model.Cost;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

public class DefaultCostDao implements CostDao {
    private static final Logger log = LoggerFactory.getLogger(DefaultCostDao.class);

    private final CostRepository repository;

    public DefaultCostDao(CostRepository repository) {
        this.repository = repository;
    }

    @Override
    public Long saveCost(Cost cost) {
        CostRoomEntity costRoomEntity = CostConverter.toEntity(cost);

        repository.save(costRoomEntity);
        log.info("cost {} save", costRoomEntity.getCost());

        return costRoomEntity.getId();
    }

    @Override
    public Cost get(long id) {
        final CostRoomEntity costRoomEntity = repository.getOne(id);
        return CostConverter.fromEntity(costRoomEntity);
    }

    @Override
    public List<Cost> getListCosts() {
        final List<CostRoomEntity> costsList = repository.findByOrderByCostAsc();
        return costsList.stream()
                .map(CostConverter::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public Cost getCost(Cost cost) {
        CostRoomEntity costRoomEntity = repository.findByCost(cost.getCost());
        return CostConverter.fromEntity(costRoomEntity);
    }
}
