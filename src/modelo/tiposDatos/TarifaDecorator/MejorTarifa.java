package modelo.tiposDatos.TarifaDecorator;

import java.io.Serializable;

import modelo.Llamada;

public abstract class MejorTarifa extends Tarifa implements Serializable  {
    private static final long serialVersionUID = -8465338506681001483L;
    Tarifa tar;
    
    public MejorTarifa(Tarifa tar){
	super(tar.getTar());
	this.tar=tar;
    }
    
    public Tarifa getTarifa(){
	return tar;
    }

    public abstract double getPrecio(Llamada ll);
    
    public void setTar(double ratio){
	super.setTar(ratio);
    }
    
    public double getTar(){
	return super.getTar();
    }
    
    public abstract String getTarifaDescrip();
}
