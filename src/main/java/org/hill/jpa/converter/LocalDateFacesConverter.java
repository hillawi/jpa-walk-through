package org.hill.jpa.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.DateTimeConverter;
import javax.faces.convert.FacesConverter;

/**
 * Created by ahmed.abdeen on 04-04-17.
 */
@FacesConverter("localDateConverter")
public class LocalDateFacesConverter extends DateTimeConverter {
    public LocalDateFacesConverter() {
        setPattern("dd/MM/yyyy");
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return super.getAsObject(context, component, value);
    }
}
