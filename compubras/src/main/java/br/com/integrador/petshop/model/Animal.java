package br.com.integrador.petshop.model;

public class Animal {
	
	private long idAnimal;
	private String nomeAnimal;
	private String racaAnimal;
	private int idadeAnimal;
	private String descricaoAnimal;
	private String sexoAnimal;
	private Cliente cliente;
	private Petshop petshop;
	
	public Animal() {
		
	}

	public Animal(long idAnimal, String nomeAnimal, String racaAnimal, int idadeAnimal, String descricaoAnimal,
			String sexoAnimal, Cliente cliente, Petshop petshop) {
		super();
		this.idAnimal = idAnimal;
		this.nomeAnimal = nomeAnimal;
		this.racaAnimal = racaAnimal;
		this.idadeAnimal = idadeAnimal;
		this.descricaoAnimal = descricaoAnimal;
		this.sexoAnimal = sexoAnimal;
		this.cliente = cliente;
		this.petshop = petshop;
	}

	public long getIdAnimal() {
		return idAnimal;
	}

	public void setIdAnimal(long idAnimal) {
		this.idAnimal = idAnimal;
	}

	public String getNomeAnimal() {
		return nomeAnimal;
	}

	public void setNomeAnimal(String nomeAnimal) {
		this.nomeAnimal = nomeAnimal;
	}

	public String getRacaAnimal() {
		return racaAnimal;
	}

	public void setRacaAnimal(String racaAnimal) {
		this.racaAnimal = racaAnimal;
	}

	public int getIdadeAnimal() {
		return idadeAnimal;
	}

	public void setIdadeAnimal(int idadeAnimal) {
		this.idadeAnimal = idadeAnimal;
	}

	public String getDescricaoAnimal() {
		return descricaoAnimal;
	}

	public void setDescricaoAnimal(String descricaoAnimal) {
		this.descricaoAnimal = descricaoAnimal;
	}

	public String getSexoAnimal() {
		return sexoAnimal;
	}

	public void setSexoAnimal(String sexoAnimal) {
		this.sexoAnimal = sexoAnimal;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Petshop getPetshop() {
		return petshop;
	}

	public void setPetshop(Petshop petshop) {
		this.petshop = petshop;
	}
}