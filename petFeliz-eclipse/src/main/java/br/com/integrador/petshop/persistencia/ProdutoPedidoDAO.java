package br.com.integrador.petshop.persistencia;

import java.sql.PreparedStatement;    
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.integrador.petshop.model.Pedido;
import br.com.integrador.petshop.model.Produto;
import br.com.integrador.petshop.model.ProdutoPedido;


public class ProdutoPedidoDAO {
	
	private ConexaoMysql conexao;
	
	public ProdutoPedidoDAO() {
		super();
		this.conexao = new ConexaoMysql("localhost", "root", "", "pet_feliz");
	}
	
	//-----------------------------CADASTRAR-------------------------------------------------------

	public ProdutoPedido cadastrar(ProdutoPedido produtoPedido) {
		
		// ABRIR A CONEXAO COM O BANCO		
		this.conexao.abrirConexao();
		// SQL COM A OPERACAO QUE DESEJA-SE REALIZAR
		String sqlInsert = "INSERT INTO produto_pedido VALUES(null, ?, ?);";
		try {
			// DECLARA E INICIALIZA UM STATEMENT, OBJETO USADO PARA PREPARAR O SQL A SER EXECUTADO
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
			// SUBSTITUIR AS INTERROGACOES PELOS VALORES QUE ESTAO NO OBJETO PETSHOPRODUTOS
			statement.setLong(1, produtoPedido.getPedido().getIdPedido());
			statement.setLong(2, produtoPedido.getProduto().getIdProduto());
			// EXECUTAR A INSTRUCAO NO BANCO
			statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();
			if(rs.next()){
				//pega o id
				produtoPedido.setIdProdutoPedido(rs.getLong(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// FECHAR A CONEXAO COM O BANCO
			this.conexao.fecharConexao();
		}
		return produtoPedido;
	}
	
	//--------------------------------EDITAR---------------------------------------
	
		// id_produto_pedido=1;
		public void editar(ProdutoPedido produtoPedido) {
			// ABRIR A CONEXAO COM O BANCO
			this.conexao.abrirConexao();
			// SQL COM A OPERACAO QUE DESEJA-SE REALIZAR
			String sqlUpdate = "UPDATE produto_pedido SET id_pedido=?, id_produto=? WHERE id_produto_pedido=?;";

			try {
				// DECLARA E INICIALIZA UM STATEMENT, OBJETO USADO PARA PREPARAR O SQL A SER EXECUTADO
				PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlUpdate);
				// SUBSTITUIR AS INTERROGACOES PELOS VALORES QUE ESTAO NO OBJETO PRODUTOPEDIDO
				statement.setLong(1, produtoPedido.getPedido().getIdPedido());
				statement.setLong(2, produtoPedido.getProduto().getIdProduto());
				statement.setLong(3, produtoPedido.getIdProdutoPedido());
				statement.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				this.conexao.fecharConexao();
			}
		}

		//---------------------------------EXCLUIR-------------------------------------
		// DELETE FROM produto_pedido WHERE id_produto_pedido=3;
			public void excluir(long id) {
				// ABRIR A CONEXAO COM O BANCO
				this.conexao.abrirConexao();
				// SQL COM A OPERACAO QUE DESEJA-SE REALIZAR
				String sqlDelete = "DELETE FROM produto_pedido WHERE id_produto_pedido=?;";
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

			// SELECT * FROM produto_pedido;
			public List<ProdutoPedido> buscarTodos() {
				// ABRIR A CONEXAO COM O BANCO
				this.conexao.abrirConexao();
				// SQL COM A OPERACAO QUE DESEJA-SE REALIZAR
				String sqlSelect = "SELECT * FROM produto_pedido pp INNER JOIN pedido p ON pp.id_pedido = p.id_pedido INNER JOIN produto prod ON pp.id_produto = prod.id_produto;";
				PreparedStatement statement;
				ProdutoPedido produtoPedido = null;
				List<ProdutoPedido> listaProdutoPedido = new ArrayList<ProdutoPedido>();
				try {
					statement = this.conexao.getConexao().prepareStatement(sqlSelect);
					ResultSet rs = statement.executeQuery();
					
					while(rs.next()) {
						// Converter um objeto ResultSet em um objeto ProdutoPedido
						produtoPedido = new ProdutoPedido();
						produtoPedido.setIdProdutoPedido(rs.getLong("id_produto_pedido"));
						
						Pedido pedido = new Pedido();
						pedido.setIdPedido(rs.getLong("id_pedido"));
						pedido.setDataPedidoEmissao(rs.getString("data_pedido_emissao"));
						pedido.setDescricaoPedido(rs.getString("descricao_pedido"));
						pedido.setQtdeItemPedido(rs.getInt("qtde_item_pedido"));
						
						Produto produto = new Produto();
						produto.setIdProduto(rs.getLong("id_produto"));
						produto.setNomeProduto(rs.getString("nome_produto"));
						produto.setDescricaoProduto(rs.getString("descricao_produto"));
						produto.setPrecoProduto(rs.getDouble("preco_produto"));
						produto.setEstoqueProduto(rs.getInt("estoque_produto"));
					
						produtoPedido.setPedido(pedido);
						produtoPedido.setProduto(produto);
						listaProdutoPedido.add(produtoPedido);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					this.conexao.fecharConexao();
				}
				return listaProdutoPedido;
			}
		
			//--------------------------------BUSCAR POR ID-----------------------------------------------		

			// SELECT * FROM produto_pedido WHERE id_produto_pedido=2;
			public ProdutoPedido buscarPorId(long id) {
				// ABRIR A CONEXAO COM O BANCO
				this.conexao.abrirConexao();
				// SQL COM A OPERACAO QUE DESEJA-SE REALIZAR
				String sqlInsert = "SELECT * FROM produto_pedido pp INNER JOIN pedido p ON pp.id_pedido = p.id_pedido INNER JOIN produto prod ON pp.id_produto = prod.id_produto WHERE id_produto_pedido=?;";
				PreparedStatement statement;
				ProdutoPedido produtoPedido = null;
				try {
					statement = this.conexao.getConexao().prepareStatement(sqlInsert);
					statement.setLong(1, id);
					ResultSet rs = statement.executeQuery();
					if(rs.next()) {
						// Converter um objeto ResultSet em um objeto ProdutoPedido
						produtoPedido = new ProdutoPedido();
						produtoPedido.setIdProdutoPedido(rs.getLong("id_produto_pedido"));
						
						Pedido pedido = new Pedido();
						pedido.setIdPedido(rs.getLong("id_pedido"));
						pedido.setDataPedidoEmissao(rs.getString("data_pedido_emissao"));
						pedido.setDescricaoPedido(rs.getString("descricao_pedido"));
						pedido.setQtdeItemPedido(rs.getInt("qtde_item_pedido"));
						
						Produto produto = new Produto();
						produto.setIdProduto(rs.getLong("id_produto"));
						produto.setNomeProduto(rs.getString("nome_produto"));
						produto.setDescricaoProduto(rs.getString("descricao_produto"));
						produto.setPrecoProduto(rs.getInt("preco_produto"));
						produto.setEstoqueProduto(rs.getInt("estoque_produto"));
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					this.conexao.fecharConexao();
				}
				return produtoPedido;
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