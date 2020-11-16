package br.fortsdev.imobilizado.models;

public class Bem {
    private int codBem;
    private int codProd;
    private String descrProd;
    private int codRFID;
    private int codDep;
    private String descrDep;
    private String dataAquisicao;

    public int getCodBem() {
        return codBem;
    }

    public void setCodBem(int codBem) {
        this.codBem = codBem;
    }

    public int getCodProd() {
        return codProd;
    }

    public void setCodProd(int codProd) {
        this.codProd = codProd;
    }

    public String getDescrProd() {
        return descrProd;
    }

    public void setDescrProd(String descrProd) {
        this.descrProd = descrProd;
    }

    public int getCodRFID() {
        return codRFID;
    }

    public void setCodRFID(int codRFID) {
        this.codRFID = codRFID;
    }

    public int getCodDep() {
        return codDep;
    }

    public void setCodDep(int codDep) {
        this.codDep = codDep;
    }

    public String getDescrDep() {
        return descrDep;
    }

    public void setDescrDep(String descrDep) {
        this.descrDep = descrDep;
    }

    public String getDataAquisicao() {
        return dataAquisicao;
    }

    public void setDataAquisicao(String dataAquisicao) {
        this.dataAquisicao = dataAquisicao;
    }
}
