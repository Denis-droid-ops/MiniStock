package com.kuznetsov.miniStock.util;

import com.kuznetsov.miniStock.dao.ElementDao;
import com.kuznetsov.miniStock.model.Element;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.FacesValidator;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@RequestScoped
@FacesValidator(value = "elementValidator",managed = true)
public class ElementValidator implements Validator {

    @Inject
    private ElementDao elementDao;

    public ElementDao getElementDao() {
        return elementDao;
    }

    public void setElementDao(ElementDao elementDao) {
        this.elementDao = elementDao;
    }

    public ElementValidator() {
    }

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String name = value.toString();
        if(elementDao.findByName(name).isPresent()){
            FacesMessage msg = new FacesMessage("Element with this name does exist");
            throw new ValidatorException(msg);
        }
    }
}
