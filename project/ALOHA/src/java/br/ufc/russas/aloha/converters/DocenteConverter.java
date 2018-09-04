package br.ufc.russas.aloha.converters;

import br.ufc.russas.aloha.dao.DocenteDAO;
import br.ufc.russas.aloha.model.Docente;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter("docenteConverter")
public class DocenteConverter implements Converter {

    // Init ---------------------------------------------------------------------------------------
    private static DocenteDAO docenteDAO = new DocenteDAO();

    // Actions ------------------------------------------------------------------------------------
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && value.trim().length() > 0) {
            try {
                // Convert the unique String representation of Foo to the actual Foo object.
                return docenteDAO.find(value);
            } catch (SQLException ex) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid docente."));
            }
        } else {
            return null;
        }
    }

    public String getAsString(FacesContext context, UIComponent component, Object value) {
        // Convert the Foo object to its unique String representation.
         if(value != null && value instanceof Docente) {
             return String.valueOf(((Docente) value).getNome());
         }else{
             System.out.println("Noã  é docente");
         }
        
        return "Erro";
    }

}
