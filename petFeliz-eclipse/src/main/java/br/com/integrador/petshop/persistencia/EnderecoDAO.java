package br.com.integrador.petshop.persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.integrador.petshop.model.Cliente;
import br.com.integrador.petshop.model.Endereco;

public class EnderecoDAO implements ObrigatorioDAO {

	private ConexaoMysql conexao;

	public EnderecoDAO() {
		super();
		this.conexao = new ConexaoMysql("localhost", "root", "", "pet_feliz");
	}

	// CADASTRAR
	public Object cadastrar(Object objeto) {
		
		Endereco endereco = (Endereco) objeto;
		
		this.conexao.abrirConexao();
		String sqlInsert = "INSERT INTO endereco VALUES(null, ?, ?, ?, ?, ?, ?, ?);";
		
		try {
			
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);

			statement.setString(1, endereco.getCep());
			statement.setString(2, endereco.getEstado());
			statement.setString(3, endereco.getCidade());
			statement.setString(4, endereco.getBairro());
			statement.setString(5, endereco.getRua());
			statement.setInt(6, endereco.getNumero());
			statement.setLong(7, endereco.getCliente().getIdCliente());
			statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();
			
			if (rs.next()) {
				endereco.setIdEndereco(rs.getLong(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return endereco;
	}
	
	// EDITAR
	public void editar(Object objeto) {

		Endereco endereco = (Endereco) objeto;
		
		this.conexao.abrirConexao();
		String sqlUpdate = "UPDATE endereco SET cep=?, estado=?, cidade=?, bairro=?, rua=?, numero=?, id_cliente=? WHERE id_endereco=?;";

		try {
			
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlUpdate);

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

	// EXCLUIR
	public void excluir(long id) {
		
		this.conexao.abrirConexao();
		String sqlDelete = "DELETE FROM endereco WHERE id_endereco=?;";
		
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
		String sqlSelect = "SELECT * FROM endereco e INNER JOIN cliente c ON e.id_cliente = c.id_cliente;";
		PreparedStatement statement;
		Endereco endereco = null;
		List<Object> listaEnderecos = new ArrayList<Object>();
		
		try {
			
			statement = this.conexao.getConexao().prepareStatement(sqlSelect);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
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

	// BUSCAR POR ID
	public Object buscarPorId(long id) {
		
		this.conexao.abrirConexao();
		String sqlInsert = "SELECT * FROM endereco e INNER JOIN cliente c ON e.id_cliente = c.id_cliente WHERE id_endereco=?;";
		PreparedStatement statement;
		Endereco endereco = null;
		
		try {
			
			statement = this.conexao.getConexao().prepareStatement(sqlInsert);
			statement.setLong(1, id);
			ResultSet rs = statement.executeQuery();
			
			if (rs.next()) {
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
}