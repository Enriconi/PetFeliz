package br.com.integrador.petshop.model;

public class Petshop {
	
	private long idPetshop;
	private String nomePetshop;
	private String cnpjPetshop;
	private String emailPetshop;
	private String senhaPetshop;
	private String telefonePetshop;
	
	public Petshop() {
		
	}

	public Petshop(long idPetshop, String nomePetshop, String cnpjPetshop, String emailPetshop, String senhaPetshop,
			String telefonePetshop) {
		super();
		this.idPetshop = idPetshop;
		this.nomePetshop = nomePetshop;
		this.cnpjPetshop = cnpjPetshop;
		this.emailPetshop = emailPetshop;
		this.senhaPetshop = senhaPetshop;
		this.telefonePetshop = telefonePetshop;
	}

	public long getIdPetshop() {
		return idPetshop;
	}

	public void setIdPetshop(long idPetshop) {
		this.idPetshop = idPetshop;
	}

	public String getNomePetshop() {
		return nomePetshop;
	}

	public void setNomePetshop(String nomePetshop) {
		this.nomePetshop = nomePetshop;
	}

	public String getCnpjPetshop() {
		return cnpjPetshop;
	}

	public void setCnpjPetshop(String cnpjPetshop) {
		this.cnpjPetshop = cnpjPetshop;
	}

	public String getEmailPetshop() {
		return emailPetshop;
	}

	public void setEmailPetshop(String emailPetshop) {
		this.emailPetshop = emailPetshop;
	}

	public String getSenhaPetshop() {
		return senhaPetshop;
	}

	public void setSenhaPetshop(String senhaPetshop) {
		this.senhaPetshop = senhaPetshop;
	}

	public String getTelefonePetshop() {
		return telefonePetshop;
	}

	public void setTelefonePetshop(String telefonePetshop) {
		this.telefonePetshop = telefonePetshop;
	}
}