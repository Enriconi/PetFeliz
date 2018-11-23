package br.com.integrador.petshop.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.integrador.petshop.model.Cliente;
import br.com.integrador.petshop.persistencia.ClienteDAO;

@Controller
@RequestMapping(path = "/cliente/")
public class ClienteController {

	private ClienteDAO cDAO;

	@RequestMapping(value = "", method = RequestMethod.POST)

	public ResponseEntity<Cliente> cadastrar(@RequestBody Cliente cliente) {
		cDAO = new ClienteDAO();
		cliente = (Cliente) cDAO.cadastrar(cliente);
		return new ResponseEntity<Cliente>(cliente, HttpStatus.CREATED);

	}

	@RequestMapping(value = "listar/", method = RequestMethod.GET)
	public ResponseEntity<List<Object>> listaTodosClientes() {

		cDAO = new ClienteDAO();
		List<Object> listaClientes = cDAO.buscarTodos();
		return new ResponseEntity<List<Object>>(listaClientes, HttpStatus.OK);
	}

	@RequestMapping(value = "", method = RequestMethod.PUT)
	public ResponseEntity<Void> editar(@RequestBody Cliente cliente) {
		cDAO = new ClienteDAO();
		cDAO.editar(cliente);
		return new ResponseEntity<Void>(HttpStatus.OK);

	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> excluir(@PathVariable long id) {
		cDAO = new ClienteDAO();
		cDAO.excluir(id);
		return new ResponseEntity<Void>(HttpStatus.OK);

	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<Cliente> buscarPorId(@PathVariable long id) {
		cDAO = new ClienteDAO();
		Cliente cliente = (Cliente) cDAO.buscarPorId(id);
		if (cliente != null) {
			return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
		}
		return new ResponseEntity<Cliente>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "{login}/{senha}", method = RequestMethod.GET)
	public ResponseEntity<Cliente> buscarPorLoginESenha(@PathVariable String login, @PathVariable String senha) {
		cDAO = new ClienteDAO();
		Cliente cliente = cDAO.buscarPorLoginESenha(login, senha);
		if (cliente != null) {
			return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
		}
		return new ResponseEntity<Cliente>(HttpStatus.NOT_FOUND);
	}
}
