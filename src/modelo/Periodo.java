package modelo;

import java.io.Serializable;
import java.util.Date;

public final class Periodo implements Serializable {
    private static final long serialVersionUID = 1L;
    private Date inicial;
    private Date alfinal;

    public Periodo() {
	this.inicial = new Date();
	this.alfinal = new Date();
    }

    public void setInicial(Date inicial) {
	this.inicial = inicial;
    }
    public Date getInicial(){
	return inicial;
    }

    public Date getAlfinal() {
	return alfinal;
    }

    public void setAlfinal(Date alfinal) {
	this.alfinal = alfinal;
    }

}
