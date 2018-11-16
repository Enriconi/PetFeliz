package br.com.integrador.petshop.model;

public class Pedido {
	
	private long idPedido;
	private String dataPedidoEmissao;
	private String descricaoPedido;
	private int QtdeItemPedido;
	
	public Pedido() {
		
	}

	public Pedido(long idPedido, String dataPedidoEmissao, String descricaoPedido, int qtdeItemPedido) {
		super();
		this.idPedido = idPedido;
		this.dataPedidoEmissao = dataPedidoEmissao;
		this.descricaoPedido = descricaoPedido;
		this.QtdeItemPedido = qtdeItemPedido;
	}

	public long getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(long idPedido) {
		this.idPedido = idPedido;
	}

	public String getDataPedidoEmissao() {
		return dataPedidoEmissao;
	}

	public void setDataPedidoEmissao(String dataPedidoEmissao) {
		this.dataPedidoEmissao = dataPedidoEmissao;
	}

	public String getDescricaoPedido() {
		return descricaoPedido;
	}

	public void setDescricaoPedido(String descricaoPedido) {
		this.descricaoPedido = descricaoPedido;
	}

	public int getQtdeItemPedido() {
		return QtdeItemPedido;
	}

	public void setQtdeItemPedido(int qtdeItemPedido) {
		QtdeItemPedido = qtdeItemPedido;
	}	
}
