/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package despacho.Clientes;

import Controlador.Controlador;
import java.util.HashMap;
import despacho.Clientes.Vista.AnadirCliente;
import despacho.Clientes.Vista.ClienteLista;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 *
 * @author gerar
 */
public class ClienteContrario extends Cliente{
    
    public ClienteContrario(){
        super();
    }
    
    public ClienteContrario(HashMap<String,String> datos){
        super(datos);
    }
    
    public ClienteContrario(String dni,String nombre,String apellidos){
        super(dni,nombre,apellidos);
    }
    
    @Override
    public JPanel visualizarClienteSwing(Controlador ctrl){
        JPanel panel = new ClienteLista(this,true);
        panel.setBorder(BorderFactory.createLineBorder(Color.black));
        panel.setSize(345, 210);
        return panel;
    }
    
}
