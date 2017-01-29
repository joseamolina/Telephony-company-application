package modelo.tiposDatos.Clientes;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import modelo.CliLlamFac;
import modelo.Direc;
import modelo.tiposDatos.TarifaDecorator.Tarifa;


public class ClientsEmpresa implements CliLlamFac,Serializable {

    private static final long serialVersionUID = 1627951237102975425L;
    private String nombre;
    private String NIF;
    private Direc direccion;
    private String email;
    private Date fecha;
    private Tarifa tarifa;

    public ClientsEmpresa(String nombre, String NIF, Direc direccion,
	    String email, Date fecha, Tarifa tarifa) {
	this.nombre = nombre;
	this.NIF = NIF;
	this.direccion = direccion;
	this.email = email;
	this.fecha = fecha;
	this.tarifa = tarifa;
    }
    
    public ClientsEmpresa(){
	this.nombre = "";
	this.NIF = "";
	this.direccion = new Direc();
	this.email = "";
	this.fecha = new Date();
	this.tarifa = null;
    }

    public String getNIF() {
	return NIF;
    }

    
    public Date getFecha() {
	return fecha;
    }

    public Tarifa getTarifa() {
	return tarifa;
    }

    public void setTarifa(Tarifa tarifa) {
	this.tarifa = tarifa;
    }

    @Override
    public String toString() {
	Calendar calendar = GregorianCalendar.getInstance();
	calendar.setTime(fecha);
	int dia = calendar.get(Calendar.DAY_OF_MONTH);
	int mes = calendar.get(Calendar.MONTH);
	int año = calendar.get(Calendar.YEAR);
	return "ENTERPRICE--> \nID: " + NIF + ";\n Name: " + nombre + ";\n address: "
		+ direccion.toString() + ";\n email: " + email
		+ ";\n Register date: " + dia + "/"
		+ mes + "/" + (año+1900)
		+ ";\n Added rate: " + tarifa.getTarifaDescrip();
    }

    
    public String getNombre() {
	return nombre;
    }

    
    public String getEmail() {
	return email;
    }

    
    public Direc getDirecion() {
	return direccion;
    }
}
