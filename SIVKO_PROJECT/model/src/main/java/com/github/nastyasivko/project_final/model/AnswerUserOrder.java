package com.github.nastyasivko.project_final.model;

public class AnswerUserOrder {

    private Long idUserOrder;
    private String userLogin;
    private String nameRoom;
    private String beds;
    private String dateStart;
    private String dateEnd;
    private Answer answer;
    private int numberRoom;
    private Integer cost;
    private String payAnswer;

    public AnswerUserOrder(Long idUserOrder, String userLogin, String nameRoom, String beds, String dateStart, String dateEnd, Answer answer, int numberRoom, Integer cost, String payAnswer) {
        this.idUserOrder = idUserOrder;
        this.userLogin = userLogin;
        this.nameRoom = nameRoom;
        this.beds = beds;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.answer = answer;
        this.numberRoom = numberRoom;
        this.cost = cost;
        this.payAnswer = payAnswer;
    }

    public AnswerUserOrder(String userLogin, String nameRoom, String beds, String dateStart, String dateEnd, Answer answer) {
        this.userLogin = userLogin;
        this.nameRoom = nameRoom;
        this.beds = beds;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.answer = answer;
    }

    public Long getIdUserOrder() {
        return idUserOrder;
    }

    public void setIdUserOrder(Long idUserOrder) {
        this.idUserOrder = idUserOrder;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getNameRoom() {
        return nameRoom;
    }

    public void setNameRoom(String nameRoom) {
        this.nameRoom = nameRoom;
    }

    public String getBeds() {
        return beds;
    }

    public void setBeds(String beds) {
        this.beds = beds;
    }

    public int getNumberRoom() {
        return numberRoom;
    }

    public void setNumberRoom(int numberRoom) {
        this.numberRoom = numberRoom;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public String getPayAnswer() {
        return payAnswer;
    }

    public void setPayAnswer(String payAnswer) {
        this.payAnswer = payAnswer;
    }
}
