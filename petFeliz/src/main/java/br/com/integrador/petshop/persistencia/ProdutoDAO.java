package br.com.integrador.petshop.persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.integrador.petshop.model.Petshop;
import br.com.integrador.petshop.model.Produto;

public class ProdutoDAO implements ObrigatorioDAO {

	private ConexaoMysql conexao;

	public ProdutoDAO() {
		super();
		this.conexao = new ConexaoMysql("localhost", "root", "", "pet_feliz");
	}

	// CADASTRAR
	public Object cadastrar(Object objeto) {
		
		Produto produto = (Produto) objeto;

		this.conexao.abrirConexao();
		String sqlInsert = "INSERT INTO produto VALUES(null, ?, ?, ?, ?, ?);";
		
		try {

			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
			
			statement.setString(1, produto.getNomeProduto());
			statement.setString(2, produto.getDescricaoProduto());
			statement.setDouble(3, produto.getPrecoProduto());
			statement.setInt(4, produto.getEstoqueProduto());
			statement.setLong(5, produto.getPetshop().getIdPetshop());
			statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();
			
			if (rs.next()) {
				produto.setIdProduto(rs.getLong(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return produto;
	}

	// EDITAR
	public void editar(Object objeto) {
		
		Produto produto = (Produto) objeto;
		
		this.conexao.abrirConexao();
		String sqlUpdate = "UPDATE produto SET nome_produto=?, descricao_produto=?, preco_produto=?, estoque_produto=?, id_petshop=? WHERE id_produto=?;";

		try {

			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlUpdate);

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

	// EXCLUIR
	public void excluir(long id) {
		
		this.conexao.abrirConexao();
		String sqlDelete = "DELETE FROM produto WHERE id_produto=?;";

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
		String sqlSelect = "SELECT * FROM produto prod INNER JOIN petshop p ON prod.id_petshop = p.id_petshop;";
		PreparedStatement statement;
		Produto produto = null;
		List<Object> listaProdutos = new ArrayList<Object>();
		
		try {
			
			statement = this.conexao.getConexao().prepareStatement(sqlSelect);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
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

	// BUSCAR POR ID
	public Object buscarPorId(long id) {
		
		this.conexao.abrirConexao();
		String sqlInsert = "SELECT * FROM produto prod INNER JOIN petshop p ON prod.id_petshop = p.id_petshop WHERE id_produto=?;";
		PreparedStatement statement;
		Produto produto = null;
		
		try {
			
			statement = this.conexao.getConexao().prepareStatement(sqlInsert);
			statement.setLong(1, id);
			ResultSet rs = statement.executeQuery();
			
			if (rs.next()) {
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
}