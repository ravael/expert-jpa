package com.hibernate.jpa2.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.hibernate.jpa2.dao.MotoristaDAO;
import com.hibernate.jpa2.modelo.Motorista;
import com.hibernate.jpa2.util.cdi.CDIServiceLocator;

@FacesConverter(forClass=Motorista.class)
public class MotoristaConverter implements Converter{


	private MotoristaDAO motoristaDAO;
	
	public MotoristaConverter() {
		this.motoristaDAO = CDIServiceLocator.getBean(MotoristaDAO.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		
		Motorista retorno = null;

		if (value != null) {
			retorno = this.motoristaDAO.buscarPeloCodigo(new Long(value));
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Long codigo = ((Motorista) value).getCodigo();
			return codigo == null ? null : codigo.toString();
		}
		return "";
	}

}
