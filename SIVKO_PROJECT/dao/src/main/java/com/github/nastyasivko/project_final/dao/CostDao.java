package com.github.nastyasivko.project_final.dao;

import com.github.nastyasivko.project_final.model.Cost;

import java.util.List;

public interface CostDao {
    List<Cost> getListCosts();

    Cost get(long id);

    Long saveCost(Cost cost);

    Cost getCost(Cost cost);
}
