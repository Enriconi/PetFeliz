package br.com.integrador.petshop.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.integrador.petshop.model.Animal;
import br.com.integrador.petshop.repository.AnimalDAO;

@Controller    
@RequestMapping(path="/animal/") 
public class AnimalController {

	
	private AnimalDAO aDAO;
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	
	public ResponseEntity<Animal> cadastrar(@RequestBody Animal animal) {
		aDAO = new AnimalDAO();
		animal = aDAO.cadastrar(animal);
		return new ResponseEntity<Animal>(animal, HttpStatus.CREATED);

	}
	
	@RequestMapping(value = "listar/", method = RequestMethod.GET)
    public ResponseEntity<List<Animal>> listaTodosAnimals() {
		
		aDAO = new AnimalDAO();
		List<Animal> listaAnimals = aDAO.buscarTodos();		
		return new ResponseEntity<List<Animal>>(listaAnimals, HttpStatus.OK);
	}

	@RequestMapping(value = "", method = RequestMethod.PUT)
	public ResponseEntity<Void> editar(@RequestBody Animal animal){
		aDAO = new AnimalDAO();
		aDAO.editar(animal);
		return new ResponseEntity<Void>(HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> excluir(@PathVariable long id){
		aDAO = new AnimalDAO();
		aDAO.excluir(id);
		return new ResponseEntity<Void>(HttpStatus.OK);

	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<Animal> buscarPorId(@PathVariable long id) {
		aDAO = new AnimalDAO();
		Animal animal = aDAO.buscarPorId(id);
		if(animal!=null) {
			return new ResponseEntity<Animal>(animal, HttpStatus.OK);
		}		
		return new ResponseEntity<Animal>(HttpStatus.NOT_FOUND);
	}

//NAO UTIL PARA ESSE METODO
//	@RequestMapping(value = "{login}/{senha}", method = RequestMethod.GET)
//	public ResponseEntity<Animal> buscarPorLoginESenha(@PathVariable String login, @PathVariable String senha) {
//		aDAO = new AnimalDAO();
//		Animal animal = aDAO.buscarPorLoginESenha(login, senha);
//		if(animal!=null) {
//			return new ResponseEntity<Animal>(animal, HttpStatus.OK);
//		}		
//		return new ResponseEntity<Animal>(HttpStatus.NOT_FOUND);
//	}
}


