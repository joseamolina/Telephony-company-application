package modelo.tiposDatos.Clientes;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import modelo.Direc;
import modelo.tiposDatos.TarifaDecorator.Tarifa;


public class ClientsParticular extends ClientsEmpresa implements Serializable {
    private static final long serialVersionUID = 3186380718281913284L;
    private String apellidos;

    public ClientsParticular(String nombre, String NIF, Direc direccion,
	    String email, Date fecha, Tarifa tarifa, String apellidos) {
	super(nombre,NIF,direccion, email,fecha,tarifa);
	this.apellidos = apellidos;
    }
    
    public ClientsParticular(){
	super();
	this.apellidos = "";
    }
    
    @Override
    public String toString() {
	Calendar calendar = GregorianCalendar.getInstance();
	calendar.setTime(getFecha());
	int dia = calendar.get(Calendar.DAY_OF_MONTH);
	int mes = calendar.get(Calendar.MONTH);
	int año = calendar.get(Calendar.YEAR);
	return "OWNER--> ID: " + getNIF() + ";\n Name: " + getNombre()
		+ ";\n Surname: " + apellidos + ";\n address: "
		+ getDirecion().toString() + ";\n email: " + getEmail()
		+ ";\n Register date: " + dia + "/"
		+ mes + "/" + (año+1900)
		+ ";\n Added rate: " +getTarifa().getTarifaDescrip();
    }

}
