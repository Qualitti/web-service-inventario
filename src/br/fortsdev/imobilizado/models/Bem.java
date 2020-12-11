package br.fortsdev.imobilizado.models;

public class Bem {
    private String codBem;
    private int codProd;
    private String descrProd;
    private String descrBem;
    private String codRFID;
    private int codDep;
    private String descrDep;
    private String dataAquisicao;
    private int codEmp;
    private int estadoDeConservacao;
    
    public int getEstadoDeConservacao() {
		return estadoDeConservacao;
	}

	public void setEstadoDeConservacao(int estadoDeConservacao) {
		this.estadoDeConservacao = estadoDeConservacao;
	}

	public int getCodEmp() {
		return codEmp;
	}

	public void setCodEmp(int codEmp) {
		this.codEmp = codEmp;
	}

	public String getDescrBem() {
		return descrBem;
	}

	public void setDescrBem(String descrBem) {
		this.descrBem = descrBem;
	}

	public String getCodBem() {
        return codBem;
    }

    public void setCodBem(String codBem) {
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

    public String getCodRFID() {
        return codRFID;
    }

    public void setCodRFID(String codRFID) {
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

	@Override
	public String toString() {
		return "Bem [codBem=" + codBem + ", codProd=" + codProd + ", descrProd=" + descrProd + ", descrBem=" + descrBem
				+ ", codRFID=" + codRFID + ", codDep=" + codDep + ", descrDep=" + descrDep + ", dataAquisicao="
				+ dataAquisicao + ", codEmp=" + codEmp + ", estadoDeConservacao=" + estadoDeConservacao + "]";
	}
    
    
}
