package com.github.nastyasivko.project_final.dao.impl;

import com.github.nastyasivko.project_final.dao.CostDao;
import com.github.nastyasivko.project_final.dao.config.DaoConfig;
import com.github.nastyasivko.project_final.model.Cost;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DaoConfig.class)
@Transactional
public class TestDefaultCostDao {

    @Autowired
    private CostDao dao;

    @BeforeEach
    public void init() {
        dao.saveCost(new Cost(null, 15));
        dao.saveCost(new Cost(null, 20));
        dao.saveCost(new Cost(null, 3));
    }

    @Test
    void testSaveCost() {
        Cost cost = new Cost(null, 100);
        long id = dao.saveCost(cost);
        Cost newCost = dao.get(id);

        assertNotNull(newCost);
        assertEquals(id, newCost.getId());
        assertEquals(cost.getCost(), newCost.getCost());
    }

    @Test
    void testGetListCosts() {
        List<Cost> listCost = dao.getListCosts();

        assertNotNull(listCost);
    }

    @Test
    void testGetCost() {
        Cost cost = new Cost(null, 15);
        Cost newCost = dao.getCost(cost);

        assertNotNull(newCost);
        assertEquals(newCost.getCost(), cost.getCost());
    }
}
