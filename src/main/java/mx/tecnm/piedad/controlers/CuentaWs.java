package mx.tecnm.piedad.controlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import mx.tecnm.piedad.dao.CuentaJDBC;
import mx.tecnm.piedad.models.Cuenta;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.PATCH})

public class CuentaWs {
	
	@Autowired
	CuentaJDBC repo;
	
	
	@PostMapping ("/planes/{plan-id}/cuentas")
	public ResponseEntity<?>insertar(@RequestBody Cuenta nueva_cuenta, @PathVariable ("plan-id") int planid){
		try {
			repo.insertar(nueva_cuenta, planid);
	    	return new ResponseEntity<>(HttpStatus.CREATED);
	    } catch (DataAccessException e) {
	    	System.out.println(e.getMessage());
	    	return new ResponseEntity<>(HttpStatus.CONFLICT);
	    	
	    	}
		}
	
	@PutMapping("/cuentas/{cuenta-id}")
	public ResponseEntity<?> modificar (@PathVariable("cuenta-id") int id, @RequestBody Cuenta cuenta){
		repo.modificar(id, cuenta);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@DeleteMapping("/cuentas/{cuenta-id}")
	public ResponseEntity<?> desactivar (@PathVariable("cuenta-id") int cuentaid){
			repo.desactivar(cuentaid);
			return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PutMapping("/planes/{plan-id}/cuentas/{cuenta-id}")
	public ResponseEntity<?> modificarP(@PathVariable ("plan-id") int planid, @PathVariable  ("cuenta-id") int cuentaid){
		repo.modificarP(planid, cuentaid);
		return new ResponseEntity<>(HttpStatus.CREATED);

		}
	
	@PostMapping("/sesion")
	public ResponseEntity<?> login (@RequestBody Cuenta cuenta){
	try 
	{
		Cuenta resultado = repo.login(cuenta);
		
		return new ResponseEntity<>(resultado, HttpStatus.OK);

	}
	catch (DataAccessException e) {
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
		}
	}
		
	
