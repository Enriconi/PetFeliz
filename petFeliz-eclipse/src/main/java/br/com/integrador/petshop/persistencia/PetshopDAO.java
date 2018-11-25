package br.com.integrador.petshop.persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.integrador.petshop.model.Petshop;

public class PetshopDAO implements ObrigatorioDAO {

	private ConexaoMysql conexao;

	public PetshopDAO() {
		super();
		this.conexao = new ConexaoMysql("localhost", "root", "", "pet_feliz");
	}

	// CADASTRAR
	public Object cadastrar(Object objeto) {
		
		Petshop petshop = (Petshop) objeto;

		this.conexao.abrirConexao();
		String sqlInsert = "INSERT INTO petshop VALUES(null, ?, ?, ?, ?, ?);";
		try {

			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
			
			statement.setString(1, petshop.getNomePetshop());
			statement.setString(2, petshop.getCnpjPetshop());
			statement.setString(3, petshop.getEmailPetshop());
			statement.setString(4, petshop.getSenhaPetshop());
			statement.setString(5, petshop.getTelefonePetshop());

			statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();
			
			if (rs.next()) {
				petshop.setIdPetshop(rs.getLong(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return petshop;
	}

	// EDITAR
	public void editar(Object objeto) {

		Petshop petshop = (Petshop) objeto;
		
		this.conexao.abrirConexao();
		String sqlUpdate = "UPDATE petshop SET nome_petshop=?, cnpj_petshop=?, email_petshop=?, senha_petshop=?, telefone_petshop=? WHERE id_petshop=?;";
		
		try {
			
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlUpdate);

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

	// EXCLUIR
	public void excluir(long id) {
		
		this.conexao.abrirConexao();
		String sqlDelete = "DELETE FROM petshop WHERE id_petshop=?;";
		
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
		String sqlSelect = "SELECT * FROM petshop;";
		PreparedStatement statement;
		Petshop petshop = null;
		List<Object> listaPetshops = new ArrayList<Object>();
		
		try {
			
			statement = this.conexao.getConexao().prepareStatement(sqlSelect);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
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

	//BUSCAR POR ID
	public Object buscarPorId(long id) {
		
		this.conexao.abrirConexao();
		String sqlInsert = "SELECT * FROM petshop WHERE id_petshop=?;";
		PreparedStatement statement;
		Petshop petshop = null;
		
		try {
			
			statement = this.conexao.getConexao().prepareStatement(sqlInsert);
			statement.setLong(1, id);
			ResultSet rs = statement.executeQuery();
			
			if (rs.next()) {
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

	// BUSCAR POR ID
	public Petshop buscarPorLoginESenha(String email, String senha) {
		
		this.conexao.abrirConexao();
		String sqlInsert = "SELECT * FROM petshop WHERE email=? AND senha=?;";
		PreparedStatement statement;
		Petshop petshop = null;
		
		try {
			
			statement = this.conexao.getConexao().prepareStatement(sqlInsert);
			statement.setString(1, email);
			statement.setString(2, senha);
			ResultSet rs = statement.executeQuery();
			
			if (rs.next()) {
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