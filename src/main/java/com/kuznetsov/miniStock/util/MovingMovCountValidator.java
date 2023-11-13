package com.kuznetsov.miniStock.util;

import com.kuznetsov.miniStock.controller.MovingController;
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
@FacesValidator(value = "movingMovCountValidator",managed = true)
public class MovingMovCountValidator implements Validator {

    @Inject
    private MovingController movingController;

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        Integer movableCount = (Integer)value;
        if(movingController.getElement().getCount()+movableCount<0){
            FacesMessage msg =
                    new FacesMessage("Cannot add moving, " +
                            "because movable count should be less or equal than element count");
            throw new ValidatorException(msg);
        }
    }
}
