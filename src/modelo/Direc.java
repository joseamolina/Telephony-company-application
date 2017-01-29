package modelo;

import java.io.Serializable;

public final class Direc implements Serializable {
    private static final long serialVersionUID = 6251401924858024697L;
    private int CP;
    private String provincia;
    private String poblacion;

    public Direc(int CP, String provincia, String poblacion) {
	this.CP = CP;
	this.poblacion = poblacion;
	this.provincia = provincia;
    }
    
    public Direc() {
	this.CP = 0;
	this.poblacion = "";
	this.provincia = "";
    }

    @Override
    public String toString() {
	return "PC:" + CP + ", town: " + poblacion
		+ ", province: " + provincia + ".";
    }

    public String getProvincia() {
	return provincia;
    }

    public void setProvincia(String provincia) {
	this.provincia = provincia;
    }

    public String getPoblacion() {
	return poblacion;
    }

    public void setPoblacion(String poblacion) {
	this.poblacion = poblacion;
    }

}
