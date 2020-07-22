package com.timbox.web.service;

import java.util.List;

import com.timbox.web.model.ErrorObject;
import com.timbox.web.model.RegistroDTO;



public interface RegistroService {

	public  ErrorObject registroUsuario(RegistroDTO datos);

	public ErrorObject login(RegistroDTO datos);

	public List<RegistroDTO> obtenerConf(RegistroDTO datos);

	public void modif(RegistroDTO datos);
	
	
	
	
}
