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

@FacesConverter("disciplinaConverter")
public class DisciplinaConverter implements Converter {

    // Init ---------------------------------------------------------------------------------------
    private static DisciplinaDAO disciplinaDAO = new DisciplinaDAO();

    // Actions ------------------------------------------------------------------------------------
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && value.trim().length() > 0) {
            try {
                 Disciplina doc = disciplinaDAO.find(value);
                 if(doc!=null) return doc;             // Convert the unique String representation of Foo to the actual Foo object.
            } catch (SQLException ex) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid disciplina."));
            }
        } 
            return null;
    }
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        // Convert the Foo object to its unique String representation.
         if(value != null && value instanceof Disciplina) {
             return String.valueOf(((Disciplina) value).getNome());
         }
        
        return "Erro";
    }

}
