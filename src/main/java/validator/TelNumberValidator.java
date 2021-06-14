/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validator;

import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author dgmin
 */

@FacesValidator("TelNumberValidator")
public class TelNumberValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        System.out.println("validateTelNumber");
        String telefonnr = value.toString();
        FacesMessage msg;
        String pattern;

        pattern =  "^\\++\\d{7,15}$";   // Die Nummer muss mit einem + starten und zwischen 7-15
                                        // Ziffern enthalten.
        if(!Pattern.matches(pattern, telefonnr)){
            msg = new FacesMessage("Bitte geben Sie Ihre Nummer im Format +49... ein"); 
            context.addMessage(component.getClientId(context), msg);
            throw new ValidatorException(msg);
        } 
    }
}
