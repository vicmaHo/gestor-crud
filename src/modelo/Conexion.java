
package modelo;

import java.sql.*;
public class Conexion {
    private static final String URL = "jdbc:mysql://localhost:3306/estudiante_db";
    private static final String USER = "root";
    private static final String PASS = "admin";
    
    Connection conn;
    
    public Connection getConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection(URL, USER, PASS);
        } catch (Exception e) {
            System.out.println("Error en la conexion: " + e.getMessage());
        }
        
        
        return conn;
    
    }
    
    
    
}
