package prueba.test;

import static org.junit.Assert.assertEquals;

import java.sql.Time;
import java.util.Date;

import modelo.Llamada;
import modelo.tiposDatos.TarifaDecorator.Tarifa;
import modelo.tiposDatos.TarifaDecorator.TarifaBase;
import modelo.tiposDatos.TarifaDecorator.TarifaDomingo;
import modelo.tiposDatos.TarifaDecorator.TarifaTardes;

import org.junit.Test;

public class Prueba3 {
    //PRUEBAS DE TARIFA DECORATOR
    
    @Test 
    public void testTarifaBase(){
	Tarifa t = new TarifaBase(1);
	Llamada l = new Llamada();
	double h = t.getPrecio(l);
	assertEquals(1.0,h,0);
    }
    
    @Test
    public void testDomingos(){
	Tarifa t = new TarifaBase(1);
	t = new TarifaDomingo(t);
	Llamada l = new Llamada();
	double h = t.getPrecio(l);
	assertEquals(0.0,h,0);
    }
   
    @Test
    public void testTardes(){
	Tarifa t = new TarifaBase(1);
	t = new TarifaTardes(t);
	Llamada l = new Llamada();
	double h = t.getPrecio(l);
	assertEquals(0.5,h,0);
    }
    
    @Test
    public void mejorTarifaTest(){
	Tarifa t = new TarifaBase(1);
	t = new TarifaTardes(t);
	t = new TarifaDomingo(t);
	Llamada l = new Llamada();
	double h = t.getPrecio(l);
	assertEquals(0,h,0);
    }
    
    @Test
    public void reverseMejorTarifaTest(){
	Tarifa t = new TarifaBase(1);
	t = new TarifaDomingo(t);
	t = new TarifaTardes(t);
	Llamada l = new Llamada();
	double h = t.getPrecio(l);
	assertEquals(0,h,0);
    }
    
    @Test
    public void NoCumpleMejorTarifaTest(){
	Tarifa t = new TarifaBase(1);
	t = new TarifaDomingo(t);
	t = new TarifaTardes(t);
	@SuppressWarnings("deprecation")
	Llamada l = new Llamada(0,new Date(2015-1900,5,17),new Time(19,19,19),60);
	double h = t.getPrecio(l);
	assertEquals(0.5,h,0);
    }
    
    @Test
    public void NoCumpleReverseMejorTarifaTest(){
	Tarifa t = new TarifaBase(1);
	t = new TarifaDomingo(t);
	t = new TarifaTardes(t);
	@SuppressWarnings("deprecation")
	Llamada l = new Llamada(0,new Date(2015-1900,4,17),new Time(1,1,1),60);
	double h = t.getPrecio(l);
	assertEquals(0,h,0);
    
    }  
}
