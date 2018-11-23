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

public class AnimalDAO implements ObrigatorioDAO {

	private ConexaoMysql conexao;

	public AnimalDAO() {
		super();
		this.conexao = new ConexaoMysql("localhost", "root", "", "pet_feliz");
	}
	
	// CADASTRAR
	public Object cadastrar(Object objeto) {

		Animal animal = (Animal) objeto;

		this.conexao.abrirConexao();
		String sqlInsert = "INSERT INTO animal VALUES(null, ?, ?, ?, ?, ?, ?, ?);";
		
		try {
			
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);

			statement.setString(1, animal.getNomeAnimal());
			statement.setString(2, animal.getRacaAnimal());
			statement.setInt(3, animal.getIdadeAnimal());
			statement.setString(4, animal.getDescricaoAnimal());
			statement.setString(5, animal.getSexoAnimal());
			statement.setLong(6, animal.getCliente().getIdCliente());
			statement.setLong(7, animal.getPetshop().getIdPetshop());

			statement.executeUpdate();
			
			ResultSet rs = statement.getGeneratedKeys();
			if (rs.next()) {
				animal.setIdAnimal(rs.getLong(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return animal;
	}
	
	// EDITAR
	public void editar(Object objeto) {

		Animal animal = (Animal) objeto;

		this.conexao.abrirConexao();
		String sqlUpdate = "UPDATE animal SET nome_animal=?, raca_animal=?, idade_animal=?, descricao_animal=?, sexo_animal=?, id_cliente=?, id_petshop=? WHERE id_animal=?;";

		try {
			
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlUpdate);

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

	// EXCLUIR
	public void excluir(long id) {
		
		this.conexao.abrirConexao();
		String sqlDelete = "DELETE FROM animal WHERE id_animal=?;";
		
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
		String sqlSelect = "SELECT * FROM animal a INNER JOIN cliente c ON a.id_cliente = c.id_cliente INNER JOIN petshop p ON a.id_petshop = p.id_petshop;";
		PreparedStatement statement;
		Animal animal = null;
		List<Object> listaAnimais = new ArrayList<Object>();
		
		try {
			statement = this.conexao.getConexao().prepareStatement(sqlSelect);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
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
		
	// BUSCAR POR ID	
	public Object buscarPorId(long id) {
		
		this.conexao.abrirConexao();
		String sqlInsert = "SELECT * FROM animal a INNER JOIN cliente c ON a.id_cliente = c.id_cliente INNER JOIN petshop p ON a.id_petshop = p.id_petshop WHERE id_animal=?;";
		PreparedStatement statement;
		Animal animal = null;
		
		try {
			statement = this.conexao.getConexao().prepareStatement(sqlInsert);
			statement.setLong(1, id);
			ResultSet rs = statement.executeQuery();
			
			if (rs.next()) {
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
}