package com.timbox.web.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.timbox.web.model.ErrorObject;
import com.timbox.web.model.RegistroDTO;
import com.timbox.web.service.RegistroService;





@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class RegistroController {
	
	@Autowired
	RegistroService registroService;

	 @RequestMapping(value = "/registro/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<?> regUsuario(@RequestBody RegistroDTO datos){
	    	//HashMap<String, Object> hmap = new HashMap<String, Object>();
		 ErrorObject error = new ErrorObject();
	    	try {	    		
	    		error = registroService.registroUsuario(datos);
	    		System.out.println("error code: "+error.getCode());
	    		if(error.getCode() == 1) {
	    			return new ResponseEntity<ErrorObject>(error, HttpStatus.NOT_ACCEPTABLE);
	    		}
			} catch (Exception e) {
				System.out.println("Error Al Insertar: "+e.getMessage());
			}
	    	return new ResponseEntity<ErrorObject>(error, HttpStatus.OK);
	    }
	 
	 @RequestMapping(value = "/login/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<?> login(@RequestBody RegistroDTO datos){
		 ErrorObject error = new ErrorObject();
	    	try {	    		
	    		error = registroService.login(datos);
	    		System.out.println("error code: "+error.getCode());
	    		if(error.getCode() == 1) {
	    			return new ResponseEntity<ErrorObject>(error, HttpStatus.NOT_ACCEPTABLE);
	    		}
			} catch (Exception e) {
				System.out.println("Error Al Insertar: "+e.getMessage());
			}
	    	return new ResponseEntity<ErrorObject>(error, HttpStatus.OK);
	    }
	
	 @RequestMapping(value = "/configuracion/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<?> configuracion(@RequestBody RegistroDTO datos){
		 HashMap<String, Object> hmap = new HashMap<String, Object>();
		 try {
			 List<RegistroDTO> datosOs = registroService.obtenerConf(datos);
			 hmap.put("VersionOs", datosOs);
		} catch (Exception e) {
			System.out.println("Error al obtener datos: "+e.getMessage());
		}
		 return new ResponseEntity<HashMap<String, Object>>(hmap, HttpStatus.OK);
	    }
	 
	 @RequestMapping(value = "/modif/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<?> modif(@RequestBody RegistroDTO datos){
			 ErrorObject error = new ErrorObject();
		    	try {
		    		registroService.modif(datos);
				} catch (Exception e) {
					error.setCode(1);
					error.setMessage("Error al modificar Os: "+e.getMessage());
				}
		    	return new ResponseEntity<ErrorObject>(error, HttpStatus.OK);
		    }
	
}
