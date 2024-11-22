/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package alumnoduoc.conexiondb;

import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Gabo's
 */
public class ConexionDB {
    public static ArrayList<Object[]> objectRS = new ArrayList<Object[]>();
    
    public static void main(String[] args) {
        AlumnoAgregarForm vista = new AlumnoAgregarForm();
        vista.updateForm();
        vista.setVisible(true);
    }
    
    public static void getAlumnos() {
        objectRS.clear();
        System.out.println(objectRS.toString());
        
        // Configuración de la conexión
        String url = "jdbc:mysql://localhost:3306/sys"; // Cambia "mi_base_de_datos" por tu nombre de base de datos
        String user = "root";  // Usuario de MySQL
        String password = "Informatica.2024"; // Contraseña del usuario

        Connection connection = null;

        try {
            // Establecer la conexión
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Conexión exitosa a la base de datos!");

            // Realizar una consulta para insertar un nuevo alumno
            String sql = "SELECT * FROM alumno";

            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet rs = statement.executeQuery(sql);
            
            while (rs.next()) {                
                objectRS.add(new Object[]{rs.getString(1), rs.getString(2) + "-" +rs.getString(3), rs.getString(4) + " " + rs.getString(5), rs.getString(6)});
                // System.out.println(rs.getString(1) +" "+ rs.getString(2) + "-" +rs.getString(3) +" "+ rs.getString(4) + " " + rs.getString(5) +" "+ rs.getString(6));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Cerrar la conexión
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void agregarAlumno(int run, String dv, String nombre, String apellido, String fechaNac) {

        // Configuración de la conexión
        String url = "jdbc:mysql://localhost:3306/sys"; // Cambia "mi_base_de_datos" por tu nombre de base de datos
        String user = "root";  // Usuario de MySQL
        String password = "Informatica.2024"; // Contraseña del usuario

        Connection connection = null;

        try {
            // Establecer la conexión
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Conexión exitosa a la base de datos!");

            // Realizar una consulta para insertar un nuevo alumno
            String sql = "INSERT INTO alumno (run, dv, nombre, apellido, fecha_nacimiento) VALUES (?, ?, ?, ?, ?)";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, run);        // run
            statement.setString(2, dv);          // dv
            statement.setString(3, nombre);       // nombre
            statement.setString(4, apellido);      // apellido
            statement.setDate(5, Date.valueOf(fechaNac));  // fecha_nacimiento

            // Ejecutar la inserción
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("¡Un nuevo alumno fue insertado exitosamente!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Cerrar la conexión
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

