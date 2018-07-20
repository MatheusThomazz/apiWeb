package br.com.fabricadeprogramador.ws.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.fabricadeprogramador.ws.model.Usuario;
import br.com.fabricadeprogramador.ws.service.UsuarioService;

@RestController
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@RequestMapping(method = RequestMethod.POST, value = "/usuarios", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Usuario> CadastrarUsuario(@RequestBody Usuario usuario) {
		
		 Usuario usurioCadastrado =usuarioService.cadastrar(usuario);
		 
		 return new ResponseEntity<Usuario>(usurioCadastrado, HttpStatus.OK);
	}

}
