package br.com.integrador.petshop;

import java.util.List;

import br.com.integrador.petshop.model.Petshop;
import br.com.integrador.petshop.repository.PetshopDAO;

public class TestePetshop {
	public static void main(String[] args) {
	
//		Petshop petshop = new Petshop(1, "Pet Feliz", "43.631.467/0001-42", "petfeliz@gmail.com", "thorzinho123", "051 9 8494-7993");
//		
//		PetshopDAO pDAO = new PetshopDAO();
//		pDAO.cadastrar(petshop);
		
//		Petshop petshop = new Petshop(1, "Pet Feliz", "43.631.467/0001-42", "petfeliz@gmail.com", "belinha123", "051 9 8494-7993");
//		
//		PetshopDAO pDAO = new PetshopDAO();
//		pDAO.editar(petshop);
		
//		PetshopDAO pDAO = new PetshopDAO();
//		Petshop petshop = pDAO.buscarPorId(2);
//		System.out.println("Petshop buscada por ID: " + petshop.getNomePetshop());
		
	
		PetshopDAO pDAO = new PetshopDAO();
		List<Petshop> listaPetshop = pDAO.buscarTodos();
		for(int i = 0; i < listaPetshop.size(); i++) {
			System.out.println("Lista de PetShops: " + listaPetshop.get(i).getNomePetshop());
		}
	}
}
