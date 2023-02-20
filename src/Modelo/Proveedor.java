package Modelo;
public class Proveedor {
    //ATRIBUTOS
    private String nombre;
    private String pais;
    private int edad;
    //CONTRUCTOR
    public Proveedor() {
    }
    public Proveedor(String nombre, String pais, int edad) {
        this.nombre = nombre;
        this.pais = pais;
        this.edad=edad;
    }
    //GET AND SET
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getPais() {
        return pais;
    }
    public void setPais(String pais) {
        this.pais = pais;
    }
    public int getEdad() {
        return edad;
    }
    public void setEdad(int edad) {
        this.edad = edad;
    } 
    @Override
    public String toString() {
        return "Proveedor{" + "nombre=" + nombre + ", pais=" + pais + '}';
    }    
}
