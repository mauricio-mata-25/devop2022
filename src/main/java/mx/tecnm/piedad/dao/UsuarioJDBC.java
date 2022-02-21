package mx.tecnm.piedad.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioJDBC {

	@Autowired
	JdbcTemplate conexion;

	public boolean login(String email, String password) {
	String sql="SELECT COUNT(*) FROM cuentas WHERE email = ? AND password = ?";
	
	return conexion.queryForObject(sql, Integer.class, email, password)==1;
	}
}
