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

@FacesValidator("NameValidator")
public class NameValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        System.out.println("validateName");
        String name = value.toString();
        FacesMessage msg;
        String pattern = "^[a-zA-Z0-9._-]{3,}$"; // Nur Buchstaben und -
                
        if(!Pattern.matches(pattern, name)){  
            msg = new FacesMessage("Bitte geben Sie einen gültigen Namen ein"); 
            context.addMessage(component.getClientId(context), msg);
            throw new ValidatorException(msg);
        }    
    }
}
