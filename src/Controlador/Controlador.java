/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import conexionBBDD.ConexionMySQL;
import despacho.Clientes.Cliente;
import despacho.Clientes.ClienteContrario;
import despacho.Procedimiento;
import despacho.Clientes.Vista.AnadirCliente;
import despacho.Vista.BuscarSwing;
import vista.PrincipalSwing;

/**
 *
 * @author gerar
 */
public class Controlador {
    ConexionMySQL con;
    Procedimiento p;

    public Controlador(){
        con = new ConexionMySQL();
    }   
    
    public ConexionMySQL getConexion(){
        return con;
    }
    
    
    public void anadirProcedimientoSwing(PrincipalSwing ventanaPrincipal) {
        Procedimiento p = new Procedimiento();
        p.anadirProcedimientoSwing(this, ventanaPrincipal);
    }
    
    public void visualizarProcedimientoSwing(PrincipalSwing ventanaPrincipal){
        
    }

    public Procedimiento buscarProcedimientoBBDD(String id) {
        Procedimiento p = new Procedimiento(id);
        p.getProcedimientoBBDD(this);
        return p;
    }

    public void cargarPanelBuscarSwing(PrincipalSwing ventanaPrincipal) {
        BuscarSwing buscarPanel = new BuscarSwing(this);
        ventanaPrincipal.cargarEnPanelPrincipal(buscarPanel);
    }
    
    public void buscarProcedimientoSwing(BuscarSwing buscarPanel){
        
    }

    public void anadirClienteAlProcedimiento() {
        AnadirCliente ventana = new AnadirCliente(null,true,false,null);
        ventana.setVisible(true);
        Cliente c = ventana.getCliente();
        if( c != null){
            p.addCliente(c);
        }   
    }
    
    public void anadirClienteContrarioAlProcedimiento(){
        AnadirCliente ventana = new AnadirCliente(null,true,true,null);
        ventana.setVisible(true);
        Cliente c = ventana.getCliente();
        if( c != null){
            p.addClienteContrario((ClienteContrario) c);
        } 
    }
    
    
}
