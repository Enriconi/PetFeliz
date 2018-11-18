package br.com.integrador.petshop.persistencia;

import java.sql.PreparedStatement;    
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.integrador.petshop.model.Petshop;


public class PetshopDAO {
	
	private ConexaoMysql conexao;
	
	public PetshopDAO() {
		super();
		this.conexao = new ConexaoMysql("localhost", "root", "", "pet_feliz");
	}
	
	//-----------------------------CADASTRAR-------------------------------------------------------

	public Petshop cadastrar(Petshop petshop) {
		
		// ABRIR A CONEXAO COM O BANCO		
		this.conexao.abrirConexao();
		// SQL COM A OPERACAO QUE DESEJA-SE REALIZAR
		String sqlInsert = "INSERT INTO petshop VALUES(null, ?, ?, ?, ?, ?);";
		try {
			
			// DECLARA E INICIALIZA UM STATEMENT, OBJETO USADO PARA PREPARAR O SQL A SER EXECUTADO
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
			// SUBSTITUIR AS INTERROGACOES PELOS VALORES QUE ESTAO NO OBJETO PETSHOP
			statement.setString(1, petshop.getNomePetshop());
			statement.setString(2, petshop.getCnpjPetshop());
			statement.setString(3, petshop.getEmailPetshop());
			statement.setString(4, petshop.getSenhaPetshop());
			statement.setString(5, petshop.getTelefonePetshop());
			
			// EXECUTAR A INSTRUCAO NO BANCO
			statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();
			if(rs.next()){
				//pega o id
				petshop.setIdPetshop(rs.getLong(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// FECHAR A CONEXAO COM O BANCO
			this.conexao.fecharConexao();
		}
		return petshop;
	}
	
	//--------------------------------EDITAR---------------------------------------
	
		// id_petshop=1;
		public void editar(Petshop petshop) {
			// ABRIR A CONEXAO COM O BANCO
			this.conexao.abrirConexao();
			// SQL COM A OPERACAO QUE DESEJA-SE REALIZAR
			String sqlUpdate = "UPDATE petshop SET nome_petshop=?, cnpj_petshop=?, email_petshop=?, senha_petshop=?, telefone_petshop=? WHERE id_petshop=?;";
			try {
				// DECLARA E INICIALIZA UM STATEMENT, OBJETO USADO PARA PREPARAR O SQL A SER EXECUTADO
				PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlUpdate);
				// SUBSTITUIR AS INTERROGACOES PELOS VALORES QUE ESTAO NO OBJETO PETSHOP
				statement.setString(1, petshop.getNomePetshop());
				statement.setString(2, petshop.getCnpjPetshop());
				statement.setString(3, petshop.getEmailPetshop());
				statement.setString(4, petshop.getSenhaPetshop());
				statement.setString(5, petshop.getTelefonePetshop());
				statement.setLong(6, petshop.getIdPetshop());

				statement.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				this.conexao.fecharConexao();
			}
		}

		//---------------------------------EXCLUIR-------------------------------------
		// DELETE FROM petshop WHERE id_petshop=3;
			public void excluir(long id) {
				// ABRIR A CONEXAO COM O BANCO
				this.conexao.abrirConexao();
				// SQL COM A OPERACAO QUE DESEJA-SE REALIZAR
				String sqlDelete = "DELETE FROM petshop WHERE id_petshop=?;";
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

			// SELECT * FROM petshop;
			public List<Petshop> buscarTodos() {
				// ABRIR A CONEXAO COM O BANCO
				this.conexao.abrirConexao();
				// SQL COM A OPERACAO QUE DESEJA-SE REALIZAR
				String sqlSelect = "SELECT * FROM petshop;";
				PreparedStatement statement;
				Petshop petshop = null;
				List<Petshop> listaPetshops = new ArrayList<Petshop>();
				try {
					statement = this.conexao.getConexao().prepareStatement(sqlSelect);
					ResultSet rs = statement.executeQuery();
					
					while(rs.next()) {
						// Converter um objeto ResultSet em um objeto Petshop
						petshop = new Petshop();
						petshop.setIdPetshop(rs.getLong("id_petshop"));
						petshop.setNomePetshop(rs.getString("nome_petshop"));
						petshop.setCnpjPetshop(rs.getString("cnpj_petshop"));
						petshop.setEmailPetshop(rs.getString("email_petshop"));
						petshop.setSenhaPetshop(rs.getString("senha_petshop"));
						petshop.setTelefonePetshop(rs.getString("telefone_petshop"));
						listaPetshops.add(petshop);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					this.conexao.fecharConexao();
				}
				return listaPetshops;
			}
		
			//--------------------------------BUSCAR POR ID-----------------------------------------------		

			// SELECT * FROM petshop WHERE id_petshop=2;
			public Petshop buscarPorId(long id) {
				// ABRIR A CONEXAO COM O BANCO
				this.conexao.abrirConexao();
				// SQL COM A OPERACAO QUE DESEJA-SE REALIZAR
				String sqlInsert = "SELECT * FROM petshop WHERE id_petshop=?;";
				PreparedStatement statement;
				Petshop petshop = null;
				try {
					statement = this.conexao.getConexao().prepareStatement(sqlInsert);
					statement.setLong(1, id);
					ResultSet rs = statement.executeQuery();
					if(rs.next()) {
						// Converter um objeto ResultSet em um objeto Petshop
						petshop = new Petshop();
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
				return petshop;
			}	
			
			//------------------------------BUSCAR POR LOGIN E SENHA------------------------------

			// SELECT * FROM petshop WHERE email=? AND senha=?;
			public Petshop buscarPorLoginESenha(String email, String senha) {
				// ABRIR A CONEXAO COM O BANCO
				this.conexao.abrirConexao();
				// SQL COM A OPERACAO QUE DESEJA-SE REALIZAR
				String sqlInsert = "SELECT * FROM petshop WHERE email=? AND senha=?;";
				PreparedStatement statement;
				Petshop petshop = null;
				try {
					statement = this.conexao.getConexao().prepareStatement(sqlInsert);
					statement.setString(1, email);
					statement.setString(2, senha);
					ResultSet rs = statement.executeQuery();
					if(rs.next()) {
						// Converter um objeto ResultSet em um objeto Petshop
						petshop = new Petshop();
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
				return petshop;
			}
	}