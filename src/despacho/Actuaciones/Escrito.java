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
public class Escrito extends Actuacion{

    public Escrito(String nombre,TipoFlujo tipoFlujo,Date fecha,String etiqueta,String archivo){
        super(nombre,tipoFlujo,fecha,etiqueta,archivo);
    }
    
    
    @Override
    public void anadirABBDD(Controlador ctrl) {
        String sql = "";
    }
    
}
