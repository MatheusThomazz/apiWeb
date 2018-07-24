package br.com.fabricadeprogramador.ws.controller;

import java.util.Date;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.fabricadeprogramador.ws.model.Usuario;
import br.com.fabricadeprogramador.ws.service.UsuarioService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
public class LoginController {

	@Autowired
	private UsuarioService usuarioService;
	
	@RequestMapping(method = RequestMethod.POST, value = "/autenticar", consumes = MediaType.APPLICATION_JSON_VALUE)
	public LoginResponse autenticar( @RequestBody Usuario usuario) throws ServletException {
		
		if(usuario.getNome() == null || usuario.getSenha() == null) {
			throw new ServletException("usuario e senha são obrigatorios");
		}
		
	//consulta no banco 	
	 Usuario usuarioAutenticado = usuarioService.buscarPorNome(usuario.getNome());
	 
	 if(usuarioAutenticado == null) {
		 throw new ServletException("usuario não encontrado");
	 }
	 
	 if(!usuarioAutenticado.getSenha().equals(usuario.getSenha())) {
		 throw new ServletException("usuario ou senha inválidos");
	 }
	 
	 //TOKEN {token:mjfhduiiejeekfjfjhjeiwji4859irkfk}
		String token = Jwts.builder().setSubject(usuarioAutenticado.getNome())
				.signWith(SignatureAlgorithm.HS512, "banana")
				.setExpiration(new Date(System.currentTimeMillis()+ 5*60*1000))
				.compact();
		
		return new LoginResponse(token);
	}
	
	private class LoginResponse{
		private String token;
		
		public LoginResponse(String token) {
			this.token = token;
		}
		
		public String getToken() {
			return this.token;
		}
		
	}
}
