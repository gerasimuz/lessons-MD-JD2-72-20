package com.github.nastyasivko.project_final.dao.converter;

import com.github.nastyasivko.project_final.dao.entity.CostRoomEntity;
import com.github.nastyasivko.project_final.model.Cost;

public class CostConverter {
    public static CostRoomEntity toEntity(Cost cost) {
        if (cost == null) {
            return null;
        }
        final CostRoomEntity costRoomEntity = new CostRoomEntity();
        costRoomEntity.setId(cost.getId());
        costRoomEntity.setCost(cost.getCost());
        return costRoomEntity;
    }

    public static Cost fromEntity(CostRoomEntity costRoomEntity) {
        if (costRoomEntity == null) {
            return null;
        }
        return new Cost(
                costRoomEntity.getId(),
                costRoomEntity.getCost());
    }
}
