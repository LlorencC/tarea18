package piezasProveedores;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

public class PiezasProveedoresDB {
	static String bd = "piezasproveedores";
	static String login = "root";
	static String password = "BlazeSQL546!";
	static String url = "jdbc:mysql://localhost:3307/"+bd;
	
	public static void insertPieza(String Nombre, Connection conn) {
		try {
			String Querydb="USE "+bd+";";
			Statement stdb=conn.createStatement();
			stdb.executeUpdate(Querydb);
			String Query="INSERT INTO piezas(Nombre) VALUES(\""+Nombre+"\")";
			stdb.executeUpdate(Query);
		} catch (SQLException ex) {
			System.out.print(ex.getMessage());
		}
	}
	
	public static void insertProveedor(String idProveedor, String Nombre, Connection conn) {
		try {
			String Querydb="USE "+bd+";";
			Statement stdb=conn.createStatement();
			stdb.executeUpdate(Querydb);
			String Query="INSERT INTO proveedores(id, Nombre) VALUES(\""+idProveedor+"\",\""+Nombre+"\")";
			stdb.executeUpdate(Query);
		} catch (SQLException ex) {
			System.out.print(ex.getMessage());
		}
	}
	
	public static void insertSuministra(int CodigoPieza, String idProveedor, int Precio, Connection conn) {
		try {
			String Querydb="USE "+bd+";";
			Statement stdb=conn.createStatement();
			stdb.executeUpdate(Querydb);
			String Query="INSERT INTO suministra(CodigoPieza, idProveedor, Precio) VALUES("+CodigoPieza+",\""+idProveedor+"\", "+Precio+")";
			stdb.executeUpdate(Query);
		} catch (SQLException ex) {
			System.out.print(ex.getMessage());
		}
		
		
	}
	
	public static void main(String[] args) {
		Connection conn = null;
		String nombrePieza[] = {"piezaUno","piezaDos","piezaTres","piezaCuatro","piezaCinco" };
		String nombreProveedor[] = { "Burguir", "MacDounalds", "Hielera", "JAJAJA", "Hoja" };
		int precio[] = { 250, 200, 230, 100, 300};
		int codigoPieza[] = { 16, 17, 13, 14, 15};
		String idProveedor[] = { "aaba", "aaca", "aada", "aaea", "aafa"};
		try {
			String sURL = url;
			conn = DriverManager.getConnection(sURL,login,password);
			
			if (conn != null) {
				System.out.println("-Abierta base de datos " + url + " - Ok");
				
				//we insert 5 new pieces
				for (int i=0;i<nombrePieza.length;i++) {
					insertPieza(nombrePieza[i],conn);
				}
				for (int i=0;i<nombreProveedor.length;i++) {
					insertProveedor(idProveedor[i],nombreProveedor[i],conn);
				}
				for (int i=0;i<nombrePieza.length;i++) {
					insertSuministra(codigoPieza[i],idProveedor[i],precio[i],conn);
				}
				System.out.println("-Añadir registros a la tabla - Ok");
				
			}
		
		}
		catch (SQLException ex) {
			System.out.print(ex.getMessage());
			JOptionPane.showMessageDialog(null, "Error en el almacenamiento");
		}
	}
}
