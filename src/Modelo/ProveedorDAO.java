package Modelo;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import java.util.ArrayList;

public class ProveedorDAO {
    public void insertarProveedor(Proveedor objProveedor){
        ConexionBD objCon=new ConexionBD();
        BasicDBObject documento=new BasicDBObject();
        documento.put("nombre", objProveedor.getNombre());
        documento.put("pais", objProveedor.getPais());
        documento.put("edad", objProveedor.getEdad());
        objCon.coleccion.insert(documento);
    }
    public ArrayList obtenerProveedores(){
        ArrayList<Proveedor> listaProveedores=new ArrayList<>();
        Proveedor aux;
        ConexionBD objCon=new ConexionBD();
        DBCursor cursor=objCon.coleccion.find();
        while(cursor.hasNext()){
            aux=new Proveedor((String)cursor.next().get("nombre"),(String)cursor.curr().get("pais"), (Integer)cursor.curr().get("edad"));
            listaProveedores.add(aux);
        }
        return listaProveedores;
    }
    public void eliminarProveedor(String datoNombre){
        ConexionBD objCon=new ConexionBD();
        BasicDBObject documento=new BasicDBObject();
        documento.put("nombre", datoNombre);
        objCon.coleccion.remove(documento);
    }
    public void modificarProveedorPais(String nombreCambio, String paisCambio){
        ConexionBD objCon=new ConexionBD();
        DBObject buscado=new BasicDBObject("nombre",nombreCambio);
        //SENTENCIA QUE TIENE LA INFO A REEMPLAZAR
        DBObject nuevo=new BasicDBObject().append("$set",new BasicDBObject().append("pais", paisCambio));       
        objCon.coleccion.update(buscado,nuevo);       
    }
    public void modificarProveedorEdad(String nombreCambio, int edadCambio){
        ConexionBD objCon=new ConexionBD();
        DBObject buscado=new BasicDBObject("nombre",nombreCambio);
        //SENTENCIA QUE TIENE LA INFO A REEMPLAZAR
        DBObject nuevo=new BasicDBObject().append("$set",new BasicDBObject().append("edad", edadCambio));
        objCon.coleccion.update(buscado,nuevo);       
    }
    
    public ArrayList buscarProveedoresPais(String pais){
        ArrayList<Proveedor> listaProveedores=new ArrayList<>();
        Proveedor aux;
        ConexionBD objCon=new ConexionBD();
        BasicDBObject buscado=new BasicDBObject("pais", pais);
        DBCursor cursor=objCon.coleccion.find(buscado);
        while(cursor.hasNext()){
            aux=new Proveedor((String)cursor.next().get("nombre"),(String)cursor.curr().get("pais"),(Integer)cursor.curr().get("edad"));
            System.out.println("datos: "+aux);
            listaProveedores.add(aux);
        }
        return listaProveedores;
    }
}
