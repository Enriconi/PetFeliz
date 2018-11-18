package br.com.integrador.petshop;

import java.util.List;

import br.com.integrador.petshop.model.Produto;
//import br.com.integrador.petshop.model.Petshop;
import br.com.integrador.petshop.repository.ProdutoDAO;



public class TesteProduto {
	public static void main(String[] args) {
		
//		Petshop petshop = new Petshop();
//		petshop.setIdPetshop(2);
//		
//		Produto produto = new Produto();		
//		produto.setNomeProduto("Pá para juntar cocô de gato.");
//		produto.setDescricaoProduto("Junta vários cocôs.");
//		produto.setPrecoProduto(12.50);
//		produto.setEstoqueProduto(15);
//		
//		ProdutoDAO pDAO = new ProdutoDAO();
//		
//		produto.setPetshop(petshop);
//		pDAO.cadastrar(produto);
		
//		Petshop petshop = new Petshop();
//		petshop.setIdPetshop(2);
//		
//		Produto produto = new Produto();		
//		produto.setNomeProduto("Pá para juntar cocô de cachorro.");
//		produto.setDescricaoProduto("Junta vários cocôs.");
//		produto.setPrecoProduto(12.50);
//		produto.setEstoqueProduto(15);
//		produto.setIdProduto(1);
//		
//		ProdutoDAO pDAO = new ProdutoDAO();
//		
//		produto.setPetshop(petshop);
//		pDAO.editar(produto);
		
//		ProdutoDAO pDAO = new ProdutoDAO();
//		pDAO.excluir(7);
		
//		ProdutoDAO pDAO = new ProdutoDAO();
//		Produto produto = pDAO.buscarPorId(1);
//		System.out.println("Produto buscada por ID: " + produto.getNomeProduto());
		
	
		ProdutoDAO pDAO = new ProdutoDAO();
		List<Produto> listaProduto = pDAO.buscarTodos();
		for(int i = 0; i < listaProduto.size(); i++) {
			System.out.println("Lista de Produtos: " + listaProduto.get(i).getNomeProduto());
		}
	}
}
