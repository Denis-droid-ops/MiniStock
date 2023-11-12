package com.kuznetsov.miniStock.controller;

import com.kuznetsov.miniStock.dao.ElementDao;
import com.kuznetsov.miniStock.dao.MovingDao;
import com.kuznetsov.miniStock.model.Element;
import com.kuznetsov.miniStock.model.Moving;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Named
@ViewScoped
public class MovingController implements Serializable {

    private Moving moving;
    private Moving newMoving = new Moving();
    private Moving selectedMoving;
    private List<Moving> movings;

    private String id;
    private Element element;

    @Inject
    private MovingDao movingDao;
    @Inject
    private ElementDao elementDao;

    @PostConstruct
    public void init(){
        movings = movingDao.findAll();
        if(id!=null){
            movings = movingDao.findAllByElementId(Integer.valueOf(id));
        }
    }

    public void setIntoServer() {
        //default values, which set on server side, movable count set on client side
        newMoving.setDateTime(LocalDateTime.now());
        newMoving.setRemainAfter(0);
        if (id != null) {
            element = elementDao.findById(Integer.valueOf(id)).get();
            newMoving.setElement(element);
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Element getElement() {
        return element;
    }

    public void setElement(Element element) {
        this.element = element;
    }

    public MovingDao getMovingDao() {
        return movingDao;
    }

    public void setMovingDao(MovingDao movingDao) {
        this.movingDao = movingDao;
    }

    public Moving getMoving() {
        return moving;
    }

    public void setMoving(Moving moving) {
        this.moving = moving;
    }

    public Moving getNewMoving() {
        return newMoving;
    }

    public void setNewMoving(Moving newMoving) {
        this.newMoving = newMoving;
    }

    public List<Moving> getMovings() {
        return movings;
    }

    public void setMovings(List<Moving> movings) {
        this.movings = movings;
    }

    public Moving getSelectedMoving() {
        return selectedMoving;
    }

    public void setSelectedMoving(Moving selectedMoving) {
        this.selectedMoving = selectedMoving;
    }

    public ElementDao getElementDao() {
        return elementDao;
    }

    public void setElementDao(ElementDao elementDao) {
        this.elementDao = elementDao;
    }

    public String create(){
        newMoving.setRemainAfter(element.getCount()+newMoving.getMovableCount());
        elementDao.updateCount(Integer.valueOf(id),newMoving.getRemainAfter());
        movingDao.save(newMoving);
        return goMainPage();
    }

    public String goMainPage(){
        return "elements?faces-redirect=true";
    }


}
