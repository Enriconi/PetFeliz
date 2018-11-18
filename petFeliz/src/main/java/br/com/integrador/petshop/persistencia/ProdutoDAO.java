package br.com.integrador.petshop.persistencia;

import java.sql.PreparedStatement;    
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.integrador.petshop.model.Petshop;
import br.com.integrador.petshop.model.Produto;


public class ProdutoDAO {
	
	private ConexaoMysql conexao;
	
	public ProdutoDAO() {
		super();
		this.conexao = new ConexaoMysql("localhost", "root", "", "pet_feliz");
	}
	
	//-----------------------------CADASTRAR-------------------------------------------------------

	public Produto cadastrar(Produto produto) {
		
		// ABRIR A CONEXAO COM O BANCO		
		this.conexao.abrirConexao();
		// SQL COM A OPERACAO QUE DESEJA-SE REALIZAR
		String sqlInsert = "INSERT INTO produto VALUES(null, ?, ?, ?, ?, ?);";
		try {
			// DECLARA E INICIALIZA UM STATEMENT, OBJETO USADO PARA PREPARAR O SQL A SER EXECUTADO
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
			// SUBSTITUIR AS INTERROGACOES PELOS VALORES QUE ESTAO NO OBJETO PRODUTO
			statement.setString(1, produto.getNomeProduto());
			statement.setString(2, produto.getDescricaoProduto());
			statement.setDouble(3, produto.getPrecoProduto());
			statement.setInt(4, produto.getEstoqueProduto());
			statement.setLong(5, produto.getPetshop().getIdPetshop());
			// EXECUTAR A INSTRUCAO NO BANCO
			statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();
			if(rs.next()){
				//pega o id
				produto.setIdProduto(rs.getLong(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// FECHAR A CONEXAO COM O BANCO
			this.conexao.fecharConexao();
		}
		return produto;
	}
	
	//--------------------------------EDITAR---------------------------------------
	
		// id_produto=1;
		public void editar(Produto produto) {
			// ABRIR A CONEXAO COM O BANCO
			this.conexao.abrirConexao();
			// SQL COM A OPERACAO QUE DESEJA-SE REALIZAR
			String sqlUpdate = "UPDATE produto SET nome_produto=?, descricao_produto=?, preco_produto=?, estoque_produto=?, id_petshop=? WHERE id_produto=?;";

			try {
				// DECLARA E INICIALIZA UM STATEMENT, OBJETO USADO PARA PREPARAR O SQL A SER EXECUTADO
				PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlUpdate);
				// SUBSTITUIR AS INTERROGACOES PELOS VALORES QUE ESTAO NO OBJETO PRODUTO
				statement.setString(1, produto.getNomeProduto());
				statement.setString(2, produto.getDescricaoProduto());
				statement.setDouble(3, produto.getPrecoProduto());
				statement.setInt(4, produto.getEstoqueProduto());
				statement.setLong(5, produto.getPetshop().getIdPetshop());
				statement.setLong(6, produto.getIdProduto());
				statement.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				this.conexao.fecharConexao();
			}
		}

		//---------------------------------EXCLUIR-------------------------------------
		// DELETE FROM produto WHERE id_produto=3;
			public void excluir(long id) {
				// ABRIR A CONEXAO COM O BANCO
				this.conexao.abrirConexao();
				// SQL COM A OPERACAO QUE DESEJA-SE REALIZAR
				String sqlDelete = "DELETE FROM produto WHERE id_produto=?;";
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

			// SELECT * FROM produto;
			public List<Produto> buscarTodos() {
				// ABRIR A CONEXAO COM O BANCO
				this.conexao.abrirConexao();
				// SQL COM A OPERACAO QUE DESEJA-SE REALIZAR
				String sqlSelect = "SELECT * FROM produto prod INNER JOIN petshop p ON prod.id_petshop = p.id_petshop;";
				PreparedStatement statement;
				Produto produto = null;
				List<Produto> listaProdutos = new ArrayList<Produto>();
				try {
					statement = this.conexao.getConexao().prepareStatement(sqlSelect);
					ResultSet rs = statement.executeQuery();
					
					while(rs.next()) {
						// Converter um objeto ResultSet em um objeto Produto
						produto = new Produto();
						produto.setIdProduto(rs.getLong("id_produto"));
						produto.setNomeProduto(rs.getString("nome_produto"));
						produto.setDescricaoProduto(rs.getString("descricao_produto"));
						produto.setPrecoProduto(rs.getInt("preco_produto"));
						produto.setEstoqueProduto(rs.getInt("estoque_produto"));
						
						Petshop petshop = new Petshop();
						petshop.setIdPetshop(rs.getLong("id_petshop"));
						petshop.setNomePetshop(rs.getString("nome_petshop"));
						petshop.setCnpjPetshop(rs.getString("cnpj_petshop"));
						petshop.setEmailPetshop(rs.getString("email_petshop"));
						petshop.setSenhaPetshop(rs.getString("senha_petshop"));
					
						produto.setPetshop(petshop);
						listaProdutos.add(produto);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					this.conexao.fecharConexao();
				}
				return listaProdutos;
			}
		
			//--------------------------------BUSCAR POR ID-----------------------------------------------		

			// SELECT * FROM produto WHERE id_produto=2;
			public Produto buscarPorId(long id) {
				// ABRIR A CONEXAO COM O BANCO
				this.conexao.abrirConexao();
				// SQL COM A OPERACAO QUE DESEJA-SE REALIZAR
				String sqlInsert =  "SELECT * FROM produto prod INNER JOIN petshop p ON prod.id_petshop = p.id_petshop WHERE id_produto=?;";
				PreparedStatement statement;
				Produto produto = null;
				try {
					statement = this.conexao.getConexao().prepareStatement(sqlInsert);
					statement.setLong(1, id);
					ResultSet rs = statement.executeQuery();
					if(rs.next()) {
						// Converter um objeto ResultSet em um objeto Produto
						produto = new Produto();
						produto.setIdProduto(rs.getLong("id_produto"));
						produto.setNomeProduto(rs.getString("nome_produto"));
						produto.setDescricaoProduto(rs.getString("descricao_produto"));
						produto.setPrecoProduto(rs.getInt("preco_produto"));
						produto.setEstoqueProduto(rs.getInt("estoque_produto"));
						
						Petshop petshop = new Petshop();
						petshop.setIdPetshop(rs.getLong("id_petshop"));
						petshop.setNomePetshop(rs.getString("nome_petshop"));
						petshop.setCnpjPetshop(rs.getString("cnpj_petshop"));
						petshop.setEmailPetshop(rs.getString("email_petshop"));
						petshop.setSenhaPetshop(rs.getString("senha_petshop"));
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					this.conexao.fecharConexao();
				}
				return produto;
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