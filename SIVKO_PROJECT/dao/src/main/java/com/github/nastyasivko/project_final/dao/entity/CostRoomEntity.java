package com.github.nastyasivko.project_final.dao.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cost_room")
public class CostRoomEntity {

    private Long id;

    private List<ApprovedOrderEntity> approvedOrdersEntities = new ArrayList<>(0);

    private Integer cost;

    public CostRoomEntity() {
    }

    public CostRoomEntity(Long id, Integer cost) {
        this.id = id;
        this.cost = cost;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @OneToMany(mappedBy = "costRoomEntity", cascade = CascadeType.ALL)
    public List<ApprovedOrderEntity> getApprovedOrdersEntities() {
        return approvedOrdersEntities;
    }

    public void setApprovedOrdersEntities(List<ApprovedOrderEntity> hotelRooms) {
        this.approvedOrdersEntities = approvedOrdersEntities;
    }

    @Column
    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }


}
