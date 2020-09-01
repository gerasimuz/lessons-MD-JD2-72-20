package com.github.nastyasivko.project_final.dao.converter;

import com.github.nastyasivko.project_final.dao.entity.CostRoomEntity;
import com.github.nastyasivko.project_final.model.Cost;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCostConverter {
    @Test
    void fromEntityNull() {
        final Cost cost = CostConverter.fromEntity(null);
        assertNull(cost);
    }

    @Test
    void fromEntityNotNull() {
        final CostRoomEntity costRoomEntity = new CostRoomEntity();
        costRoomEntity.setId(1L);
        costRoomEntity.setCost(1);

        final Cost cost = CostConverter.fromEntity(costRoomEntity);

        assertNotNull(cost);
        assertEquals(cost.getId(), costRoomEntity.getId());
        assertEquals(cost.getCost(), costRoomEntity.getCost());
    }

    @Test
    void toEntityNull() {
        final CostRoomEntity costRoomEntity = CostConverter.toEntity(null);
        assertNull(costRoomEntity);
    }

    @Test
    void toEntityNotNull() {
        final Cost cost = new Cost(1L, 1);

        final CostRoomEntity costRoomEntity = CostConverter.toEntity(cost);

        assertNotNull(costRoomEntity);
        assertEquals(cost.getId(), costRoomEntity.getId());
        assertEquals(cost.getCost(), costRoomEntity.getCost());
    }
}
