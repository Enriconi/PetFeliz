package br.com.integrador.petshop.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.integrador.petshop.model.ClientePedido;
import br.com.integrador.petshop.persistencia.ClientePedidoDAO;

@Controller    
@RequestMapping(path="/clientePedido/") 
public class ClientePedidoController {

	
	private ClientePedidoDAO cpDAO;
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	
	public ResponseEntity<ClientePedido> cadastrar(@RequestBody ClientePedido clientePedido) {
		cpDAO = new ClientePedidoDAO();
		clientePedido = cpDAO.cadastrar(clientePedido);
		return new ResponseEntity<ClientePedido>(clientePedido, HttpStatus.CREATED);

	}
	
	@RequestMapping(value = "listar/", method = RequestMethod.GET)
    public ResponseEntity<List<ClientePedido>> listaTodosClientePedidos() {
		
		cpDAO = new ClientePedidoDAO();
		List<ClientePedido> listaClientePedidos = cpDAO.buscarTodos();		
		return new ResponseEntity<List<ClientePedido>>(listaClientePedidos, HttpStatus.OK);
	}

	@RequestMapping(value = "", method = RequestMethod.PUT)
	public ResponseEntity<Void> editar(@RequestBody ClientePedido clientePedido){
		cpDAO = new ClientePedidoDAO();
		cpDAO.editar(clientePedido);
		return new ResponseEntity<Void>(HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> excluir(@PathVariable long id){
		cpDAO = new ClientePedidoDAO();
		cpDAO.excluir(id);
		return new ResponseEntity<Void>(HttpStatus.OK);

	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<ClientePedido> buscarPorId(@PathVariable long id) {
		cpDAO = new ClientePedidoDAO();
		ClientePedido clientePedido = cpDAO.buscarPorId(id);
		if(clientePedido!=null) {
			return new ResponseEntity<ClientePedido>(clientePedido, HttpStatus.OK);
		}		
		return new ResponseEntity<ClientePedido>(HttpStatus.NOT_FOUND);
	}

//NAO UTIL PARA ESSE METODO
//	@RequestMapping(value = "{login}/{senha}", method = RequestMethod.GET)
//	public ResponseEntity<ClientePedido> buscarPorLoginESenha(@PathVariable String login, @PathVariable String senha) {
//		cpDAO = new ClientePedidoDAO();
//		ClientePedido clientePedido = cpDAO.buscarPorLoginESenha(login, senha);
//		if(clientePedido!=null) {
//			return new ResponseEntity<ClientePedido>(clientePedido, HttpStatus.OK);
//		}		
//		return new ResponseEntity<ClientePedido>(HttpStatus.NOT_FOUND);
//	}
}


