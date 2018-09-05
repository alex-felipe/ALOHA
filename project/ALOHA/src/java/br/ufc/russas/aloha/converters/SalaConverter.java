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

@FacesConverter("salaConverter")
public class SalaConverter implements Converter {

    // Init ---------------------------------------------------------------------------------------
    private static SalaDAO salaDAO = new SalaDAO();

    // Actions ------------------------------------------------------------------------------------
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && value.trim().length() > 0) {
            try {
                 Sala sl = salaDAO.find(value);
                 if(sl!=null) return sl;             // Convert the unique String representation of Foo to the actual Foo object.
            } catch (SQLException ex) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid sala."));
            }
        } 
            return null;
        
    }
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        // Convert the Foo object to its unique String representation.
         if(value != null && value instanceof Sala) {
             return String.valueOf(((Sala) value).getNome());
         }
        return "Erro";
    }

}
