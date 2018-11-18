package br.com.integrador.petshop;

import java.util.List;

import br.com.integrador.petshop.model.Cliente;
import br.com.integrador.petshop.model.Petshop;
import br.com.integrador.petshop.persistencia.ClienteDAO;



public class TesteCliente {
	public static void main(String[] args) {
		
		Petshop petshop = new Petshop();
		petshop.setIdPetshop(2);
//		
//		Cliente cliente = new Cliente();		
//		cliente.setNomeCliente("Gabriel Soares Enriconi");
//		cliente.setCpfCliente("023.357.080-23");
//		cliente.setEmailCliente("gabriel.enriconi@gmail.com");
//		cliente.setSenhaCliente("Batata123");
//		cliente.setTelefoneCliente("051 9 8322-2090");
//		
//		ClienteDAO cDAO = new ClienteDAO();
//		
//		cliente.setPetshop(petshop);
//		cDAO.cadastrar(cliente);
		
//		Cliente petshop = new Cliente();
//		petshop.setIdCliente(2);
//		
//		Cliente cliente = new Cliente();		
//		cliente.setNomeCliente("Gabriel Enriconi");
//		cliente.setCpfCliente("023.357.080-23");
//		cliente.setEmailCliente("gabriel.enriconi@gmail.com");
//		cliente.setSenhaCliente("Batata123.");
//		cliente.setTelefoneCliente("051 9 8322-2090");
//		cliente.setIdCliente(2);
//		
//		ClienteDAO cDAO = new ClienteDAO();
//		
//		cliente.setCliente(petshop);
//		cDAO.editar(cliente);
		
//		ClienteDAO cDAO = new ClienteDAO();
//		cDAO.excluir(7);
		
//		ClienteDAO cDAO = new ClienteDAO();
//		Cliente cliente = cDAO.buscarPorId(2);
//		System.out.println("Cliente buscada por ID: " + cliente.getNomeCliente());
		
	
		ClienteDAO cDAO = new ClienteDAO();
		List<Cliente> listaCliente = cDAO.buscarTodos();
		for(int i = 0; i < listaCliente.size(); i++) {
			System.out.println("Lista de Clientes: " + listaCliente.get(i).getNomeCliente());
		}
	}
}
