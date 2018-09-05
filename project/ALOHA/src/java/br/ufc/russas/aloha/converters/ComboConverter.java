package br.ufc.russas.aloha.converters;

import br.ufc.russas.aloha.dao.*;

import br.ufc.russas.aloha.model.*;
import java.sql.SQLException;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter("comboConverter")
public class ComboConverter implements Converter {

    // Init ---------------------------------------------------------------------------------------
    private static ComboDAO comboDAO = new ComboDAO();

    // Actions ------------------------------------------------------------------------------------
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && value.trim().length() > 0) {
            try {
                List<Combo> combos = comboDAO.selectALL();
                for (Combo c : combos) {   
                    if(c.getDiasEstendido().equals(value)){ 
                        return c;
                    }
                } 
                return null;// Convert the unique String representation of Foo to the actual Foo object.
            } catch (Exception ex) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid combo."));
            }
        }
        return null;

    }

    public String getAsString(FacesContext context, UIComponent component, Object value) {
        // Convert the Foo object to its unique String representation.
        if (value != null && value instanceof Combo) {
            return String.valueOf(((Combo) value).getDiasEstendido());
        }
        return "Erro";
    }

}
