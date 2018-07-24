package br.com.fabricadeprogramador.ws.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import br.com.fabricadeprogramador.ws.model.Cliente;
import br.com.fabricadeprogramador.ws.service.ClienteService;


@RestController
@RequestMapping("/admin")
public class ClienteController {
	
	@Autowired
     ClienteService clienteservice;

	// End Points
	@RequestMapping(method = RequestMethod.POST, value = "/clientes", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Cliente> cadastrarCliente(@RequestBody Cliente cliente) {

		Cliente clienteCadastrado = clienteservice.cadastrar(cliente);

		return new ResponseEntity<Cliente>(clienteCadastrado, HttpStatus.CREATED);

	}

	@RequestMapping(method = RequestMethod.GET, value = "/clientes", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Cliente>> buscarTodosClientes() {

		Collection<Cliente> clientesBuscados = clienteservice.buscarTodos();

		return new ResponseEntity<Collection<Cliente>>(clientesBuscados, HttpStatus.OK);

	}
	@RequestMapping(method = RequestMethod.GET, value = "/clientes/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Cliente> buscarClientesPorId(@PathVariable Integer id) {

		Cliente clienteEncontrado = clienteservice.buscaPorId(id);

		return new ResponseEntity<>(clienteEncontrado, HttpStatus.OK);

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/clientes/{id}")
	public ResponseEntity<Cliente> excluirCliente(@PathVariable Integer id) {

		Cliente clienteEncontrado = clienteservice.buscaPorId(id);

		if (clienteEncontrado == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else
			clienteservice.excluir(clienteEncontrado);

		return new ResponseEntity<>(HttpStatus.OK);

	}

	@RequestMapping(method = RequestMethod.PUT, value = "/clientes", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Cliente> alterarCliente(@RequestBody Cliente cliente) {

		Cliente clienteAlterado = clienteservice.alterar(cliente);

		return new ResponseEntity<Cliente>(clienteAlterado, HttpStatus.OK);

	}

}
