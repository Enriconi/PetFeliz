package br.com.integrador.petshop.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.integrador.petshop.model.Petshop;
import br.com.integrador.petshop.repository.PetshopDAO;

@Controller    
@RequestMapping(path="/petshop") 
public class PetshopController {

	
	private PetshopDAO petDAO;
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	
	public ResponseEntity<Petshop> cadastrar(@RequestBody Petshop petshop) {
		petDAO = new PetshopDAO();
		petshop = petDAO.cadastrar(petshop);
		return new ResponseEntity<Petshop>(petshop, HttpStatus.CREATED);

	}
	
	@RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<Petshop>> listaTodosPetshops() {
		
		petDAO = new PetshopDAO();
		List<Petshop> listaPetshops = petDAO.buscarTodos();		
		return new ResponseEntity<List<Petshop>>(listaPetshops, HttpStatus.OK);
	}

	@RequestMapping(value = "", method = RequestMethod.PUT)
	public ResponseEntity<Void> editar(@RequestBody Petshop petshop){
		petDAO = new PetshopDAO();
		petDAO.editar(petshop);
		return new ResponseEntity<Void>(HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> excluir(@PathVariable long id){
		petDAO = new PetshopDAO();
		petDAO.excluir(id);
		return new ResponseEntity<Void>(HttpStatus.OK);

	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<Petshop> buscarPorId(@PathVariable long id) {
		petDAO = new PetshopDAO();
		Petshop petshop = petDAO.buscarPorId(id);
		if(petshop!=null) {
			return new ResponseEntity<Petshop>(petshop, HttpStatus.OK);
		}		
		return new ResponseEntity<Petshop>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "{login}/{senha}", method = RequestMethod.GET)
	public ResponseEntity<Petshop> buscarPorLoginESenha(@PathVariable String login, @PathVariable String senha) {
		petDAO = new PetshopDAO();
		Petshop petshop = petDAO.buscarPorLoginESenha(login, senha);
		if(petshop!=null) {
			return new ResponseEntity<Petshop>(petshop, HttpStatus.OK);
		}		
		return new ResponseEntity<Petshop>(HttpStatus.NOT_FOUND);
	}
}


