package br.com.integrador.petshop.repository;

import java.sql.PreparedStatement;    
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.integrador.petshop.model.Pedido;


public class PedidoDAO {
	
	private ConexaoMysql conexao;
	
	public PedidoDAO() {
		super();
		this.conexao = new ConexaoMysql("localhost", "root", "", "pet_feliz");
	}
	
	//-----------------------------CADASTRAR-------------------------------------------------------

	public Pedido cadastrar(Pedido pedido) {
		
		// ABRIR A CONEXAO COM O BANCO		
		this.conexao.abrirConexao();
		// SQL COM A OPERACAO QUE DESEJA-SE REALIZAR
		String sqlInsert = "INSERT INTO pedido VALUES(null, ?, ?, ?);";
		try {
			// DECLARA E INICIALIZA UM STATEMENT, OBJETO USADO PARA PREPARAR O SQL A SER EXECUTADO
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
			// SUBSTITUIR AS INTERROGACOES PELOS VALORES QUE ESTAO NO OBJETO PEDIDO
			statement.setString(1, pedido.getDataPedidoEmissao());
			statement.setString(2, pedido.getDescricaoPedido());
			statement.setInt(3, pedido.getQtdeItemPedido());
	
			// EXECUTAR A INSTRUCAO NO BANCO
			statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();
			if(rs.next()){
				//pega o id
				pedido.setIdPedido(rs.getLong(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// FECHAR A CONEXAO COM O BANCO
			this.conexao.fecharConexao();
		}
		return pedido;
	}
	
	//--------------------------------EDITAR---------------------------------------
	
		// id_pedido=1;
		public void editar(Pedido pedido) {
			// ABRIR A CONEXAO COM O BANCO
			this.conexao.abrirConexao();
			// SQL COM A OPERACAO QUE DESEJA-SE REALIZAR
			String sqlUpdate = "UPDATE pedido SET data_pedido_emissao=?, descricao_pedido=?, qtde_item_pedido=? WHERE id_pedido=?;";

			try {
				// DECLARA E INICIALIZA UM STATEMENT, OBJETO USADO PARA PREPARAR O SQL A SER EXECUTADO
				PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlUpdate);
				// SUBSTITUIR AS INTERROGACOES PELOS VALORES QUE ESTAO NO OBJETO PEDIDO
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

		//---------------------------------EXCLUIR-------------------------------------
		// DELETE FROM pedido WHERE id_pedido=3;
			public void excluir(long id) {
				// ABRIR A CONEXAO COM O BANCO
				this.conexao.abrirConexao();
				// SQL COM A OPERACAO QUE DESEJA-SE REALIZAR
				String sqlDelete = "DELETE FROM pedido WHERE id_pedido=?;";
				// DECLARA E INICIALIZA UM STATEMENT, OBJETO USADO PARA PREPARAR O SQL A SER EXECUTADO
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
		
			//------------------------------BUSCAR TODOS-------------------------------------------------------------

			// SELECT * FROM pedido;
			public List<Pedido> buscarTodos() {
				// ABRIR A CONEXAO COM O BANCO
				this.conexao.abrirConexao();
				// SQL COM A OPERACAO QUE DESEJA-SE REALIZAR
				String sqlSelect = "SELECT * FROM pedido;";
				PreparedStatement statement;
				Pedido pedido = null;
				List<Pedido> listaPedidos = new ArrayList<Pedido>();
				try {
					statement = this.conexao.getConexao().prepareStatement(sqlSelect);
					ResultSet rs = statement.executeQuery();
					
					while(rs.next()) {
						// Converter um objeto ResultSet em um objeto Pedido
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
		
			//--------------------------------BUSCAR POR ID-----------------------------------------------		

			// SELECT * FROM pedido WHERE id_pedido=2;
			public Pedido buscarPorId(long id) {
				// ABRIR A CONEXAO COM O BANCO
				this.conexao.abrirConexao();
				// SQL COM A OPERACAO QUE DESEJA-SE REALIZAR
				String sqlInsert = "SELECT * FROM pedido WHERE id_pedido=?;";
				PreparedStatement statement;
				Pedido pedido = null;
				try {
					statement = this.conexao.getConexao().prepareStatement(sqlInsert);
					statement.setLong(1, id);
					ResultSet rs = statement.executeQuery();
					if(rs.next()) {
						// Converter um objeto ResultSet em um objeto Pedido
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
			
			//------------------------------BUSCAR POR LOGIN E SENHA------------------------------
			//NAO UTIL PARA ESTE DAO

			// SELECT * FROM usuario WHERE email=? AND senha=?;
//			public Usuario buscarPorLoginESenha(String email, String senha) {
//				// ABRIR A CONEXaO COM O BANCO
//				this.conexao.abrirConexao();
//				// SQL COM A OPERA��O QUE DESEJA-SE REALIZAR
//				String sqlInsert = "SELECT * FROM usuario WHERE email=? AND senha=?;";
//				PreparedStatement statement;
//				Usuario usuario = null;
//				try {
//					statement = this.conexao.getConexao().prepareStatement(sqlInsert);
//					statement.setString(1, email);
//					statement.setString(2, senha);
//					ResultSet rs = statement.executeQuery();
//					if(rs.next()) {
//						// Converter um objeto ResultSet em um objeto Usuario
//						usuario = new Usuario();
//						usuario.setIdUsuario(rs.getLong("id_usuario"));
//						usuario.setEmail(rs.getString("email"));
//						usuario.setNomeUsuario(rs.getString("nome_usuario"));
//						usuario.setSenha(rs.getString("senha"));
//						usuario.setFotoUsuario(rs.getString("foto_usuario"));
//					}
//				} catch (SQLException e) {
//					e.printStackTrace();
//				} finally {
//					this.conexao.fecharConexao();
//				}
//				return usuario;
//			}
	}