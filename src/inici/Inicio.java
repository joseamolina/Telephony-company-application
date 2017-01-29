package inici;

import controlador.ImplementacionControlador;
import modelo.CliLlamFac;
import modelo.ImplementacionModelo;
import vista.ImplementacionVista;

public class Inicio {
    public static void main(String[] args) {
	inicioTotal();
    }
    
    private static void inicioTotal() {
	ImplementacionControlador controlador = new ImplementacionControlador();
	controlador.entrada();
	ImplementacionModelo<CliLlamFac> modelo = new ImplementacionModelo<CliLlamFac>();
	ImplementacionVista vista = new ImplementacionVista();
	vista.setControlador(controlador);
	modelo.setVista(vista);
	controlador.setVista(vista);
	controlador.setModelo(modelo);
	vista.initGUI();
    }
}
