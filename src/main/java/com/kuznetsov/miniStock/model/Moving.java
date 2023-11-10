package com.kuznetsov.miniStock.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Moving {
    private Integer id;
    private LocalDateTime dateTime;
    private Integer movableCount;
    private Integer remainAfter;
    private Element element;

    public Moving() {
    }

    public Moving(Integer id, LocalDateTime dateTime, Integer movableCount, Integer remainAfter, Element element) {
        this.id = id;
        this.dateTime = dateTime;
        this.movableCount = movableCount;
        this.remainAfter = remainAfter;
        this.element = element;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Integer getMovableCount() {
        return movableCount;
    }

    public void setMovableCount(Integer movableCount) {
        this.movableCount = movableCount;
    }

    public Integer getRemainAfter() {
        return remainAfter;
    }

    public void setRemainAfter(Integer remainAfter) {
        this.remainAfter = remainAfter;
    }

    public Element getElement() {
        return element;
    }

    public void setElement(Element element) {
        this.element = element;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Moving)) return false;
        Moving moving = (Moving) o;
        return Objects.equals(id, moving.id) && Objects.equals(dateTime, moving.dateTime) && Objects.equals(movableCount, moving.movableCount) && Objects.equals(remainAfter, moving.remainAfter) && Objects.equals(element, moving.element);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateTime, movableCount, remainAfter, element);
    }

    @Override
    public String toString() {
        return "Moving{" +
                "id=" + id +
                ", date=" + dateTime +
                ", movableCount=" + movableCount +
                ", remainAfter=" + remainAfter +
                ", element=" + element +
                '}';
    }
}
