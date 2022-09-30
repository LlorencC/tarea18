package losInvestigadoresJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class investigadoresDB {
	static String bd = "investigadoresdb";
	static String login = "root";
	static String password = "BlazeSQL546!";
	static String url = "jdbc:mysql://localhost:3307/"+bd;
	
	public static void insertFacultad(String Nombre, Connection conn) {
		try {
			String Querydb="USE "+bd+";";
			Statement stdb=conn.createStatement();
			stdb.executeUpdate(Querydb);
			String Query="INSERT INTO facultad(Nombre) VALUES(\""+Nombre+"\");";
			stdb.executeUpdate(Query);
			//System.out.print(Query);
		} catch (SQLException ex) {
			System.out.print(ex.getMessage());
		}
	}

	public static void insertEquipo(String NumSerie, String Nombre, int idFacu, Connection conn) {
		try {
			String Querydb="USE "+bd+";";
			Statement stdb=conn.createStatement();
			stdb.executeUpdate(Querydb);
			String Query="INSERT INTO equipos(NumSerie,Nombre,Facultad) VALUES(\""+NumSerie+"\",\""+Nombre+"\","+idFacu+");";
			stdb.executeUpdate(Query);
			//System.out.print(Query);
		} catch (SQLException ex) {
			System.out.print(ex.getMessage());
		}
	}

	public static void insertInvestigadores(String DNI, String NomApels, int idFacu, Connection conn) {
		try {
			String Querydb="USE "+bd+";";
			Statement stdb=conn.createStatement();
			stdb.executeUpdate(Querydb);
			String Query="INSERT INTO investigadores(DNI, NomApels,Facultad) VALUES(\""+DNI+"\",\""+NomApels+"\", "+idFacu+");";
			stdb.executeUpdate(Query);
			//System.out.print(Query);
		} catch (SQLException ex) {
			System.out.print(ex.getMessage());
		}
	}
	
	public static void insertReserva(String DNI, String NumSerie, String Comienzo, String Fin, Connection conn) {
		try {
			String Querydb="USE "+bd+";";
			Statement stdb=conn.createStatement();
			stdb.executeUpdate(Querydb);
			String Query="INSERT INTO reserva(DNI, NumSerie, Comienzo, Fin) VALUES(\""+DNI+"\",\""+NumSerie+"\",\""+Comienzo+"\",\""+Fin+"\");";
			//System.out.print(Query);
			stdb.executeUpdate(Query);
		} catch (SQLException ex) {
			System.out.print(ex.getMessage());
		}
	}
	
	public static void main(String[] args) {
		Connection conn = null;
		String DNIs[] = {"A3849645","B1230645","C3727312","D2645689","F7864321" };
		String NumSerie[] = { "0070", "0074", "0071", "0072", "0079"};
		String Comienzo[] = { "2020-03-12", "2020-03-12", "2020-03-12", "2020-03-12", "2020-03-12" };
		String Fin[] = { "2020-03-13", "2020-03-13", "2020-03-13", "2020-03-13", "2020-03-13" };
		String NombreEquipo[] = { "Burguir", "MacDounalds", "Hielera", "JAJAJA", "Hoja" };
		String NombreFacu[] = { "Burguir", "MacDounalds", "Hielera", "JAJAJA", "Hoja" };
		String NomApels[] = { "Paco Sanz", "Arturo Barrionuevo", "Antoni Maduell", "Lluisa Vidiella", "Wenos Dias" };
		int idFacu[]={1,2,4,7,8};
		
		try {
			String sURL = url;
			conn = DriverManager.getConnection(sURL,login,password);
			
			if (conn != null) {
				System.out.println("-Abierta base de datos " + url + " - Ok");
				
				//we insert 5 new pieces
				for (int i=0;i<NombreFacu.length;i++) {
					insertFacultad(NombreFacu[i],conn);
				}
				for (int i=0;i<DNIs.length;i++) {
					insertInvestigadores(DNIs[i],NomApels[i],idFacu[i],conn);
				}
				for (int i=0;i<NumSerie.length;i++) {
					System.out.println(NumSerie[i]);
					insertEquipo(NumSerie[i],NombreEquipo[i],idFacu[i],conn);
				}
				for (int i=0;i<Comienzo.length;i++) {
					insertReserva(DNIs[i],NumSerie[i],Comienzo[i],Fin[i],conn);
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
