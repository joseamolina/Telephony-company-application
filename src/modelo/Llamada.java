package modelo;

import java.util.Date;
import java.io.Serializable;
import java.sql.Time;


public final class Llamada implements CliLlamFac, Serializable {

    private static final long serialVersionUID = 7102818794374950591L;
    private int nTelf;
    private Date fecLlam;
    private Time HorLlam;
    private int duracion;

    public Llamada(int nTelf, Date fecLlam, Time horLlam, int duración) {
	this.nTelf = nTelf;
	this.fecLlam = fecLlam;
	HorLlam = horLlam;
	this.duracion = duración;
    }
    
    public Time getHorLlam(){
	return HorLlam;
    }
    
    @SuppressWarnings("deprecation")
    public Llamada(){
	this.nTelf = 0;
	this.fecLlam = new Date(2015-1900,4,17);
	HorLlam = new Time(19,19,19);
	this.duracion = 60;
    }

    public int getDuración() {
	return duracion;
    }

    @SuppressWarnings("deprecation")
    @Override
    public String toString() {
	return "	✔ Phone call to: " + nTelf + ", " + fecLlam.getDate()
		+ "/" + fecLlam.getMonth() + "/" + (fecLlam.getYear()+1900)
		+ ", at " + HorLlam.toString() + ", whose duration: "
		+ duracion + " seconds.\n";
    }

    @Override
    public Date getFecha() {
	return fecLlam;

    }

}
