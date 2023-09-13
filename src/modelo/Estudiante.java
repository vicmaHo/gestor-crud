
package modelo;

public class Estudiante {
    private int id;
    private String nombres;
    private String correo;
    private String telefono;

    public Estudiante() {
        
    }

    public Estudiante(int id, String nombres, String correo, String telefono) {
        this.id = id;
        this.nombres = nombres; 
        this.correo = correo;
        this.telefono = telefono;
    }

    public Estudiante(String nombres, String correo, String telefono) {
        this.nombres = nombres;
        this.correo = correo;
        this.telefono = telefono;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
