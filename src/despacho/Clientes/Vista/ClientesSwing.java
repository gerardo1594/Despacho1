/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package despacho.Clientes.Vista;

import Controlador.Controlador;
import despacho.Clientes.Cliente;
import despacho.Clientes.Vista.ClienteLista;
import despacho.Procedimiento;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import static javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER;
import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER;

/**
 *
 * @author gerar
 */
public class ClientesSwing extends javax.swing.JPanel {
    private Procedimiento procedimiento;
    /**
     * Creates new form ActuacionesSwing
     * @param procedimiento
     */
    public ClientesSwing(Procedimiento procedimiento,Controlador ctrl) {
        initComponents();
        this.procedimiento = procedimiento;
        int tamano = 0;
        for (Cliente c : this.procedimiento.getClientes()) {
            panelClientes.add(c.visualizarClienteSwing(ctrl));
            tamano += 210;
        }
        panelClientes.setSize(tamano,350);
        panelClientes.revalidate();
        panelClientes.repaint();
        scrollPanelClientes.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);
        tamano = 0;
        for (Cliente c : this.procedimiento.getClientesContrarios()) {
            panelClientesContrarios.add(c.visualizarClienteSwing(ctrl));
            tamano += 210;
        }
        panelClientesContrarios.setSize(350, 1000);
        panelClientesContrarios.revalidate();
        panelClientesContrarios.repaint();
        scrollPanelContrarios.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        scrollPanelClientes = new javax.swing.JScrollPane();
        panelClientes = new javax.swing.JPanel();
        scrollPanelContrarios = new javax.swing.JScrollPane();
        panelClientesContrarios = new javax.swing.JPanel();

        setPreferredSize(new java.awt.Dimension(787, 580));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Clientes:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Clientes Contrarios:");

        javax.swing.GroupLayout panelClientesLayout = new javax.swing.GroupLayout(panelClientes);
        panelClientes.setLayout(panelClientesLayout);
        panelClientesLayout.setHorizontalGroup(
            panelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 350, Short.MAX_VALUE)
        );
        panelClientesLayout.setVerticalGroup(
            panelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 482, Short.MAX_VALUE)
        );

        scrollPanelClientes.setViewportView(panelClientes);

        javax.swing.GroupLayout panelClientesContrariosLayout = new javax.swing.GroupLayout(panelClientesContrarios);
        panelClientesContrarios.setLayout(panelClientesContrariosLayout);
        panelClientesContrariosLayout.setHorizontalGroup(
            panelClientesContrariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 350, Short.MAX_VALUE)
        );
        panelClientesContrariosLayout.setVerticalGroup(
            panelClientesContrariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 482, Short.MAX_VALUE)
        );

        scrollPanelContrarios.setViewportView(panelClientesContrarios);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(scrollPanelClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(scrollPanelContrarios, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(scrollPanelClientes)
                    .addComponent(scrollPanelContrarios))
                .addContainerGap(20, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel panelClientes;
    private javax.swing.JPanel panelClientesContrarios;
    private javax.swing.JScrollPane scrollPanelClientes;
    private javax.swing.JScrollPane scrollPanelContrarios;
    // End of variables declaration//GEN-END:variables
}
