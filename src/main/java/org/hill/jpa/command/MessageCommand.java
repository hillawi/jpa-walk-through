package org.hill.jpa.command;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 * Created by Hillawi on 01-04-17.
 */
@ManagedBean(name = "msgCommand")
public class MessageCommand {
    public void execute() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Executed", "Using Message command."));
    }
}
