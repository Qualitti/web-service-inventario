package br.fortsdev.imobilizado.models;

public class ItemInventario {
    int codInventario;
    int codBem;
    String codRFID;
    int codDep;
    String descrDep;
    String descrBem;
    boolean encontrado;


    public String getDescrDep() {
        return descrDep;
    }

    public void setDescrDep(String descrDep) {
        this.descrDep = descrDep;
    }

    public String getDescrBem() {
        return descrBem;
    }

    public void setDescrBem(String descrBem) {
        this.descrBem = descrBem;
    }

    public int getCodDep() {
        return codDep;
    }

    public void setCodDep(int codDep) {
        this.codDep = codDep;
    }

    public boolean isEncontrado() {
        return encontrado;
    }

    public void setEncontrado(boolean encontrado) {
        this.encontrado = encontrado;
    }

    public int getCodInventario() {
        return codInventario;
    }

    public void setCodInventario(int codInventario) {
        this.codInventario = codInventario;
    }

    public int getCodBem() {
        return codBem;
    }

    public void setCodBem(int codBem) {
        this.codBem = codBem;
    }

    public String getCodRFID() {
        return codRFID;
    }

    public void setCodRFID(String codRFID) {
        this.codRFID = codRFID;
    }
}
