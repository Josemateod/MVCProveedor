package Modelo;
//IMPORTA DESDE MONGODB LA CLASE DB, DBCOLLECTION,MONGOCLIENT y MONGOEXCEPTION
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;

public class ConexionBD {
    DB baseDatos;
    DBCollection coleccion;
    
    //CONSTRUCTOR
    public ConexionBD(){
        //PUEDEN DARSE MUCHOS PROBLEMAS AL CONECTARSE HACIA LA BD
        //NO EXISTE, RECHAZADO, FALTA AUTORIZACIONES
        try{
            //UBICADO EN LOCALHOST, MÁS AVANZADO SERÍA LA IP DE LA PC CON LA BD
            //EL NUMERO ES EL NUMERO DEL PUERTO ESPECIAL PARA 
            MongoClient mongo=new MongoClient("localhost",27017);
            //CONEXION BASE DE DATOS CLÍNICA
            baseDatos=mongo.getDB("clinica");
            //CONEXION A LA COLECCIÓN PROVEEDORES
            coleccion=baseDatos.getCollection("proveedores");
            //SABER SI LA CONEXION HA SIDO EXISTOSA
            System.out.println("Conexion a base de datos OK ");
        }catch(MongoException e){
            System.out.println(e.getMessage());
        }
    }
}
    