package br.com.integrador.petshop;

import java.util.List;

import br.com.integrador.petshop.model.Pedido;
import br.com.integrador.petshop.repository.PedidoDAO;

public class TestePedido {
	public static void main(String[] args) {
	
//		Pedido pedido = new Pedido(1, "19/05/2010", "Cuidado, é frágil!", 10);
//	
//		PedidoDAO pDAO = new PedidoDAO();
//		pDAO.cadastrar(pedido);
		
//		Pedido pedido = new Pedido(1, "15/11/2018", "Cuidado, é frágil!", 10);
//		
//		PedidoDAO pDAO = new PedidoDAO();
//		pDAO.editar(pedido);
//		
//		PedidoDAO pDAO = new PedidoDAO();
//		Pedido pedido = pDAO.buscarPorId(1);
//		System.out.println("Pedido buscado por ID: " + pedido.getDataPedidoEmissao());
		
	
		PedidoDAO pDAO = new PedidoDAO();
		List<Pedido> listaPedidos = pDAO.buscarTodos();
		for(int i = 0; i < listaPedidos.size(); i++) {
			System.out.println("Lista de PetShops: " + listaPedidos.get(i).getDataPedidoEmissao());
		}
	}
}
