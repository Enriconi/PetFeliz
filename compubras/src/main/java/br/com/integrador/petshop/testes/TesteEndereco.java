package br.com.integrador.petshop.testes;

import java.util.List;

import br.com.integrador.petshop.model.Endereco;
import br.com.integrador.petshop.persistencia.EnderecoDAO;



public class TesteEndereco {
	public static void main(String[] args) {
		
//		Cliente cliente = new Cliente();
//		cliente.setIdCliente(2);
//		
//		Endereco endereco = new Endereco();		
//		endereco.setCep("93224-490");
//		endereco.setEstado("RS");
//		endereco.setCidade("Sapucaia do Sul");
//		endereco.setBairro("Pasqualini");
//		endereco.setRua("Leopoldo Johan");
//		endereco.setNumero(199);
//		
//		EnderecoDAO eDAO = new EnderecoDAO();
//		
//		endereco.setCliente(cliente);
//		eDAO.cadastrar(endereco);
		
//		Cliente cliente = new Cliente();
//		cliente.setIdCliente(2);
//		
//		Endereco endereco = new Endereco();		
//		endereco.setCep("93224-490");
//		endereco.setEstado("RS");
//		endereco.setCidade("Sapucaia do Sul");
//		endereco.setBairro("Pasqualini");
//		endereco.setRua("Leopoldo Johan");
//		endereco.setNumero(203);
//		endereco.setIdEndereco(1);
//		
//		EnderecoDAO eDAO = new EnderecoDAO();
//		
//		endereco.setCliente(cliente);
//		eDAO.editar(endereco);
		
//		EnderecoDAO eDAO = new EnderecoDAO();
//		eDAO.excluir(7);
		
//		EnderecoDAO eDAO = new EnderecoDAO();
//		Endereco endereco = eDAO.buscarPorId(1);
//		System.out.println("Endereço buscado por ID: " + endereco.getRua());
		
	
		EnderecoDAO eDAO = new EnderecoDAO();
		List<Endereco> listaEndereco = eDAO.buscarTodos();
		for(int i = 0; i < listaEndereco.size(); i++) {
			System.out.println("Lista de endereços: " + listaEndereco.get(i).getRua());
		}
	}
}
