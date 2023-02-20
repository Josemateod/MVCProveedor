package Controlador;

import Modelo.ConexionBD;
import Modelo.ProveedorDAO;
import Vista.FrmProveedor;

/**
 * 
 * PROBAR LA CONEXION MEDIANTE ESTA CLASE
 */
public class GestorMVCProveedor {
    //CREAMOS EL MAIN
    public static void main(String args[]){
        FrmProveedor v= new FrmProveedor();
        ProveedorDAO pDAO= new ProveedorDAO();
        ControladorProveedor c= new ControladorProveedor(v, pDAO);
        v.setVisible(true);
        v.setLocationRelativeTo(null);
    }
    
}
