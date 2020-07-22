package com.timbox.web.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;


import com.timbox.web.model.ErrorObject;
import com.timbox.web.model.RegistroDTO;

@Service
public class RegistroServiceImpl implements RegistroService{
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	

	@Override
	public ErrorObject registroUsuario(RegistroDTO datos) {
		String query = "INSERT INTO usuario (nombre, correo, rfc, contrasena) values (?,?,?,?)";		
		String querySelect = "SELECT count(rfc) FROM usuario WHERE rfc = '"+datos.getRfc()+"'";
		ErrorObject error = new ErrorObject();
		String valor = jdbcTemplate.queryForObject(querySelect, String.class);
				if(valor.equals("1")) {
					System.out.println("RFC ya existe: "+valor);
					error.setCode(1);
					error.setMessage("RFC YA EXISTE");
					return error;
				}else {
					jdbcTemplate.update(query, 
							new Object[] { 	datos.getNombre(),
											datos.getCorreo(),
											datos.getRfc(),
											datos.getContrasena()
					});
					System.out.println("Se registro el Usuario");
					return error;
				}
				
				
				
		
			
		
	}



	@Override
	public ErrorObject login(RegistroDTO datos) {
		//System.out.println("rfc: "+datos.getRfc() + " contraseña: "+datos.getContrasena());
		String querySelect = "select nombre from usuario where rfc = '"+datos.getRfc()+"' and contrasena = '"+datos.getContrasena()+"'";
		//System.out.println("query: "+querySelect);
		ErrorObject error = new ErrorObject();
		try {
			jdbcTemplate.queryForObject(querySelect, String.class);
			return error;
		} catch (Exception e) {
			error.setCode(1);
			error.setMessage("Error: Usuario y/o contraseña Incorrecta");
			return error;
		}	
		
	}



	@Override
	public List<RegistroDTO> obtenerConf(RegistroDTO datos) {
		String query = "SELECT nombre, rfc, direccion, telefono, website, contrasena, correo FROM usuario WHERE rfc= '"+datos.getRfc()+"'";
		List <RegistroDTO> datosOs = new ArrayList<RegistroDTO>();
		datosOs = jdbcTemplate.query(query, new RowMapper<RegistroDTO>() {
			@Override
			public  RegistroDTO mapRow(ResultSet set, int rows) throws SQLException {
				RegistroDTO lista = new RegistroDTO();
				lista.setNombre(set.getString(1));		
				lista.setRfc(set.getString(2));
				lista.setDireccion(set.getString(3));
				lista.setTelefono(set.getString(4));
				lista.setWeb(set.getString(5));
				lista.setContrasena(set.getString(6));
				lista.setCorreo(set.getString(7));
				return lista;
			} 
		});
		return datosOs;
	}



	@Override
	public void modif(RegistroDTO datos) {
		String query = "UPDATE usuarios SET nombre = ?, direccion = ?, telefono = ? , website = ? , contrasena = ? WHERE idOs = '"+datos.getRfc()+"' AND ID <> 0";
		jdbcTemplate.update(query,
				new Object[] { datos.getNombre(),
								datos.getDireccion(),
								datos.getTelefono(),
								datos.getWeb(),
								datos.getContrasena(),
				});
		System.out.println("query: "+query);
	}


	



}
