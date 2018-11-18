package br.com.integrador.petshop.persistencia;

import java.sql.PreparedStatement;    
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.integrador.petshop.model.Cliente;
import br.com.integrador.petshop.model.ClientePedido;
import br.com.integrador.petshop.model.Pedido;


public class ClientePedidoDAO {
	
	private ConexaoMysql conexao;
	
	public ClientePedidoDAO() {
		super();
		this.conexao = new ConexaoMysql("localhost", "root", "", "pet_feliz");
	}
	
	//-----------------------------CADASTRAR-------------------------------------------------------

	public ClientePedido cadastrar(ClientePedido clientePedido) {
		
		// ABRIR A CONEXAO COM O BANCO		
		this.conexao.abrirConexao();
		// SQL COM A OPERACAO QUE DESEJA-SE REALIZAR
		String sqlInsert = "INSERT INTO cliente_pedido VALUES(null, ?, ?);";
		try {
			// DECLARA E INICIALIZA UM STATEMENT, OBJETO USADO PARA PREPARAR O SQL A SER EXECUTADO
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
			// SUBSTITUIR AS INTERROGACOES PELOS VALORES QUE ESTAO NO OBJETO CLIENTEPEDIDO
			statement.setLong(1, clientePedido.getCliente().getIdCliente());
			statement.setLong(2, clientePedido.getPedido().getIdPedido());
			// EXECUTAR A INSTRUCAO NO BANCO
			statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();
			if(rs.next()){
				//pega o id
				clientePedido.setIdClientePedido(rs.getLong(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// FECHAR A CONEXAO COM O BANCO
			this.conexao.fecharConexao();
		}
		return clientePedido;
	}
	
	//--------------------------------EDITAR---------------------------------------
	
		// id_cliente_pedido=1;
		public void editar(ClientePedido clientePedido) {
			// ABRIR A CONEXAO COM O BANCO
			this.conexao.abrirConexao();
			// SQL COM A OPERACAO QUE DESEJA-SE REALIZAR
			String sqlUpdate = "UPDATE cliente_pedido SET id_cliente=?, id_pedido=? WHERE id_cliente_pedido=?;";

			try {
				// DECLARA E INICIALIZA UM STATEMENT, OBJETO USADO PARA PREPARAR O SQL A SER EXECUTADO
				PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlUpdate);
				// SUBSTITUIR AS INTERROGACOES PELOS VALORES QUE ESTAO NO OBJETO CLIENTEPEDIDO
				statement.setLong(1, clientePedido.getCliente().getIdCliente());
				statement.setLong(2, clientePedido.getPedido().getIdPedido());
				statement.setLong(3, clientePedido.getIdClientePedido());
				statement.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				this.conexao.fecharConexao();
			}
		}

		//---------------------------------EXCLUIR-------------------------------------
		// DELETE FROM cliente_pedido WHERE id_cliente_pedido=3;
			public void excluir(long id) {
				// ABRIR A CONEXAO COM O BANCO
				this.conexao.abrirConexao();
				// SQL COM A OPERACAO QUE DESEJA-SE REALIZAR
				String sqlDelete = "DELETE FROM cliente_pedido WHERE id_cliente_pedido=?;";
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

			public List<ClientePedido> buscarTodos() {
				// ABRIR A CONEXAO COM O BANCO
				this.conexao.abrirConexao();
				// SQL COM A OPERACAO QUE DESEJA-SE REALIZAR
				String sqlSelect = "SELECT * FROM cliente_pedido cp INNER JOIN cliente c ON cp.id_cliente = c.id_pedido INNER JOIN pedido p ON pp.id_pedido = p.id_pedido;";
				PreparedStatement statement;
				ClientePedido clientePedido = null;
				List<ClientePedido> listaClientePedido = new ArrayList<ClientePedido>();
				try {
					statement = this.conexao.getConexao().prepareStatement(sqlSelect);
					ResultSet rs = statement.executeQuery();
					
					while(rs.next()) {
						// Converter um objeto ResultSet em um objeto ClientePedido
						clientePedido = new ClientePedido();
						clientePedido.setIdClientePedido(rs.getLong("id_cliente_pedido"));
						
						Cliente cliente = new Cliente();
						cliente = new Cliente();
						cliente.setIdCliente(rs.getLong("id_cliente"));
						cliente.setNomeCliente(rs.getString("nome_cliente"));
						cliente.setCpfCliente(rs.getString("cpf_cliente"));
						cliente.setEmailCliente(rs.getString("email_cliente"));
						cliente.setSenhaCliente(rs.getString("senha_cliente"));
						cliente.setTelefoneCliente(rs.getString("telefone_cliente"));
						
						Pedido pedido = new Pedido();
						pedido.setIdPedido(rs.getLong("id_pedido"));
						pedido.setDataPedidoEmissao(rs.getString("data_pedido_emissao"));
						pedido.setDescricaoPedido(rs.getString("descricao_pedido"));
						pedido.setQtdeItemPedido(rs.getInt("qtde_item_pedido"));
						
						clientePedido.setCliente(cliente);
						clientePedido.setPedido(pedido);
						listaClientePedido.add(clientePedido);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					this.conexao.fecharConexao();
				}
				return listaClientePedido;
			}
		
			//--------------------------------BUSCAR POR ID-----------------------------------------------		

			public ClientePedido buscarPorId(long id) {
				// ABRIR A CONEXAO COM O BANCO
				this.conexao.abrirConexao();
				// SQL COM A OPERACAO QUE DESEJA-SE REALIZAR
				String sqlInsert = "SELECT * FROM cliente_pedido cp INNER JOIN cliente c ON cp.id_cliente = c.id_pedido INNER JOIN pedido p ON pp.id_pedido = p.id_pedido WHERE id_cliente_pedido=?;";
				PreparedStatement statement;
				ClientePedido clientePedido = null;
				try {
					statement = this.conexao.getConexao().prepareStatement(sqlInsert);
					statement.setLong(1, id);
					ResultSet rs = statement.executeQuery();
					if(rs.next()) {
						// Converter um objeto ResultSet em um objeto ClientePedido
						clientePedido = new ClientePedido();
						clientePedido.setIdClientePedido(rs.getLong("id_cliente_pedido"));
						
						Cliente cliente = new Cliente();
						cliente = new Cliente();
						cliente.setIdCliente(rs.getLong("id_cliente"));
						cliente.setNomeCliente(rs.getString("nome_cliente"));
						cliente.setCpfCliente(rs.getString("cpf_cliente"));
						cliente.setEmailCliente(rs.getString("email_cliente"));
						cliente.setSenhaCliente(rs.getString("senha_cliente"));
						cliente.setTelefoneCliente(rs.getString("telefone_cliente"));
						
						Pedido pedido = new Pedido();
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
				return clientePedido;
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