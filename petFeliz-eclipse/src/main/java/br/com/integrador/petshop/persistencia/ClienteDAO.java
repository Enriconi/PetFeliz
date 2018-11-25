package br.com.integrador.petshop.persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.integrador.petshop.model.Cliente;
import br.com.integrador.petshop.model.Petshop;

public class ClienteDAO implements ObrigatorioDAO {

	private ConexaoMysql conexao;

	public ClienteDAO() {
		super();
		this.conexao = new ConexaoMysql("localhost", "root", "", "pet_feliz");
	}

	// CADASTRAR
	public Object cadastrar(Object objeto) {
		
		Cliente cliente = (Cliente) objeto;

		this.conexao.abrirConexao();
		String sqlInsert = "INSERT INTO cliente VALUES(null, ?, ?, ?, ?, ?, ?);";
		
		try {
			
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
			
			statement.setString(1, cliente.getNomeCliente());
			statement.setString(2, cliente.getCpfCliente());
			statement.setString(3, cliente.getEmailCliente());
			statement.setString(4, cliente.getSenhaCliente());
			statement.setString(5, cliente.getTelefoneCliente());
			statement.setLong(6, cliente.getPetshop().getIdPetshop());
			statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();
			
			if (rs.next()) {
				cliente.setIdCliente(rs.getLong(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return cliente;
	}

	// EDITAR
	public void editar(Object objeto) {
		
		Cliente cliente = (Cliente) objeto;
		
		this.conexao.abrirConexao();
		String sqlUpdate = "UPDATE cliente SET nome_cliente=?, cpf_cliente=?, email_cliente=?, senha_cliente=?, telefone_cliente=?, id_petshop=? WHERE id_cliente=?;";
		
		try {
			
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlUpdate);
		
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

	// EXCLUIR
	public void excluir(long id) {
		
		this.conexao.abrirConexao();
		String sqlDelete = "DELETE FROM cliente WHERE id_cliente=?;";
	
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
		String sqlSelect = "SELECT * FROM cliente c INNER JOIN petshop p ON c.id_petshop = p.id_petshop;";
		PreparedStatement statement;
		Cliente cliente = null;
		List<Object> listaClientes = new ArrayList<Object>();
		
		try {
			
			statement = this.conexao.getConexao().prepareStatement(sqlSelect);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
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

	// BUSCAR POR ID
	public Object buscarPorId(long id) {
		
		this.conexao.abrirConexao();
		String sqlInsert = "SELECT * FROM cliente c INNER JOIN petshop p ON c.id_petshop = p.id_petshop WHERE id_cliente=?;";
		PreparedStatement statement;
		Cliente cliente = null;
		
		try {
			
			statement = this.conexao.getConexao().prepareStatement(sqlInsert);
			statement.setLong(1, id);
			ResultSet rs = statement.executeQuery();
			
			if (rs.next()) {
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

	// BUSCAR POR LOGIN E SENHA
	public Cliente buscarPorLoginESenha(String email, String senha) {
		
		this.conexao.abrirConexao();
		String sqlInsert = "SELECT * FROM cliente WHERE email=? AND senha=?;";
		PreparedStatement statement;
		Cliente cliente = null;
		
		try {
			
			statement = this.conexao.getConexao().prepareStatement(sqlInsert);
			statement.setString(1, email);
			statement.setString(2, senha);
			ResultSet rs = statement.executeQuery();
			
			if (rs.next()) {
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