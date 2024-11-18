/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package alumnoduoc.conexiondb;

import java.sql.*;

/**
 *
 * @author Gabo's
 */
public class ConexionDB {
    public static void main(String[] args) {
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
            statement.setInt(1, 12345678);        // run
            statement.setString(2, "9");          // dv
            statement.setString(3, "Juan");       // nombre
            statement.setString(4, "Pérez");      // apellido
            statement.setDate(5, Date.valueOf("2000-01-01"));  // fecha_nacimiento

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

