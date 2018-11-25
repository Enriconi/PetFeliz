package br.com.integrador.petshop.persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.integrador.petshop.model.Pedido;

public class PedidoDAO implements ObrigatorioDAO {

	private ConexaoMysql conexao;

	public PedidoDAO() {
		super();
		this.conexao = new ConexaoMysql("localhost", "root", "", "pet_feliz");
	}

	// CADASTRAR
	public Object cadastrar(Object objeto) {

		Pedido pedido = (Pedido) objeto;
		
		this.conexao.abrirConexao();
		String sqlInsert = "INSERT INTO pedido VALUES(null, ?, ?, ?);";
		try {
			
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
			
			statement.setString(1, pedido.getDataPedidoEmissao());
			statement.setString(2, pedido.getDescricaoPedido());
			statement.setInt(3, pedido.getQtdeItemPedido());
			statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();
			
			if (rs.next()) {
				pedido.setIdPedido(rs.getLong(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return pedido;
	}

	// EDITAR 
	public void editar(Object objeto) {
		
		Pedido pedido = (Pedido) objeto;
		
		this.conexao.abrirConexao();
		String sqlUpdate = "UPDATE pedido SET data_pedido_emissao=?, descricao_pedido=?, qtde_item_pedido=? WHERE id_pedido=?;";

		try {
			
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlUpdate);
		
			statement.setString(1, pedido.getDataPedidoEmissao());
			statement.setString(2, pedido.getDescricaoPedido());
			statement.setInt(3, pedido.getQtdeItemPedido());
			statement.setLong(4, pedido.getIdPedido());
			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
	}

	// EXCLUIR
	public void excluir(long id) {
		
		this.conexao.abrirConexao();
		String sqlDelete = "DELETE FROM pedido WHERE id_pedido=?;";
		
		try {
			
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlDelete);
			statement.setLong(1, id);

			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
	}

	// BUSCAR TODOS
	public List<Object> buscarTodos() {
		
		this.conexao.abrirConexao();
		String sqlSelect = "SELECT * FROM pedido;";
		PreparedStatement statement;
		Pedido pedido = null;
		List<Object> listaPedidos = new ArrayList<Object>();
		
		try {
			
			statement = this.conexao.getConexao().prepareStatement(sqlSelect);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				pedido = new Pedido();
				pedido.setIdPedido(rs.getLong("id_pedido"));
				pedido.setDataPedidoEmissao(rs.getString("data_pedido_emissao"));
				pedido.setDescricaoPedido(rs.getString("descricao_pedido"));
				pedido.setQtdeItemPedido(rs.getInt("qtde_item_pedido"));
				
				listaPedidos.add(pedido);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return listaPedidos;
	}

	// BUSCAR TODOS
	public Object buscarPorId(long id) {
		
		this.conexao.abrirConexao();
		String sqlInsert = "SELECT * FROM pedido WHERE id_pedido=?;";
		PreparedStatement statement;
		Pedido pedido = null;
		
		try {
	
			statement = this.conexao.getConexao().prepareStatement(sqlInsert);
			statement.setLong(1, id);
			
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				pedido = new Pedido();
				pedido.setIdPedido(rs.getLong("id_pedido"));
				pedido.setDataPedidoEmissao(rs.getString("data_pedido_emissao"));
				pedido.setDescricaoPedido(rs.getString("descricao_pedido"));
				pedido.setQtdeItemPedido(rs.getInt("qtde_item_pedido"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return pedido;
	}
}