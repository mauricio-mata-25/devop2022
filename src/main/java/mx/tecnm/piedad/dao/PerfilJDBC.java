package mx.tecnm.piedad.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import mx.tecnm.piedad.models.Perfil;

@Repository
public class PerfilJDBC {

	@Autowired
	 private JdbcTemplate conexion;
	
	public void insertar(Perfil nuevo_perfil, int cuentaid) {
        String sql = "INSERT INTO perfiles_usuarios (id, nombre, idioma, clasificacion_edad, cuentas_id)"
        		+ " VALUES (?, ?, ?, ?, ?)";
        conexion.update(sql, nuevo_perfil.getId(), nuevo_perfil.getNombre(), nuevo_perfil.getIdioma(), nuevo_perfil.getClasificacion_edad()
        		, cuentaid);
	}
	
	public List<Perfil> consultar(int cuentaid) {
		String sql = "SELECT * FROM perfiles_usuarios WHERE cuentas_id = ?";
		return conexion.query(sql, new PerfilRM(), cuentaid);
	}
	
	public void modificar (Perfil perfil, int perfilid) {
		String sql = "UPDATE perfiles_usuarios SET nombre = ?, idioma = ?, clasificacion_edad = ? WHERE id = ?"; 
		conexion.update(sql, perfil.getNombre(), perfil.getIdioma(), perfil.getClasificacion_edad(), perfilid);
	}
	
	public void eliminar (int perfilid) {
		String sql = "DELETE FROM perfiles_usuarios WHERE id = ?";
		conexion.update(sql, perfilid);
	}
}
