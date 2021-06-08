/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author dgmin
 */
public class JSPUtil {
    
    public static void addErrorMessageForComponent(
        String component,
        String message ) {
        
        FacesMessage fmsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null);
        FacesContext.getCurrentInstance().addMessage(component, fmsg);
    }
    
    public static void addSuccessMessage(String message) {
        FacesMessage fmsg = new FacesMessage(FacesMessage.SEVERITY_INFO, message, message);
        FacesContext.getCurrentInstance().addMessage("successInfo", fmsg);
    }
}
