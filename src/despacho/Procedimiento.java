/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package despacho;

import Controlador.Controlador;
import despacho.Abogados.Abogado;
import despacho.Clientes.Cliente;
import conexionBBDD.ConexionMySQL;
import despacho.Actuaciones.Actuacion;
import despacho.Actuaciones.Vista.ActuacionesSwing;
import despacho.Clientes.ClienteContrario;
import despacho.Clientes.Vista.ClientesSwing;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JFrame;
import javax.swing.JPanel;
import despacho.Vista.AnadirSwing;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTabbedPane;
import vista.PrincipalSwing;

/**
 *
 * @author gerar
 */
public class Procedimiento {
    private String nAuto,juzgado,jucio,cuantia,id;
    private ArrayList<Cliente> clientes;
    private ArrayList<ClienteContrario> clientesContrarios;
    private ArrayList<Actuacion> actuaciones;

    public Procedimiento() {
        clientes = new ArrayList();
        clientesContrarios = new ArrayList();
    }

    public Procedimiento(String id) {
        this.id = id;
        clientes = new ArrayList();
        clientesContrarios = new ArrayList();
    }
    
    public String getnAuto() {
        return nAuto;
    }

    public void setnAuto(String nAuto) {
        this.nAuto = nAuto;
    }

    public String getJuzgado() {
        return juzgado;
    }

    public void setJuzgado(String juzagado) {
        this.juzgado = juzagado;
    }

    public String getJucio() {
        return jucio;
    }

    public void setJucio(String jucio) {
        this.jucio = jucio;
    }

    public String getCuantia() {
        return cuantia;
    }

    public void setCuantia(String cuantia) {
        this.cuantia = cuantia;
    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(ArrayList<Cliente> clientes) {
        this.clientes = clientes;
    }

    public ArrayList<ClienteContrario> getClientesContrarios() {
        return clientesContrarios;
    }

    public void setClientesContrarios(ArrayList<ClienteContrario> clientesContrarios) {
        this.clientesContrarios = clientesContrarios;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }  
    
    public void addCliente(Cliente cliente){
        this.clientes.add(cliente);
    }
    
    public void addClienteContrario(ClienteContrario cliente){
        this.clientesContrarios.add(cliente);
    }
    
    
    public void anadirABBDD(){
        ConexionMySQL con = new ConexionMySQL();
        String sql = "SELECT IDProcInterno FROM procedimiento WHERE `Numero Auto` = '"+nAuto+"' AND `Juzgado` = '"+juzgado+"' AND `Juicio` = '"+jucio+"' AND `Cuantia` = '"+cuantia+"'";
        ArrayList datos = con.executeQuery(sql);
        if(datos.isEmpty()){
            sql = "INSERT INTO `procedimiento` (`IDProcInterno`, `Numero Auto`, `Juzgado`, `Juicio`, `Cuantia`) VALUES (NULL, '"+nAuto+"', '"+juzgado+"', '"+jucio+"', '"+cuantia+"');";                  
        }  
        con.execute(sql);
        for (Cliente cliente : clientes) {
            cliente.anadirABBDD();
            Abogado a = cliente.getAbogado();
            sql = "INSERT INTO `procaboprocucliente` (`IDProcedimiento`, `IDProcurador`, `IDAbogado`, `IDCliente`) VALUES "
                    + "('"+getIDProcedimientoBBDD()+"', '"+a.getProcurador().getIDdeBBDD()+"', '"+a.getIDdeBBDD()+"', '"+cliente.getIDdeBBDD()+"');";
            con.execute(sql);
        }
        for (Cliente clienteContrario : this.clientesContrarios) {
            clienteContrario.anadirABBDD();
            Abogado a = clienteContrario.getAbogado();
            sql = "INSERT INTO `procaboprocuclienteContrario` (`IDProcedimiento`, `IDProcurador`, `IDAbogado`, `IDCliente`) VALUES "
                    + "('"+getIDProcedimientoBBDD()+"', '"+a.getProcurador().getIDdeBBDD()+"', '"+a.getIDdeBBDD()+"', '"+clienteContrario.getIDdeBBDD()+"');";
            con.execute(sql);
        }
    }
    public int getIDProcedimientoBBDD(){
        ConexionMySQL con = new ConexionMySQL();
        String sql = "SELECT IDProcInterno FROM Procedimiento WHERE `Numero Auto` = '"+nAuto+"' AND Juzgado = '"+juzgado+"' AND Juicio = '"+jucio+"' AND Cuantia = '"+cuantia+"'";
        HashMap<String,String> datos = (HashMap<String,String>) con.executeQuery(sql).get(0);
        id = datos.get("IDProcInterno");
        return Integer.parseInt(id); 
    }
    
    public void getAllProcedimientoBBDD(Controlador ctrl,String id){
        ConexionMySQL con = ctrl.getConexion();
        String sql = "SELECT * FROM Procedimiento WHERE `IDProcInterno` = '"+id+"'";
        HashMap<String,String> datos = (HashMap<String,String>) con.executeQuery(sql).get(0);
        this.id = id;
        nAuto = datos.get("Numero Auto");
        jucio = datos.get("Juicio");
        juzgado = datos.get("Juzgado");
        cuantia = datos.get("Cuantia");
        sql = "SELECT PAPC.IDCliente,DNI,C.Nombre,C.Apellidos,PAPC.IDAbogado,A.Nombre 'Nombre Abogado',A.Apellidos 'Apellidos Abogado',P.`Nombre Despacho` FROM cliente C, procaboprocucliente PAPC,abogado A,Procurador P WHERE C.IDCliente = PAPC.IDCliente "+
                "AND PAPC.IDAbogado = A.IDAbogado AND PAPC.IDProcurador = P.IDProcurador AND PAPC.IDProcedimiento = '"+id+"'";
        ArrayList<HashMap<String,String>> datosClientes = con.executeQuery(sql);
        this.clientes = new ArrayList();
        for (HashMap<String, String> datosCliente : datosClientes) {
            Cliente cliente = new Cliente(datosCliente);
            this.clientes.add(cliente);
        }
        
        sql = "SELECT PAPCC.IDCliente,DNI,C.Nombre,C.Apellidos,PAPCC.IDAbogado,A.Nombre 'Nombre Abogado',A.Apellidos 'Apellidos Abogado',P.`Nombre Despacho` FROM cliente C, procaboprocuclientecontrario PAPCC,abogado A,Procurador P WHERE C.IDCliente = PAPCC.IDCliente "+
                "AND PAPCC.IDAbogado = A.IDAbogado AND PAPCC.IDProcurador = P.IDProcurador AND PAPCC.IDProcedimiento = '"+id+"'";
    
        ArrayList<HashMap<String,String>> datosClientesContrarios = con.executeQuery(sql);
        this.clientesContrarios = new ArrayList();
        for (HashMap<String, String> datosCliente : datosClientesContrarios) {
            ClienteContrario cliente = new ClienteContrario(datosCliente);
            this.clientesContrarios.add(cliente);
        }
    }
    
    public void getProcedimientoBBDD(Controlador ctrl){
        String sql = "SELECT * FROM Procedimiento WHERE `IDProcInterno` = '"+id+"'";
        HashMap<String,String> datos = (HashMap<String,String>) ctrl.getConexion().executeQuery(sql).get(0);
        nAuto = datos.get("Numero Auto");
        jucio = datos.get("Juicio");
        juzgado = datos.get("Juzgado");
        cuantia = datos.get("Cuantia");
    }
    
    public void getClientesBBDD(Controlador ctrl){
        ConexionMySQL con = ctrl.getConexion();
        String sql = "SELECT PAPC.IDCliente,DNI,C.Nombre,C.Apellidos,PAPC.IDAbogado,A.Nombre 'Nombre Abogado',A.Apellidos 'Apellidos Abogado',P.`Nombre Despacho` FROM cliente C, procaboprocucliente PAPC,abogado A,Procurador P WHERE C.IDCliente = PAPC.IDCliente "+
                "AND PAPC.IDAbogado = A.IDAbogado AND PAPC.IDProcurador = P.IDProcurador AND PAPC.IDProcedimiento = '"+id+"'";
        ArrayList<HashMap<String,String>> datosClientes = con.executeQuery(sql);
        this.clientes = new ArrayList();
        for (HashMap<String, String> datosCliente : datosClientes) {
            Cliente cliente = new Cliente(datosCliente);
            this.clientes.add(cliente);
        }
        
        sql = "SELECT PAPCC.IDCliente,DNI,C.Nombre,C.Apellidos,PAPCC.IDAbogado,A.Nombre 'Nombre Abogado',A.Apellidos 'Apellidos Abogado',P.`Nombre Despacho` FROM cliente C, procaboprocuclientecontrario PAPCC,abogado A,Procurador P WHERE C.IDCliente = PAPCC.IDCliente "+
                "AND PAPCC.IDAbogado = A.IDAbogado AND PAPCC.IDProcurador = P.IDProcurador AND PAPCC.IDProcedimiento = '"+id+"'";
    
        ArrayList<HashMap<String,String>> datosClientesContrarios = con.executeQuery(sql);
        this.clientesContrarios = new ArrayList();
        for (HashMap<String, String> datosCliente : datosClientesContrarios) {
            ClienteContrario cliente = new ClienteContrario(datosCliente);
            this.clientesContrarios.add(cliente);
        }
    }

    public void anadirPadreBBDD(String idPadre) {
        String sql = "INSERT INTO `procedimientopadrehijo` (`IDProcedimientoPadre`, `IDProcedimientoHijo`) VALUES ('"+idPadre+"', '"+getIDProcedimientoBBDD()+"')";
        ConexionMySQL con = new ConexionMySQL();
        con.execute(sql);
    }
    
    public void anadirProcedimientoSwing(Controlador ctrl,PrincipalSwing ventana){
        ventana.cargarEnPanelPrincipal(new AnadirSwing(ctrl));
    }
    
    public void visualizarProcedimientoSwing(Controlador ctrl,JTabbedPane ventana){
        AnadirSwing tabProcedimiento = new AnadirSwing(this,ctrl);
        tabProcedimiento.setSize(810,580);
        //ventana.setSize(810,580);
        ventana.removeAll();
        ventana.add("Informacion",tabProcedimiento);
        getClientesBBDD(ctrl);
        ClientesSwing tabClientes = new ClientesSwing(this,ctrl);
        ventana.add("Clientes",tabClientes);
        
        ActuacionesSwing tabActuaciones = new ActuacionesSwing(this);
        ventana.add("Actuaciones",tabActuaciones);
        /*tabProcedimiento.revalidate();
        tabProcedimiento.repaint();*/
        ventana.revalidate();
        ventana.repaint();
        
        //btnActualizar.setVisible(true);
    }
    
    public void anadirProcedimientoHijoSwing(Controlador ctrl,JFrame ventana){
        
    }
}
