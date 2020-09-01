package com.github.nastyasivko.project_final.dao.entity;

import com.github.nastyasivko.project_final.model.Answer;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

@Data
@AllArgsConstructor
@Entity
@Table(name = "approved_order")
public class ApprovedOrderEntity extends Order {

    private Answer answer;

    private Integer numberRoom;

    private CostRoomEntity costRoomEntity;

    private String payAnswer;

    public ApprovedOrderEntity(Long id, Long userId, String dateStart, String dateEnd, Answer answer, Integer numberRoom, CostRoomEntity costRoomEntity, String payAnswer) {
        super(id, userId, dateStart, dateEnd);
        this.answer = answer;
        this.numberRoom = numberRoom;
        this.costRoomEntity = costRoomEntity;
        this.payAnswer = payAnswer;
    }

    public ApprovedOrderEntity() {
    }

    @Enumerated(EnumType.STRING)
    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    @Column(name = "number_room")
    public Integer getNumberRoom() {
        return numberRoom;
    }

    public void setNumberRoom(Integer numberRoom) {
        this.numberRoom = numberRoom;
    }

    @ManyToOne
    @JoinColumn(name = "cost_room")
    public CostRoomEntity getCostRoomEntity() {
        return costRoomEntity;
    }

    public void setCostRoomEntity(CostRoomEntity costRoomEntity) {
        this.costRoomEntity = costRoomEntity;
    }

    @Column(name = "pay_answer")
    public String getPayAnswer() {
        return payAnswer;
    }

    public void setPayAnswer(String payAnswer) {
        this.payAnswer = payAnswer;
    }

    @Override
    public String toString() {
        return "ApprovedOrderEntity{" +
                "answer=" + answer +
                ", numberRoom=" + numberRoom +
                ", costRoomEntity=" + costRoomEntity +
                ", payAnswer='" + payAnswer + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ApprovedOrderEntity)) return false;
        if (!super.equals(o)) return false;
        ApprovedOrderEntity that = (ApprovedOrderEntity) o;
        return getAnswer() == that.getAnswer() &&
                Objects.equals(getNumberRoom(), that.getNumberRoom()) &&
                Objects.equals(getCostRoomEntity(), that.getCostRoomEntity()) &&
                Objects.equals(getPayAnswer(), that.getPayAnswer());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getAnswer(), getNumberRoom(), getCostRoomEntity(), getPayAnswer());
    }
}
