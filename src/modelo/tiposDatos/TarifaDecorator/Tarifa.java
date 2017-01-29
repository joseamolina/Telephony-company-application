package modelo.tiposDatos.TarifaDecorator;

import java.io.Serializable;

import modelo.Llamada;


public abstract class Tarifa implements Serializable{

    private static final long serialVersionUID = 1L;
    public String descripcion;
    public boolean b1;
    public boolean b2;
    public boolean b3;
    private double ratio;
    
    public Tarifa(double ratio){
	this.ratio=ratio;
    }
    
    public abstract double getPrecio(Llamada llam);
    
    public abstract String getTarifaDescrip();
    
    public double getTar(){
	return ratio;
    }
    
    public void setTar(double ratio){
	this.ratio=ratio;
    }
    
    public abstract boolean getB1();
    
    public abstract boolean getB2();
    
    public abstract boolean getB3();
}
