package mx.tecnm.piedad.controlers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import mx.tecnm.piedad.dao.PerfilJDBC;
import mx.tecnm.piedad.models.Perfil;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.PATCH})

public class PerfilWs {

	@Autowired PerfilJDBC repo;
	
	
	@PostMapping ("/cuentas/{cuenta-id}/perfiles")
	public ResponseEntity<?>insertar(@RequestBody Perfil nuevo_perfil, @PathVariable ("cuenta-id") int cuentaid){
		try {
			repo.insertar(nuevo_perfil, cuentaid);
	    	return new ResponseEntity<>(HttpStatus.CREATED);
	    } catch (DataAccessException e) {
	    	System.out.println(e.getMessage());
	    	return new ResponseEntity<>(HttpStatus.CONFLICT);
	    	
	    }
// comentario
	}
	
	@GetMapping("/cuentas/{cuenta-id}/perfiles")
	public ResponseEntity<?> consultar(@PathVariable ("cuenta-id") int cuentaid){
		List<Perfil> resultado = repo.consultar(cuentaid);
    	return new ResponseEntity<>(resultado, HttpStatus.OK);
	}
	
	@PutMapping("/perfiles/{perfil-id}")
	public ResponseEntity<?> modificar (@PathVariable ("perfil-id") int perfilid, @RequestBody Perfil perfil){
		repo.modificar(perfil, perfilid);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@DeleteMapping("/perfiles/{perfil-id}")
	public ResponseEntity<?> eliminar (@PathVariable ("perfil-id") int perfilid){
		repo.eliminar(perfilid);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
