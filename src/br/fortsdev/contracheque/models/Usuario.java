package br.fortsdev.contracheque.models;

public class Usuario {
    int codUsu;
    int tipoUsu;
	int codVend;
	String login;
	String senha;
	String ativo;
	double saldoFlex;
	double descMax;
	double acrescMax;
	double provisao;
	String imei;
	String nome;
	String email;
	String telefone;
	
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getAtivo() {
		return ativo;
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public int getTipoUsu() {
		return tipoUsu;
	}
	public void setTipoUsu(int tipoUsu) {
		this.tipoUsu = tipoUsu;
	}
	public double getAcrescMax() {
		return acrescMax;
	}
	public void setAcrescMax(double acrescMax) {
		this.acrescMax = acrescMax;
	}
	public int getCodUsu() {
		return codUsu;
	}
	public void setCodUsu(int codUsu) {
		this.codUsu = codUsu;
	}
	public int getCodVend() {
		return codVend;
	}
	public void setCodVend(int codVend) {
		this.codVend = codVend;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String isAtivo() {
		return ativo;
	}
	public void setAtivo(String ativo) {
		this.ativo = ativo;
	}
	public double getSaldoFlex() {
		return saldoFlex;
	}
	public void setSaldoFlex(double saldoFlex) {
		this.saldoFlex = saldoFlex;
	}
	public double getDescMax() {
		return descMax;
	}
	public void setDescMax(double descMax) {
		this.descMax = descMax;
	}
	public double getAcresMax() {
		return acrescMax;
	}
	public void setAcresMax(double acresMax) {
		this.acrescMax = acresMax;
	}
	public double getProvisao() {
		return provisao;
	}
	public void setProvisao(double provisao) {
		this.provisao = provisao;
	}
}
