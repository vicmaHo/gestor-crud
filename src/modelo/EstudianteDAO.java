
package modelo;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class EstudianteDAO {
    PreparedStatement ps;
    ResultSet rs;
    Connection conn;
    Conexion conectar = new Conexion();
    Estudiante e = new Estudiante();
    
    public List<Estudiante> listar(){
        List<Estudiante> datos = new ArrayList<>();
        try {
            // hago la conexion y ejecuto la instruccion para obtener todos los datos
            // almacenandolos en un resultset
            conn = conectar.getConnection();
            ps = conn.prepareStatement("SELECT * FROM estudiantes;");   
            rs = ps.executeQuery();
            
            // convierto los datos del resultset en objetos de estudiante y los agrego
            // a un List de estudiantes que posteriorment retornare
            while(rs.next()){
                Estudiante p = new Estudiante();
                p.setId(rs.getInt(1));
                p.setNombres(rs.getString(2));
                p.setCorreo(rs.getString(3));
                p.setTelefono(rs.getString(4));
                
                datos.add(p);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener la lista de todos: " + e.getMessage());
        }
        
        return datos;
    }
    
    public int agregar(Estudiante estudiante){
        int r = 0;
        String sql = "INSERT INTO estudiantes (Nombre, Correo, Telefono) VALUES (?,?,?);";
        
        try {
            conn = conectar.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, estudiante.getNombres());
            ps.setString(2, estudiante.getCorreo());
            ps.setString(3, estudiante.getTelefono());
            
            r = ps.executeUpdate();
            
        } catch (Exception e) {
            System.out.println("Error al agregar: " + e.getMessage());
        }
        
        return r;
    }
    
    public int actualizar(Estudiante estudiante){
        int r = 0;
        String sql = "UPDATE estudiantes SET Nombre = ?, Correo = ?, Telefono = ? WHERE Id = ?;";
        try {
            conn = conectar.getConnection();
            ps = conn.prepareStatement(sql);
            
            ps.setString(1, estudiante.getNombres());
            ps.setString(2, estudiante.getCorreo());
            ps.setString(3, estudiante.getTelefono());
            ps.setInt(  4, estudiante.getId());
            
            r = ps.executeUpdate();
            
            
        } catch (Exception e) {
            System.out.println("Error al actualizar: " + e.getMessage());
        }
        
        return r;
    }
    
    public int eliminar(int id){
        int r = 0;
        String sql = "DELETE FROM estudiantes WHERE id = ?";
        
        try {
            conn = conectar.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            
            r = ps.executeUpdate();
            
        } catch (Exception e) {
            System.out.println("Error al eliminar: " + e.getMessage());
        }
        
        
        return r;
    }
}
