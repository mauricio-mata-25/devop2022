package mx.tecnm.piedad.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import mx.tecnm.piedad.models.Cuenta;

@Repository
public class CuentaJDBC {

	@Autowired
	 private JdbcTemplate conexion;
	
	public void insertar(Cuenta nueva_cuenta, int planid) {
        String sql = "INSERT INTO cuentas (id, email, password, activa, fecha_ultimo_pago, nombre, apellido, numero_tarjeta,"
        + " fecha_vencimiento, codigo_seguridad, tipo_tarjeta, planes_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        conexion.update(sql, nueva_cuenta.getId(), nueva_cuenta.getEmail(), nueva_cuenta.getPassword(), nueva_cuenta.getActiva(),
        		nueva_cuenta.getFecha_ultimo_pago(), nueva_cuenta.getNombre(), nueva_cuenta.getApellido(), nueva_cuenta.getNumero_tarjeta(),
        		nueva_cuenta.getFecha_vencimiento(), nueva_cuenta.getCodigo_seguridad(), nueva_cuenta.getTipo_tarjeta(),
        		planid);
	}
	
	public void modificar(int id, Cuenta cuenta) {
		String sql = "UPDATE cuentas SET email = ?, password = ?, fecha_ultimo_pago = ?, nombre = ?, apellido = ?, numero_tarjeta = ?,"
				+" fecha_vencimiento = ?, codigo_seguridad = ?, tipo_tarjeta = ? WHERE id = ?";
		conexion.update(sql, cuenta.getEmail(), cuenta.getPassword(),
        		cuenta.getFecha_ultimo_pago(), cuenta.getNombre(), cuenta.getApellido(), cuenta.getNumero_tarjeta(),
        		cuenta.getFecha_vencimiento(), cuenta.getCodigo_seguridad(), cuenta.getTipo_tarjeta(), id);
	}
	
	public void desactivar(int cuentaid) {
        String sql = "UPDATE cuentas SET activa = 0 WHERE id = ?";
        conexion.update(sql, cuentaid);
    }
	
	public void modificarP(int planid, int cuentaid) {
		String sql = "UPDATE cuentas SET planes_id = ? WHERE id = ?";
		conexion.update(sql, planid, cuentaid);
		
	}
	
	public Cuenta login (Cuenta login_cuenta) {
		String sql = "SELECT * FROM cuentas WHERE email = ? AND password = ? ";
		 return conexion.queryForObject(sql, new CuentaRM(), login_cuenta.getEmail(), login_cuenta.getPassword());
		
	}
	
}
