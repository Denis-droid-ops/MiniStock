package com.kuznetsov.miniStock.model;

import java.time.LocalDate;
import java.util.Objects;

public class Moving {
    private Integer id;
    private LocalDate date;
    private Integer movableCount;
    private Integer remainAfter;
    private Element element;

    public Moving() {
    }

    public Moving(Integer id, LocalDate date, Integer movableCount, Integer remainAfter, Element element) {
        this.id = id;
        this.date = date;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
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
        return Objects.equals(id, moving.id) && Objects.equals(date, moving.date) && Objects.equals(movableCount, moving.movableCount) && Objects.equals(remainAfter, moving.remainAfter) && Objects.equals(element, moving.element);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, movableCount, remainAfter, element);
    }

    @Override
    public String toString() {
        return "Moving{" +
                "id=" + id +
                ", date=" + date +
                ", movableCount=" + movableCount +
                ", remainAfter=" + remainAfter +
                ", element=" + element +
                '}';
    }
}
