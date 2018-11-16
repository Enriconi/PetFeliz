package br.com.integrador.petshop.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.integrador.petshop.model.Pedido;
import br.com.integrador.petshop.repository.PedidoDAO;

@Controller    
@RequestMapping(path="/pedido/") 
public class PedidoController {

	
	private PedidoDAO pedaDAO;
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	
	public ResponseEntity<Pedido> cadastrar(@RequestBody Pedido pedido) {
		pedaDAO = new PedidoDAO();
		pedido = pedaDAO.cadastrar(pedido);
		return new ResponseEntity<Pedido>(pedido, HttpStatus.CREATED);

	}
	
	@RequestMapping(value = "listar/", method = RequestMethod.GET)
    public ResponseEntity<List<Pedido>> listaTodosPedidos() {
		
		pedaDAO = new PedidoDAO();
		List<Pedido> listaPedidos = pedaDAO.buscarTodos();		
		return new ResponseEntity<List<Pedido>>(listaPedidos, HttpStatus.OK);
	}

	@RequestMapping(value = "", method = RequestMethod.PUT)
	public ResponseEntity<Void> editar(@RequestBody Pedido pedido){
		pedaDAO = new PedidoDAO();
		pedaDAO.editar(pedido);
		return new ResponseEntity<Void>(HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> excluir(@PathVariable long id){
		pedaDAO = new PedidoDAO();
		pedaDAO.excluir(id);
		return new ResponseEntity<Void>(HttpStatus.OK);

	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<Pedido> buscarPorId(@PathVariable long id) {
		pedaDAO = new PedidoDAO();
		Pedido pedido = pedaDAO.buscarPorId(id);
		if(pedido!=null) {
			return new ResponseEntity<Pedido>(pedido, HttpStatus.OK);
		}		
		return new ResponseEntity<Pedido>(HttpStatus.NOT_FOUND);
	}

//NAO UTIL PARA ESSE METODO
//	@RequestMapping(value = "{login}/{senha}", method = RequestMethod.GET)
//	public ResponseEntity<Pedido> buscarPorLoginESenha(@PathVariable String login, @PathVariable String senha) {
//		pedaDAO = new PedidoDAO();
//		Pedido pedido = pedaDAO.buscarPorLoginESenha(login, senha);
//		if(pedido!=null) {
//			return new ResponseEntity<Pedido>(pedido, HttpStatus.OK);
//		}		
//		return new ResponseEntity<Pedido>(HttpStatus.NOT_FOUND);
//	}
}


