package br.com.integrador.petshop.persistencia;

import java.util.List;

public interface ObrigatorioDAO {

	Object cadastrar(Object objeto);
	void editar(Object objeto);
	void excluir(long id);
	Object buscarPorId(long id);
	List<Object> buscarTodos();
}
