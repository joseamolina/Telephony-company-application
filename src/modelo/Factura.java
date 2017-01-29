package modelo;

import java.io.Serializable;
import java.util.Date;

import modelo.tiposDatos.TarifaDecorator.Tarifa;


public final class Factura implements CliLlamFac, Serializable {

    private static final long serialVersionUID = 6587431018881834213L;
    private Tarifa tarifa;
    private int codigo;
    private Date fecha;
    private Periodo periodo;
    private double importe;
    private String codCli;

    public Factura(int codigo, Date fecha, Periodo periodo, String codCli) {
	this.codigo = codigo;
	this.fecha = fecha;
	this.periodo = periodo;
	this.importe = 0.0;
	this.codCli = codCli;
    }
    
    public Factura() {
	this.tarifa = null;
	this.codigo = 0;
	this.fecha = new Date();
	this.periodo = new Periodo();
	this.importe = 0.0;
	this.codCli = "";
    }

    public void setTarifa(Tarifa tarifa) {
	this.tarifa = tarifa;
    }

    public Date getFecha() {
	return fecha;
    }

    public void setFecha(Date fecha) {
	this.fecha = fecha;
    }

    public void setPeriodo(Periodo periodo) {
	this.periodo = periodo;
    }

    public void setImporte(double importe) {
	this.importe = importe;
    }

    public String getCodCli() {
	return codCli;
    }

    public void setCodCli(String codCli) {
	this.codCli = codCli;
    }

    public void setCodigo(int codigo) {
	this.codigo = codigo;
    }

    public int getCodigo() {
	return codigo;
    }

    @SuppressWarnings("deprecation")
    @Override
    public String toString() {
	return "\nInvoice number" + codigo + "\n ============== \n Added rate: "
		+ tarifa.getTarifaDescrip() + "\n Emitting date: " + fecha.getDate() + "/"
		+ fecha.getMonth() + "/" + (fecha.getYear()+1900)
		+ "\n Price: " + Math.rint(importe*100)/100 +"€"+ "\n Client: " + codCli
		+ "\n Period: " + periodo.getInicial().getDate() + "/"
		+ periodo.getInicial().getMonth() + "/" + (periodo.getInicial().getYear()+1900) + " to "
		+ periodo.getAlfinal().getDate() + "/"
		+ periodo.getAlfinal().getMonth() + "/" + (periodo.getAlfinal().getYear()+1900)+"\n";
    }

}
