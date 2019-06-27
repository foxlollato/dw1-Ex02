package dw1s5.modelo.entidades;

public class Usuario {
	
	private String nome;
	private String email;
	private String senha;
	private String perfil;
	
	public Usuario(String nome, String email, String senha, String perfil) {
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.perfil = perfil;
	}
	
	public String getNome(){
		return nome;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public String getPerfil() {
		return perfil;
	}
	
}
