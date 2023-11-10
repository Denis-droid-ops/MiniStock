package com.kuznetsov.miniStock.controller;

import com.kuznetsov.miniStock.dao.ElementDao;
import com.kuznetsov.miniStock.model.Element;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class ElementController implements Serializable {

    private static final Integer INIT_COUNT = 0;

    private Element element;
    private Element newElement = new Element();
    private List<Element> elements;
    private Element selectedElement;


    @Inject
    private ElementDao elementDao;

    @PostConstruct
    public void init() {
        newElement.setCount(INIT_COUNT);
        elements = this.elementDao.findAll();
    }

    public Element getElement() {
        return element;
    }

    public void setElement(Element element) {
        this.element = element;
    }

    public Element getNewElement() {
        return newElement;
    }

    public void setNewElement(Element newElement) {
        this.newElement = newElement;
    }

    public ElementDao getElementDao() {
        return elementDao;
    }

    public void setElementDao(ElementDao elementDao) {
        this.elementDao = elementDao;
    }

    public List<Element> getElements() {
        return elements;
    }

    public Element getSelectedElement() {
        return selectedElement;
    }

    public void setSelectedElement(Element selectedElement) {
        this.selectedElement = selectedElement;
    }

    public String create(){
        elementDao.save(newElement);
        return goMainPage();
    }

    public String delete(){
        elementDao.delete(selectedElement.getId());
        return goMainPage();
    }

    public String newElementForm(){
        return "newElement?faces-redirect=true";
    }

    public String goMainPage(){
        return "elements?faces-redirect=true";
    }

    public String newMovingForm(Element element){
        return "newMoving?faces-redirect=true&id="+element.getId();
    }



}
