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
	// Configuración de la conexión
	public static String url = "jdbc:mysql://localhost:3306/sys"; // Cambia "mi_base_de_datos" por tu nombre de base de datos
	public static String user = "root";  // Usuario de MySQL
	public static String password = "Informatica.2024"; // Contraseña del usuario
	 
	public static ArrayList<Object[]> objectRS = new ArrayList<>();
	public static ArrayList<Object[]> objectPruebasRS = new ArrayList<>();
	
	public static void main(String[] args) {
		AlumnoAgregarForm vista = new AlumnoAgregarForm();
		vista.updateForm();
		vista.setVisible(true);
	}
	
	public static void getAlumnos() {
		objectRS.clear();
		
		// Configuración de la conexión
		String url = "jdbc:mysql://localhost:3306/sys"; // Cambia "mi_base_de_datos" por tu nombre de base de datos
		String user = "root";  // Usuario de MySQL
		String password = "Informatica.2024"; // Contraseña del usuario

		Connection connection = null;

		try {
			// Establecer la conexión
			connection = DriverManager.getConnection(url, user, password);
			System.out.println("Conexión exitosa a la base de datos!");

			// Realizar una consulta para conseguir a
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
	
	public static void getPruebas() {
		objectPruebasRS.clear();
		
		// Configuración de la conexión
		String url = "jdbc:mysql://localhost:3306/sys"; // Cambia "mi_base_de_datos" por tu nombre de base de datos
		String user = "root";  // Usuario de MySQL
		String password = "Informatica.2024"; // Contraseña del usuario

		Connection connection = null;

		try {
			// Establecer la conexión
			connection = DriverManager.getConnection(url, user, password);
			System.out.println("Conexión exitosa a la base de datos!");

			// Realizar una consulta para conseguir a
			String sql = "SELECT * FROM fecha_pruebas";

			PreparedStatement statement = connection.prepareStatement(sql);

			ResultSet rs = statement.executeQuery(sql);
			
			int curso = (int)rs.getInt("id_curso");
			String sCurso;
			switch (curso) {
				case 1:
					sCurso = "A1";
					break;
				default:
					throw new AssertionError();
			}
			
			while (rs.next()) {                
				objectPruebasRS.add(new Object[]{rs.getString(1), rs.getString(3), rs.getInt("id_curso"), rs.getString(4)});
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
			
			objectRS.clear();
			
			

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
	
	public static void actualizarAlumnoPorID(int idAlumno,int run, String dv, String nombre, String apellido, String fechaNac) {
		Connection connection = null;

		try {
			// Establecer la conexión
			connection = DriverManager.getConnection(url, user, password);
			System.out.println("Conexión exitosa a la base de datos!");

			// Consulta SQL para actualizar un alumno basado en el id_alumno
			String sql = "UPDATE alumno SET run= ?, dv = ?, nombre = ?, apellido = ?, fecha_nacimiento = ? WHERE id_alumno = ?";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, run);             // dv
			statement.setString(2, dv);             // dv
			statement.setString(3, nombre);         // nombre
			statement.setString(4, apellido);       // apellido
			statement.setDate(5, Date.valueOf(fechaNac)); // fecha_nacimiento
			statement.setInt(6, idAlumno);          // id_alumno (condición)

			// Ejecutar la actualización
			int rowsUpdated = statement.executeUpdate();
			if (rowsUpdated > 0) {
				System.out.println("¡El registro del alumno fue actualizado exitosamente!");
			} else {
				System.out.println("No se encontró un alumno con el ID especificado.");
			}
			//getAlumnos();
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

	public static void eliminarAlumno(int idAlumno) {
		Connection connection = null;

		try {
			// Establecer la conexión
			connection = DriverManager.getConnection(url, user, password);
			System.out.println("Conexión exitosa a la base de datos!");

			// Consulta SQL para eliminar un alumno basado en el id_alumno
			String sql = "DELETE FROM alumno WHERE id_alumno = ?";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, idAlumno);  // Establecer el id_alumno para la condición WHERE

			// Ejecutar la eliminación
			int rowsDeleted = statement.executeUpdate();
			if (rowsDeleted > 0) {
				System.out.println("¡El alumno fue eliminado exitosamente!");

			} else {
				System.out.println("No se encontró un alumno con el ID especificado.");
			}
			//getAlumnos();
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
	
	public static void setCursoAlumno(int id_alumno, int id_curso) {
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
			String sql = "INSERT INTO cursos_alumnos (id_alumno, id_curso) VALUES (?, ?)";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, id_alumno);        // run
			statement.setInt(2, id_curso);          // dv

			// Ejecutar la inserción
			int rowsInserted = statement.executeUpdate();
			if (rowsInserted > 0) {
				System.out.println("¡Un nuevo alumno fue ingresado a un curso exitosamente!");
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
	
	public static void addPrueba(int id_curso, String nombre, String fechaPrueba) {

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
			String sql = "INSERT INTO fecha_pruebas (id_curso, nombre, fecha) VALUES (?, ?, ?)";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, id_curso);       
			statement.setString(2, nombre);         
			statement.setDate(3, Date.valueOf(fechaPrueba));        

			// Ejecutar la inserción
			int rowsInserted = statement.executeUpdate();
			if (rowsInserted > 0) {
				System.out.println("¡Un nuevo alumno fue insertado exitosamente!");
			}
			
			objectPruebasRS.clear();
			
			

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

