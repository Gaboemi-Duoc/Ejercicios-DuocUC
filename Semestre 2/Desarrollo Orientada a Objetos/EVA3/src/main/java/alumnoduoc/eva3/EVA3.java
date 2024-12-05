/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package alumnoduoc.eva3;

import java.sql.*;
import java.util.ArrayList;

import vista.ManejadorForm;

/**
 *
 * @author Gabo's
 */
public class EVA3 {
	// Configuración de la conexión
	public static String url = "jdbc:mysql://localhost:3306/sys"; // Cambia "mi_base_de_datos" por tu nombre de base de datos
	public static String user = "root";  // Usuario de MySQL
	public static String password = "Informatica.2024"; // Contraseña del usuario
	 
	public static ArrayList<Object[]> cartasObjectRS = new ArrayList<>();
	public static ArrayList<Object[]> usuariosObjectRS = new ArrayList<>();
	public static ArrayList<Object[]> mazoObjectRS = new ArrayList<>();
	public static ArrayList<String> mazoLista = new ArrayList<>();
	
	public static void main(String[] args) {
		ManejadorForm vista = new ManejadorForm();
		vista.setVisible(true);
	}
	
	
	// Gets
	public static void getCartas(){
		
		cartasObjectRS.clear();

		Connection connection = null;

		try {
			// Establecer la conexión
			connection = DriverManager.getConnection(url, user, password);
			System.out.println("Conexión exitosa a la base de datos!");

			// Realizar una consulta para conseguir a
			String sql = "SELECT * FROM cartas";

			PreparedStatement statement = connection.prepareStatement(sql);

			ResultSet rs = statement.executeQuery(sql);
			
			while (rs.next()) {                
				cartasObjectRS.add(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)});
				mazoLista.add( rs.getString(1) + " - " + rs.getString(2) + " - " + rs.getString(3) + " - " + rs.getString(4) + " - " + rs.getString(5) );
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
	public static void getUsuarios(){
		
	}
	public static void getMazo(int idUsuario){
	}
	
	// Sets
	public static void addCarta(String nombre, String elemento, int atq, int def){

		Connection connection = null;

		try {
			// Establecer la conexión
			connection = DriverManager.getConnection(url, user, password);
			System.out.println("Conexión exitosa a la base de datos!");

			// Realizar una consulta para insertar un nuevo alumno
			String sql = "INSERT INTO cartas (nombre, elemento, ataque, defensa) VALUES (?, ?, ?, ?)";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, nombre);        // run
			statement.setString(2, elemento);          // dv
			statement.setInt(3, atq);       // nombre
			statement.setInt(4, def);      // apellido

			// Ejecutar la inserción
			int rowsInserted = statement.executeUpdate();
			if (rowsInserted > 0) {
				System.out.println("¡Carta Insertada!");
			}
			
			cartasObjectRS.clear();
			
			

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
