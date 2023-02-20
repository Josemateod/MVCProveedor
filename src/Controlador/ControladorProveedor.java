package Controlador;

import Modelo.Proveedor;
import Modelo.ProveedorDAO;
import Vista.FrmProveedor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ControladorProveedor implements ActionListener, KeyListener {
    //ATRIBUTOS
    FrmProveedor objVista=new FrmProveedor();
    ProveedorDAO objDAO=new ProveedorDAO();
    Proveedor objProveedor=new Proveedor();
    
    //CONSTRUCTOR
    public ControladorProveedor(FrmProveedor vista, ProveedorDAO dao) {
        objVista=vista;
        objDAO=dao;
        objVista.btnAgregar.addActionListener(this);
        objVista.btnMostrar.addActionListener(this);
        objVista.btnEditar.addActionListener(this);
        objVista.btnModificar.addActionListener(this);
        objVista.btnEliminar.addActionListener(this);
        objVista.txtNombre.addKeyListener(this);
        objVista.txtPais.addKeyListener(this);
        objVista.txtBuscado.addKeyListener(this);
        objVista.txtEdad.addKeyListener(this);
    }
    //MÉTODOS
    public void llenarTabla(JTable tablaD){
        DefaultTableModel modeloT=new DefaultTableModel();
        tablaD.setModel(modeloT);
        modeloT.addColumn("NOMBRE");
        modeloT.addColumn("PAIS");
        modeloT.addColumn("EDAD");
        Object [] columna=new Object[3];
        int numReg=objDAO.obtenerProveedores().size();
        for(int i=0;i<numReg;i++){
            objProveedor=(Proveedor)objDAO.obtenerProveedores().get(i);
            columna[0]=objProveedor.getNombre();
            columna[1]=objProveedor.getPais();
            columna[2]=objProveedor.getEdad();
            modeloT.addRow(columna);
        }
    }
    public void limpiarElementos(){
        objVista.txtNombre.setText("");
        objVista.txtNombre.setEditable(true);
        objVista.txtPais.setText("");
        objVista.txtEdad.setText("");
    }  
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==objVista.btnAgregar)
        {
            String nombre= objVista.txtNombre.getText();
            String pais= objVista.txtPais.getText();
            //CONVERTIR DE CADENA DE TEXTO A NUMERO
            int edad=Integer.parseInt(objVista.txtEdad.getText());
            Proveedor objP= new Proveedor(nombre, pais,edad);
            objDAO.insertarProveedor(objP);
            limpiarElementos();
        }
        if (e.getSource()==objVista.btnMostrar)
        {
            llenarTabla(objVista.jtDatos);
        }

        if (e.getSource()== objVista.btnEditar){
            int filaEditar= objVista.jtDatos.getSelectedRow();
            if(filaEditar >=0){
                objVista.txtNombre.setText(String.valueOf(objVista.jtDatos.getValueAt(filaEditar, 0)));
                objVista.txtNombre.setEditable(false);
                objVista.btnAgregar.setEnabled(false);
                objVista.btnEditar.setEnabled(false);
                objVista.btnEliminar.setEnabled(false);
                objVista.txtBuscado.setEnabled(false);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Debe seleccionar una fila");
            } 
        }      
        if (e.getSource()== objVista.btnModificar)
        {
            String nombre= objVista.txtNombre.getText();
            String pais= objVista.txtPais.getText();
            int edad=Integer.parseInt(objVista.txtEdad.getText());
            objDAO.modificarProveedorPais(nombre, pais);
            objDAO.modificarProveedorEdad(nombre, edad);
            limpiarElementos();
            objVista.btnAgregar.setEnabled(true);
            objVista.btnEditar.setEnabled(true);
            objVista.btnEliminar.setEnabled(true);
            objVista.txtBuscado.setEnabled(true);
            objVista.btnModificar.setEnabled(false);
        }
        
        if(e.getSource()== objVista.btnEliminar)
        {
            int filaInicio= objVista.jtDatos.getSelectedRow();
            int numFS= objVista.jtDatos.getSelectedRowCount();
            ArrayList <String> listaNombres = new ArrayList();
            String nom="";
            if (filaInicio>=0){
                for(int i=0; i<numFS; i++){
                    nom= String.valueOf(objVista.jtDatos.getValueAt(i+filaInicio, 0));
                    listaNombres.add(nom);
                }
                for (int i=0; i<numFS; i++){
                    int respuesta= JOptionPane.showConfirmDialog(null, "quiere eliminar registro con nombre "+ nom+" ?");
                    //si; respuesta=0
                    //no; respuesta =1
                    //cancelar; respuesta=2               
                    if (respuesta==0){
                        objDAO.eliminarProveedor(nom);                   
                    }
                }
                llenarTabla(objVista.jtDatos);           
        }
            else{
                JOptionPane.showMessageDialog(null, "Debe seleccionar una fila a eliminar");
            }
    
    }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //evento al presionar tecla especial que no es caracter ni numero
        if (e.getSource()== objVista.txtNombre) 
        {
            char c= e.getKeyChar();
            if ((c<'a' || c>'z') && (c< 'A'|| c >'Z'))
            {
                e.consume();
            }
        }
    }
    @Override
    public void keyPressed(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getSource()==objVista.txtBuscado){
            String  pais= objVista.txtBuscado.getText();
            
            DefaultTableModel modeloT= new DefaultTableModel();
            objVista.jtDatos.setModel(modeloT);
            modeloT.addColumn("NOMBRE");
            modeloT.addColumn("PAIS");
            modeloT.addColumn("EDAD");        
            Object [] columna= new Object[3];
            int numReg= objDAO.buscarProveedoresPais(pais).size();           
            for(int i=0; i<numReg; i++)
            {
                objProveedor= (Proveedor) objDAO.buscarProveedoresPais(pais).get(i);
                System.out.println(objProveedor);
                columna[0]= objProveedor.getNombre();
                columna[1]=objProveedor.getPais();
                columna[2]=objProveedor.getEdad();
                modeloT.addRow(columna);               
             }      
        }
    }   
}
