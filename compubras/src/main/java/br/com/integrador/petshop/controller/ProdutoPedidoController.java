package br.com.integrador.petshop.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.integrador.petshop.model.ProdutoPedido;
import br.com.integrador.petshop.persistencia.ProdutoPedidoDAO;

@Controller    
@RequestMapping(path="/produtoPedido/") 
public class ProdutoPedidoController {

	
	private ProdutoPedidoDAO prodpedaDAO;
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	
	public ResponseEntity<ProdutoPedido> cadastrar(@RequestBody ProdutoPedido produtoPedido) {
		prodpedaDAO = new ProdutoPedidoDAO();
		produtoPedido = prodpedaDAO.cadastrar(produtoPedido);
		return new ResponseEntity<ProdutoPedido>(produtoPedido, HttpStatus.CREATED);

	}
	
	@RequestMapping(value = "listar/", method = RequestMethod.GET)
    public ResponseEntity<List<ProdutoPedido>> listaTodosProdutoPedidos() {
		
		prodpedaDAO = new ProdutoPedidoDAO();
		List<ProdutoPedido> listaProdutoPedidos = prodpedaDAO.buscarTodos();		
		return new ResponseEntity<List<ProdutoPedido>>(listaProdutoPedidos, HttpStatus.OK);
	}

	@RequestMapping(value = "", method = RequestMethod.PUT)
	public ResponseEntity<Void> editar(@RequestBody ProdutoPedido produtoPedido){
		prodpedaDAO = new ProdutoPedidoDAO();
		prodpedaDAO.editar(produtoPedido);
		return new ResponseEntity<Void>(HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> excluir(@PathVariable long id){
		prodpedaDAO = new ProdutoPedidoDAO();
		prodpedaDAO.excluir(id);
		return new ResponseEntity<Void>(HttpStatus.OK);

	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<ProdutoPedido> buscarPorId(@PathVariable long id) {
		prodpedaDAO = new ProdutoPedidoDAO();
		ProdutoPedido produtoPedido = prodpedaDAO.buscarPorId(id);
		if(produtoPedido!=null) {
			return new ResponseEntity<ProdutoPedido>(produtoPedido, HttpStatus.OK);
		}		
		return new ResponseEntity<ProdutoPedido>(HttpStatus.NOT_FOUND);
	}

//NAO UTIL PARA ESSE METODO
//	@RequestMapping(value = "{login}/{senha}", method = RequestMethod.GET)
//	public ResponseEntity<ProdutoPedido> buscarPorLoginESenha(@PathVariable String login, @PathVariable String senha) {
//		prodpedaDAO = new ProdutoPedidoDAO();
//		ProdutoPedido produtoPedido = prodpedaDAO.buscarPorLoginESenha(login, senha);
//		if(produtoPedido!=null) {
//			return new ResponseEntity<ProdutoPedido>(produtoPedido, HttpStatus.OK);
//		}		
//		return new ResponseEntity<ProdutoPedido>(HttpStatus.NOT_FOUND);
//	}
}


