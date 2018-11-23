package br.com.integrador.petshop.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.integrador.petshop.model.Produto;
import br.com.integrador.petshop.persistencia.ProdutoDAO;

@Controller
@RequestMapping(path = "/produto/")
public class ProdutoController {

	private ProdutoDAO prodDAO;

	@RequestMapping(value = "", method = RequestMethod.POST)

	public ResponseEntity<Produto> cadastrar(@RequestBody Produto produto) {
		prodDAO = new ProdutoDAO();
		produto = (Produto) prodDAO.cadastrar(produto);
		return new ResponseEntity<Produto>(produto, HttpStatus.CREATED);

	}

	@RequestMapping(value = "listar/", method = RequestMethod.GET)
	public ResponseEntity<List<Object>> listaTodosProdutos() {

		prodDAO = new ProdutoDAO();
		List<Object> listaProdutos = prodDAO.buscarTodos();
		return new ResponseEntity<List<Object>>(listaProdutos, HttpStatus.OK);
	}

	@RequestMapping(value = "", method = RequestMethod.PUT)
	public ResponseEntity<Void> editar(@RequestBody Produto produto) {
		prodDAO = new ProdutoDAO();
		prodDAO.editar(produto);
		return new ResponseEntity<Void>(HttpStatus.OK);

	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> excluir(@PathVariable long id) {
		prodDAO = new ProdutoDAO();
		prodDAO.excluir(id);
		return new ResponseEntity<Void>(HttpStatus.OK);

	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<Produto> buscarPorId(@PathVariable long id) {
		prodDAO = new ProdutoDAO();
		Produto produto = (Produto) prodDAO.buscarPorId(id);
		if (produto != null) {
			return new ResponseEntity<Produto>(produto, HttpStatus.OK);
		}
		return new ResponseEntity<Produto>(HttpStatus.NOT_FOUND);
	}
}