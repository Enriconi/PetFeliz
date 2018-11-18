package br.com.integrador.petshop.persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.integrador.petshop.model.Cliente;
import br.com.integrador.petshop.model.Petshop;


public class ClienteDAO {
	
	private ConexaoMysql conexao;
	
	public ClienteDAO() {
		super();
		this.conexao = new ConexaoMysql("localhost", "root", "", "pet_feliz");
	}
	
	//-----------------------------CADASTRAR-------------------------------------------------------

	public Cliente cadastrar(Cliente cliente) {
		
		// ABRIR A CONEXAO COM O BANCO		
		this.conexao.abrirConexao();
		// SQL COM A OPERACAO QUE DESEJA-SE REALIZAR
		String sqlInsert = "INSERT INTO cliente VALUES(null, ?, ?, ?, ?, ?, ?);";
		try {
			
			// DECLARA E INICIALIZA UM STATEMENT, OBJETO USADO PARA PREPARAR O SQL A SER EXECUTADO
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
			// SUBSTITUIR AS INTERROGACOES PELOS VALORES QUE ESTAO NO OBJETO CLIENTE
			statement.setString(1, cliente.getNomeCliente());
			statement.setString(2, cliente.getCpfCliente());
			statement.setString(3, cliente.getEmailCliente());
			statement.setString(4, cliente.getSenhaCliente());
			statement.setString(5, cliente.getTelefoneCliente());
			statement.setLong(6, cliente.getPetshop().getIdPetshop());
			// EXECUTAR A INSTRUCAO NO BANCO
			statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();
			if(rs.next()){
				//pega o id
				cliente.setIdCliente(rs.getLong(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// FECHAR A CONEXAO COM O BANCO
			this.conexao.fecharConexao();
		}
		return cliente;
	}
	
	//--------------------------------EDITAR---------------------------------------
	
		// id_cliente=1;
		public void editar(Cliente cliente) {
			// ABRIR A CONEXAO COM O BANCO
			this.conexao.abrirConexao();
			// SQL COM A OPERACAO QUE DESEJA-SE REALIZAR
			String sqlUpdate = "UPDATE cliente SET nome_cliente=?, cpf_cliente=?, email_cliente=?, senha_cliente=?, telefone_cliente=?, id_petshop=? WHERE id_cliente=?;";
			try {
				// DECLARA E INICIALIZA UM STATEMENT, OBJETO USADO PARA PREPARAR O SQL A SER EXECUTADO
				PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlUpdate);
				// SUBSTITUIR AS INTERROGACOES PELOS VALORES QUE ESTAO NO OBJETO CLIENTE
				statement.setString(1, cliente.getNomeCliente());
				statement.setString(2, cliente.getCpfCliente());
				statement.setString(3, cliente.getEmailCliente());
				statement.setString(4, cliente.getSenhaCliente());
				statement.setString(5, cliente.getTelefoneCliente());
				statement.setLong(6, cliente.getPetshop().getIdPetshop());
				statement.setLong(7, cliente.getIdCliente());

				statement.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				this.conexao.fecharConexao();
			}
		}

		//---------------------------------EXCLUIR-------------------------------------
		// DELETE FROM cliente WHERE id_cliente=3;
			public void excluir(long id) {
				// ABRIR A CONEXAO COM O BANCO
				this.conexao.abrirConexao();
				// SQL COM A OPERACAO QUE DESEJA-SE REALIZAR
				String sqlDelete = "DELETE FROM cliente WHERE id_cliente=?;";
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

			// SELECT * FROM cliente;
			public List<Cliente> buscarTodos() {
				// ABRIR A CONEXAO COM O BANCO
				this.conexao.abrirConexao();
				// SQL COM A OPERACAO QUE DESEJA-SE REALIZAR
				String sqlSelect = "SELECT * FROM cliente c INNER JOIN petshop p ON c.id_petshop = p.id_petshop;";
				PreparedStatement statement;
				Cliente cliente = null;
				List<Cliente> listaClientes = new ArrayList<Cliente>();
				try {
					statement = this.conexao.getConexao().prepareStatement(sqlSelect);
					ResultSet rs = statement.executeQuery();
					
					while(rs.next()) {
						// Converter um objeto ResultSet em um objeto Cliente
						cliente = new Cliente();
						cliente.setIdCliente(rs.getLong("id_cliente"));
						cliente.setNomeCliente(rs.getString("nome_cliente"));
						cliente.setCpfCliente(rs.getString("cpf_cliente"));
						cliente.setEmailCliente(rs.getString("email_cliente"));
						cliente.setSenhaCliente(rs.getString("senha_cliente"));
						cliente.setTelefoneCliente(rs.getString("telefone_cliente"));
						
						Petshop petshop = new Petshop();
						petshop.setIdPetshop(rs.getLong("id_petshop"));
						petshop.setNomePetshop(rs.getString("nome_petshop"));
						petshop.setCnpjPetshop(rs.getString("cnpj_petshop"));
						petshop.setEmailPetshop(rs.getString("email_petshop"));
						petshop.setSenhaPetshop(rs.getString("senha_petshop"));
						petshop.setTelefonePetshop(rs.getString("telefone_petshop"));
						
						cliente.setPetshop(petshop);
						listaClientes.add(cliente);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					this.conexao.fecharConexao();
				}
				return listaClientes;
			}
		
			//--------------------------------BUSCAR POR ID-----------------------------------------------		

			// SELECT * FROM cliente WHERE id_cliente=2;
			public Cliente buscarPorId(long id) {
				// ABRIR A CONEXAO COM O BANCO
				this.conexao.abrirConexao();
				// SQL COM A OPERACAO QUE DESEJA-SE REALIZAR
				String sqlInsert = "SELECT * FROM cliente c INNER JOIN petshop p ON c.id_petshop = p.id_petshop WHERE id_cliente=?;";
				PreparedStatement statement;
				Cliente cliente = null;
				try {
					statement = this.conexao.getConexao().prepareStatement(sqlInsert);
					statement.setLong(1, id);
					ResultSet rs = statement.executeQuery();
					if(rs.next()) {
						// Converter um objeto ResultSet em um objeto Usuario
						cliente = new Cliente();
						cliente.setIdCliente(rs.getLong("id_cliente"));
						cliente.setNomeCliente(rs.getString("nome_cliente"));
						cliente.setCpfCliente(rs.getString("cpf_cliente"));
						cliente.setEmailCliente(rs.getString("email_cliente"));
						cliente.setSenhaCliente(rs.getString("senha_cliente"));
						cliente.setTelefoneCliente(rs.getString("telefone_cliente"));
						
						Petshop petshop = new Petshop();
						petshop.setIdPetshop(rs.getLong("id_petshop"));
						petshop.setNomePetshop(rs.getString("nome_petshop"));
						petshop.setCnpjPetshop(rs.getString("cnpj_petshop"));
						petshop.setEmailPetshop(rs.getString("email_petshop"));
						petshop.setSenhaPetshop(rs.getString("senha_petshop"));
						petshop.setTelefonePetshop(rs.getString("telefone_petshop"));
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					this.conexao.fecharConexao();
				}
				return cliente;
			}	
			
			//------------------------------BUSCAR POR LOGIN E SENHA------------------------------

			// SELECT * FROM cliente WHERE email=? AND senha=?;
			public Cliente buscarPorLoginESenha(String email, String senha) {
				// ABRIR A CONEXAO COM O BANCO
				this.conexao.abrirConexao();
				// SQL COM A OPERACAO QUE DESEJA-SE REALIZAR
				String sqlInsert = "SELECT * FROM cliente WHERE email=? AND senha=?;";
				PreparedStatement statement;
				Cliente cliente = null;
				try {
					statement = this.conexao.getConexao().prepareStatement(sqlInsert);
					statement.setString(1, email);
					statement.setString(2, senha);
					ResultSet rs = statement.executeQuery();
					if(rs.next()) {
						// Converter um objeto ResultSet em um objeto Cliente
						cliente = new Cliente();
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
				return cliente;
			}
	}