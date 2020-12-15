package br.fortsdev.imobilizado.models;

public class Resposta {
	String msgErro;
	boolean erro;
	
	public String getMsgErro() {
		return msgErro;
	}
	public void setMsgErro(String msgErro) {
		this.msgErro = msgErro;
	}
	public boolean isErro() {
		return erro;
	}
	public void setErro(boolean erro) {
		this.erro = erro;
	}
}
