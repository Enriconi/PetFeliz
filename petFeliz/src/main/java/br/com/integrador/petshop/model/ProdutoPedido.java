package br.com.integrador.petshop.model;

public class ProdutoPedido {
	
	private long idProdutoPedido;
	private Produto produto;
	private Pedido pedido;
	
	public ProdutoPedido() {
		
	}

	public ProdutoPedido(long idProdutoPedido, Produto produto, Pedido pedido) {
		super();
		this.idProdutoPedido = idProdutoPedido;
		this.produto = produto;
		this.pedido = pedido;
	}

	public long getIdProdutoPedido() {
		return idProdutoPedido;
	}

	public void setIdProdutoPedido(long idProdutoPedido) {
		this.idProdutoPedido = idProdutoPedido;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
}
