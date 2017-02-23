/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package despacho.Actuaciones;

import Controlador.Controlador;
import java.sql.Date;

/**
 *
 * @author gerar
 */



public abstract class Actuacion {
    public enum TipoFlujo{Entrada,Salida};
    private String nombre;
    private TipoFlujo tipoFlujo;
    private Date fecha;
    private String etiqueta;
    private String archivo;
    
    public Actuacion(){};
    
    public Actuacion(String nombre,TipoFlujo tipoFlujo,Date fecha,String etiqueta,String archivo){
        this.nombre = nombre;
        this.tipoFlujo = tipoFlujo;
        this.fecha = fecha;
        this.etiqueta = etiqueta;
        this.archivo = archivo;
    }
    
    public abstract void anadirABBDD(Controlador ctrl);
}
