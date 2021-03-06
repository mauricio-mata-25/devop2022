package mx.tecnm.piedad.controlers;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import mx.tecnm.piedad.dao.UsuarioJDBC;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.PATCH})
public class Mensaje {
	
	@Autowired
	UsuarioJDBC repo;
	
	@PostMapping("/login")
    public ResponseEntity<?> autenticar(@RequestParam String email, @RequestParam String password){
    
        String token="";
        if (repo.login(email,password)==true){
            token = generarJWTToken(email);
            return new ResponseEntity<>(token, HttpStatus.OK);
        }
        else {

        return new ResponseEntity<>(token, HttpStatus.NO_CONTENT);
        }
}
    
        private String generarJWTToken(String usuario) {
            String secretKey = "abracadabra";
            List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");
            String token = Jwts
                    .builder()
                    .setId("itlpJWT")
                    .setSubject(usuario)
                    .claim("authorities", 
                            grantedAuthorities.stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList()))
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis()+600000))
            .signWith(SignatureAlgorithm.HS512,
                    secretKey.getBytes()).compact();

            return "Bearer " + token;

}
	
        
    @GetMapping("/hola")
	public String saludar () {
		return "??Hola WS!";
	}
    
    @GetMapping("/eco")
    public String eco(@RequestParam String mensaje) {
    	return mensaje + "" + mensaje + "" + mensaje; 
    }
    
    @GetMapping ("/saludo")
    public String saludarUsuario(@RequestParam String usuario, @RequestParam String mensaje) {
    	return usuario + " " + mensaje;
    }
    
    @GetMapping("/mensaje/{numero}")
    public String elegirMensaje(@PathVariable int numero) {
    	String mensajes[] = new String [] {"Hoy depositan","Arriba el Am??rica","Ya es viernes"};
    	
    	try{
    	return mensajes [numero];
        }catch(Exception e) {
        	return "Suerte para la proxima, sigue participando";
        }
    }
    
}
