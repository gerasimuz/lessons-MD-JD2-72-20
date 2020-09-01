package com.github.nastyasivko.project_final.model;

public class Cost {
    private Long id;
    private Integer cost;

    public Cost(Long id, Integer cost) {
        this.id = id;
        this.cost = cost;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }
}
