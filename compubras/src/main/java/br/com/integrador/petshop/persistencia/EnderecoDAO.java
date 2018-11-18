package br.com.integrador.petshop.persistencia;

import java.sql.PreparedStatement;    
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.integrador.petshop.model.Cliente;
import br.com.integrador.petshop.model.Endereco;


public class EnderecoDAO {
	
	private ConexaoMysql conexao;
	
	public EnderecoDAO() {
		super();
		this.conexao = new ConexaoMysql("localhost", "root", "", "pet_feliz");
	}
	
	//-----------------------------CADASTRAR-------------------------------------------------------

	public Endereco cadastrar(Endereco endereco) {
		
		// ABRIR A CONEXAO COM O BANCO		
		this.conexao.abrirConexao();
		// SQL COM A OPERACAO QUE DESEJA-SE REALIZAR
		String sqlInsert = "INSERT INTO endereco VALUES(null, ?, ?, ?, ?, ?, ?, ?);";
		try {
			
			// DECLARA E INICIALIZA UM STATEMENT, OBJETO USADO PARA PREPARAR O SQL A SER EXECUTADO
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
			// SUBSTITUIR AS INTERROGACOES PELOS VALORES QUE ESTAO NO OBJETO ENDERECO
			statement.setString(1, endereco.getCep());
			statement.setString(2, endereco.getEstado());
			statement.setString(3, endereco.getCidade());
			statement.setString(4, endereco.getBairro());
			statement.setString(5, endereco.getRua());
			statement.setInt(6, endereco.getNumero());
			statement.setLong(7, endereco.getCliente().getIdCliente());
			// EXECUTAR A INSTRUCAO NO BANCO
			statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();
			if(rs.next()){
				//pega o id
				endereco.setIdEndereco(rs.getLong(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// FECHAR A CONEXAO COM O BANCO
			this.conexao.fecharConexao();
		}
		return endereco;
	}
	
	//--------------------------------EDITAR---------------------------------------
	
		// id_endereco=1;
		public void editar(Endereco endereco) {
			// ABRIR A CONEXAO COM O BANCO
			this.conexao.abrirConexao();
			// SQL COM A OPERACAO QUE DESEJA-SE REALIZAR
			String sqlUpdate = "UPDATE endereco SET cep=?, estado=?, cidade=?, bairro=?, rua=?, numero=?, id_cliente=? WHERE id_endereco=?;";

			try {
				// DECLARA E INICIALIZA UM STATEMENT, OBJETO USADO PARA PREPARAR O SQL A SER EXECUTADO
				PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlUpdate);
				// SUBSTITUIR AS INTERROGACOES PELOS VALORES QUE ESTAO NO OBJETO ENDERECO
				statement.setString(1, endereco.getCep());
				statement.setString(2, endereco.getEstado());
				statement.setString(3, endereco.getCidade());
				statement.setString(4, endereco.getBairro());
				statement.setString(5, endereco.getRua());
				statement.setInt(6, endereco.getNumero());
				statement.setLong(7, endereco.getCliente().getIdCliente());
				statement.setLong(8, endereco.getIdEndereco());

				statement.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				this.conexao.fecharConexao();
			}
		}

		//---------------------------------EXCLUIR-------------------------------------
		// DELETE FROM endereco WHERE id_endereco=3;
			public void excluir(long id) {
				// ABRIR A CONEXAO COM O BANCO
				this.conexao.abrirConexao();
				// SQL COM A OPERACAO QUE DESEJA-SE REALIZAR
				String sqlDelete = "DELETE FROM endereco WHERE id_endereco=?;";
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

			// SELECT * FROM endereco;
			public List<Endereco> buscarTodos() {
				// ABRIR A CONEXAO COM O BANCO
				this.conexao.abrirConexao();
				// SQL COM A OPERACAO QUE DESEJA-SE REALIZAR
				String sqlSelect = "SELECT * FROM endereco e INNER JOIN cliente c ON e.id_cliente = c.id_cliente;";
				PreparedStatement statement;
				Endereco endereco = null;
				List<Endereco> listaEnderecos = new ArrayList<Endereco>();
				try {
					statement = this.conexao.getConexao().prepareStatement(sqlSelect);
					ResultSet rs = statement.executeQuery();
					
					while(rs.next()) {
						// Converter um objeto ResultSet em um objeto Endereco
						endereco = new Endereco();
						endereco.setIdEndereco(rs.getLong("id_endereco"));
						endereco.setCep(rs.getString("cep"));
						endereco.setEstado(rs.getString("estado"));
						endereco.setCidade(rs.getString("cidade"));
						endereco.setBairro(rs.getString("bairro"));
						endereco.setRua(rs.getString("rua"));
						endereco.setNumero(rs.getInt("numero"));
						
						Cliente cliente = new Cliente();
						cliente.setIdCliente(rs.getLong("id_cliente"));
						cliente.setNomeCliente(rs.getString("nome_cliente"));
						cliente.setCpfCliente(rs.getString("cpf_cliente"));
						cliente.setEmailCliente(rs.getString("email_cliente"));
						cliente.setSenhaCliente(rs.getString("senha_cliente"));
						cliente.setTelefoneCliente(rs.getString("telefone_cliente"));
					
						endereco.setCliente(cliente);
						listaEnderecos.add(endereco);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					this.conexao.fecharConexao();
				}
				return listaEnderecos;
			}
		
			//--------------------------------BUSCAR POR ID-----------------------------------------------		

			// SELECT * FROM endereco WHERE id_endereco=2;
			public Endereco buscarPorId(long id) {
				// ABRIR A CONEXAO COM O BANCO
				this.conexao.abrirConexao();
				// SQL COM A OPERACAO QUE DESEJA-SE REALIZAR
				String sqlInsert = "SELECT * FROM endereco e INNER JOIN cliente c ON e.id_cliente = c.id_cliente WHERE id_endereco=?;";
				PreparedStatement statement;
				Endereco endereco = null;
				try {
					statement = this.conexao.getConexao().prepareStatement(sqlInsert);
					statement.setLong(1, id);
					ResultSet rs = statement.executeQuery();
					if(rs.next()) {
						// Converter um objeto ResultSet em um objeto Endereco
						endereco = new Endereco();
						endereco.setIdEndereco(rs.getLong("id_endereco"));
						endereco.setCep(rs.getString("cep"));
						endereco.setEstado(rs.getString("estado"));
						endereco.setCidade(rs.getString("cidade"));
						endereco.setBairro(rs.getString("bairro"));
						endereco.setRua(rs.getString("rua"));
						endereco.setNumero(rs.getInt("numero"));
						
						Cliente cliente = new Cliente();
						cliente.setIdCliente(rs.getLong("id_cliente"));
						cliente.setNomeCliente(rs.getString("nome_cliente"));
						cliente.setCpfCliente(rs.getString("cpf_cliente"));
						cliente.setEmailCliente(rs.getString("email_cliente"));
						cliente.setSenhaCliente(rs.getString("senha_cliente"));
						cliente.setTelefoneCliente(rs.getString("telefone_cliente"));
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					this.conexao.fecharConexao();
				}
				return endereco;
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