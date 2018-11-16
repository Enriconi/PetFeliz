package br.com.integrador.petshop.model;

public class ClientePedido {
	
	private long idClientePedido;
	Cliente cliente;
	Pedido pedido;
	
	public ClientePedido() {
		
	}

	public ClientePedido(long idClientePedido, Cliente cliente, Pedido pedido) {
		super();
		this.idClientePedido = idClientePedido;
		this.cliente = cliente;
		this.pedido = pedido;
	}

	public long getIdClientePedido() {
		return idClientePedido;
	}

	public void setIdClientePedido(long idClientePedido) {
		this.idClientePedido = idClientePedido;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
}
