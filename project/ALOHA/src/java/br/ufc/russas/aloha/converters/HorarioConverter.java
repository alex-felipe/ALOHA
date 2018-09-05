package br.ufc.russas.aloha.converters;

import br.ufc.russas.aloha.dao.*;
import br.ufc.russas.aloha.model.*;
import java.sql.SQLException;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter("horarioConverter")
public class HorarioConverter implements Converter {

    private static HorarioDAO horarioDAO = new HorarioDAO();

    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && value.trim().length() > 0) {
            try {
                 Horario hr = horarioDAO.find(value);
                 if(hr!=null) return hr;             // Convert the unique String representation of Foo to the actual Foo object.
            } catch (SQLException ex) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid horario."));
            }
        } 
            return null;
        
    }
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return (value != null && value instanceof Horario) ? String.valueOf(((Horario) value).getDescricao()): "Erro";   

    }

}
