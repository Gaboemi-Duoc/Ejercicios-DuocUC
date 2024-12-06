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
	 
	public static ArrayList<String> cartasLista = new ArrayList<>();
	public static ArrayList<String> usuariosLista = new ArrayList<>();
	public static ArrayList<String> mazoLista = new ArrayList<>();
	
	public static void main(String[] args) {
		ManejadorForm vista = new ManejadorForm();
		vista.startUpdate();
		vista.setVisible(true);
	}
	
	
	// Gets
	public static void getCartas(){
		
		cartasLista.clear();

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
				//cartasObjectRS.add(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)});
				cartasLista.add( rs.getString(1) + " - " + rs.getString(2) + " - " + rs.getString(3) + " - " + rs.getString(4) + " - " + rs.getString(5) );
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

		usuariosLista.clear();

		Connection connection = null;

		try {
			// Establecer la conexión
			connection = DriverManager.getConnection(url, user, password);
			System.out.println("Conexión exitosa a la base de datos!");

			// Realizar una consulta para conseguir a
			String sql = "SELECT * FROM usuarios";

			PreparedStatement statement = connection.prepareStatement(sql);

			ResultSet rs = statement.executeQuery(sql);
			
			while (rs.next()) {                
				//cartasObjectRS.add(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)});
				usuariosLista.add( rs.getString(1) + " - " + rs.getString(2) + " - " + rs.getString(3) );
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
	public static void getMazoString(int idUsuario){

		mazoLista.clear();

		Connection connection = null;

		try {
			// Establecer la conexión
			connection = DriverManager.getConnection(url, user, password);
			System.out.println("Conexión exitosa a la base de datos!");

			// Realizar una consulta para conseguir a
			String sql = "SELECT * FROM mazos WHERE id_usuario = " + idUsuario;

			PreparedStatement statement = connection.prepareStatement(sql);
			//statement.setInt(1, idUsuario);			// idUsuario

			ResultSet rs = statement.executeQuery(sql);
			
			int count = 1;
			while (rs.next()) {                
				//cartasObjectRS.add(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)});
				mazoLista.add( count + " - " + rs.getString(2) + " - " + rs.getString(3) + " - " + rs.getString(4));
				count += 1;
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
	public static ArrayList getMazoData(int idUsuario){

		mazoLista.clear();

		Connection connection = null;
		
		ArrayList<Integer> dataMazo = new ArrayList<>();

		try {
			// Establecer la conexión
			connection = DriverManager.getConnection(url, user, password);
			System.out.println("Conexión exitosa a la base de datos!");

			// Realizar una consulta para conseguir a
			String sql = "SELECT id_carta FROM mazos WHERE id_usuario = " + idUsuario;

			PreparedStatement statement = connection.prepareStatement(sql);
			//statement.setInt(1, idUsuario);			// idUsuario

			ResultSet rs = statement.executeQuery(sql);
			
			while (rs.next()) {                
				int data = rs.getInt(1);
				dataMazo.add( data );
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
			
			return dataMazo;
		}
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
			statement.setString(1, nombre);			// nombre
			statement.setString(2, elemento);		// elemento
			statement.setInt(3, atq);				// ataque
			statement.setInt(4, def);				// defensa

			// Ejecutar la inserción
			int rowsInserted = statement.executeUpdate();
			if (rowsInserted > 0) {
				System.out.println("¡Carta Insertada!");
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
	public static void addUsuario(String nombre, String email){

		Connection connection = null;

		try {
			// Establecer la conexión
			connection = DriverManager.getConnection(url, user, password);
			System.out.println("Conexión exitosa a la base de datos!");

			// Realizar una consulta para insertar un nuevo alumno
			String sql = "INSERT INTO usuarios (nombre, email) VALUES (?, ?)";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, nombre);			// nombre
			statement.setString(2, email);		// correo

			// Ejecutar la inserción
			int rowsInserted = statement.executeUpdate();
			if (rowsInserted > 0) {
				System.out.println("¡Usuario Insertado!");
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
	public static void addMazo(String nombre, int idUsuario, int idCarta){

		Connection connection = null;

		try {
			// Establecer la conexión
			connection = DriverManager.getConnection(url, user, password);
			System.out.println("Conexión exitosa a la base de datos!");

			// Realizar una consulta para insertar un nuevo alumno
			String sql = "INSERT INTO mazos ( id_usuario, id_carta) VALUES ( ?, ?)";

			PreparedStatement statement = connection.prepareStatement(sql);
			//statement.setString(1, nombre);			// nombre
			statement.setInt(1, idUsuario);			// idUsuario
			statement.setInt(2, idCarta);				// idCarta

			// Ejecutar la inserción
			int rowsInserted = statement.executeUpdate();
			if (rowsInserted > 0) {
				System.out.println("Mazo Insertado!");
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
