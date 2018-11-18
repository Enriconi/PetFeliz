package br.com.integrador.petshop.persistencia;

import java.sql.PreparedStatement;    
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.integrador.petshop.model.Animal;
import br.com.integrador.petshop.model.Cliente;
import br.com.integrador.petshop.model.Petshop;


public class AnimalDAO {
	
	private ConexaoMysql conexao;
	
	public AnimalDAO() {
		super();
		this.conexao = new ConexaoMysql("localhost", "root", "", "pet_feliz");
	}
	
	//-----------------------------CADASTRAR-------------------------------------------------------

	public Animal cadastrar(Animal animal) {
		
		// ABRIR A CONEXAO COM O BANCO		
		this.conexao.abrirConexao();
		// SQL COM A OPERACAO QUE DESEJA-SE REALIZAR
		String sqlInsert = "INSERT INTO animal VALUES(null, ?, ?, ?, ?, ?, ?, ?);";
		try {
			
			// DECLARA E INICIALIZA UM STATEMENT, OBJETO USADO PARA PREPARAR O SQL A SER EXECUTADO
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
			// SUBSTITUIR AS INTERROGACOES PELOS VALORES QUE ESTAO NO OBJETO ANIMAL
			statement.setString(1, animal.getNomeAnimal());
			statement.setString(2, animal.getRacaAnimal());
			statement.setInt(3, animal.getIdadeAnimal());
			statement.setString(4, animal.getDescricaoAnimal());
			statement.setString(5, animal.getSexoAnimal());
			statement.setLong(6, animal.getCliente().getIdCliente());
			statement.setLong(7, animal.getPetshop().getIdPetshop());

			// EXECUTAR A INSTRUCAO NO BANCO
			statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();
			if(rs.next()){
				//pega o id
				animal.setIdAnimal(rs.getLong(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// FECHAR A CONEXAO COM O BANCO
			this.conexao.fecharConexao();
		}
		return animal;
	}
	
	//--------------------------------EDITAR---------------------------------------
	
		// id_animal=1;
		public void editar(Animal animal) {
			// ABRIR A CONEXAO COM O BANCO
			this.conexao.abrirConexao();
			// SQL COM A OPERACAO QUE DESEJA-SE REALIZAR
			String sqlUpdate = "UPDATE animal SET nome_animal=?, raca_animal=?, idade_animal=?, descricao_animal=?, sexo_animal=?, id_cliente=?, id_petshop=? WHERE id_animal=?;";

			try {
				// DECLARA E INICIALIZA UM STATEMENT, OBJETO USADO PARA PREPARAR O SQL A SER EXECUTADO
				PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlUpdate);
				// SUBSTITUIR AS INTERROGACOES PELOS VALORES QUE ESTAO NO OBJETO ANIMAL
				statement.setString(1, animal.getNomeAnimal());
				statement.setString(2, animal.getRacaAnimal());
				statement.setInt(3, animal.getIdadeAnimal());
				statement.setString(4, animal.getDescricaoAnimal());
				statement.setString(5, animal.getSexoAnimal());
				statement.setLong(6, animal.getCliente().getIdCliente());
				statement.setLong(7, animal.getPetshop().getIdPetshop());
				statement.setLong(8, animal.getIdAnimal());

				statement.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				this.conexao.fecharConexao();
			}
		}

		//---------------------------------EXCLUIR-------------------------------------
		// DELETE FROM animal WHERE id_animal=3;
			public void excluir(long id) {
				// ABRIR A CONEXAO COM O BANCO
				this.conexao.abrirConexao();
				// SQL COM A OPERACAO QUE DESEJA-SE REALIZAR
				String sqlDelete = "DELETE FROM animal WHERE id_animal=?;";
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

			// SELECT * FROM animal;
			public List<Animal> buscarTodos() {
				// ABRIR A CONEXAO COM O BANCO
				this.conexao.abrirConexao();
				// SQL COM A OPERACAO QUE DESEJA-SE REALIZAR
				String sqlSelect = "SELECT * FROM animal a INNER JOIN cliente c ON a.id_cliente = c.id_cliente INNER JOIN petshop p ON a.id_petshop = p.id_petshop;";
				PreparedStatement statement;
				Animal animal = null;
				List<Animal> listaAnimais = new ArrayList<Animal>();
				try {
					statement = this.conexao.getConexao().prepareStatement(sqlSelect);
					ResultSet rs = statement.executeQuery();
					
					while(rs.next()) {
						// Converter um objeto ResultSet em um objeto Animal
						animal = new Animal();
						animal.setIdAnimal(rs.getLong("id_animal"));
						animal.setNomeAnimal(rs.getString("nome_animal"));
						animal.setRacaAnimal(rs.getString("raca_animal"));
						animal.setIdadeAnimal(rs.getInt("idade_animal"));
						animal.setDescricaoAnimal(rs.getString("descricao_animal"));
						animal.setSexoAnimal(rs.getString("sexo_animal"));
						
						Cliente cliente = new Cliente();
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
					
						animal.setCliente(cliente);
						animal.setPetshop(petshop);
						listaAnimais.add(animal);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					this.conexao.fecharConexao();
				}
				return listaAnimais;
			}
		
			//--------------------------------BUSCAR POR ID-----------------------------------------------		

			// SELECT * FROM animal WHERE id_animal=2;
			public Animal buscarPorId(long id) {
				// ABRIR A CONEXAO COM O BANCO
				this.conexao.abrirConexao();
				// SQL COM A OPERACAO QUE DESEJA-SE REALIZAR
				String sqlInsert = "SELECT * FROM animal a INNER JOIN cliente c ON a.id_cliente = c.id_cliente INNER JOIN petshop p ON a.id_petshop = p.id_petshop WHERE id_animal=?;";
				PreparedStatement statement;
				Animal animal = null;
				try {
					statement = this.conexao.getConexao().prepareStatement(sqlInsert);
					statement.setLong(1, id);
					ResultSet rs = statement.executeQuery();
					if(rs.next()) {
						// Converter um objeto ResultSet em um objeto Animal
						animal = new Animal();
						animal.setIdAnimal(rs.getLong("id_animal"));
						animal.setNomeAnimal(rs.getString("nome_animal"));
						animal.setRacaAnimal(rs.getString("raca_animal"));
						animal.setIdadeAnimal(rs.getInt("idade_animal"));
						animal.setDescricaoAnimal(rs.getString("descricao_animal"));
						animal.setSexoAnimal(rs.getString("sexo_animal"));
						
						Cliente cliente = new Cliente();
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
				return animal;
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