package br.fortsdev.imobilizado.models;


public class CabecalhoInventario {
    private int codInventario;
    private int codEmp;
    private int codDep;
    private int codUsu;
    private String dataIni;
    private String dataFim;

    public int getCodInventario() {
        return codInventario;
    }

    public void setCodInventario(int codInventario) {
        this.codInventario = codInventario;
    }

    public int getCodEmp() {
        return codEmp;
    }

    public void setCodEmp(int codEmp) {
        this.codEmp = codEmp;
    }

    public int getCodDep() {
        return codDep;
    }

    public void setCodDep(int codDep) {
        this.codDep = codDep;
    }

    public int getCodUsu() {
        return codUsu;
    }

    public void setCodUsu(int codUsu) {
        this.codUsu = codUsu;
    }

    public String getDataIni() {
        return dataIni;
    }

    public void setDataIni(String dataIni) {
        this.dataIni = dataIni;
    }

    public String getDataFim() {
        return dataFim;
    }

    public void setDataFim(String dataFim) {
        this.dataFim = dataFim;
    }
}
