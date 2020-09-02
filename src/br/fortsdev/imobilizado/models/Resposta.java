package br.fortsdev.imobilizado.models;

public class Resposta {
  String msgErro;
  Object corpoResposta;
  
  public String getMsgErro() {
	return msgErro;
  }
  public void setMsgErro(String msgErro) {
	this.msgErro = msgErro;
  }
  public Object getCorpoResposta() {
	return corpoResposta;
  }
  public void setCorpoResposta(Object corpoResposta) {
	this.corpoResposta = corpoResposta;
  }
}
