

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.scene.control.Alert;

public class InsertSql extends Thread{

	public String inset = "";
	String Reqest = "";
	String ip_bd = "";
	String port_bd = "";
	String name_bd = "";
	String name_table = "";
	String login = "";
	String password = "";
	String name_note = "";
	String data_note = "";
	String info_note = "";

	int Flag = 0;
	
	public void Load(String ip_bd, String port_bd, String name_bd, String name_table, String login, String password, String name_note, String data_note, String info_note) {
		//System.out.println(" Name " + name_note + "DATA " + data_note + "ID" + id_note );
		
	    this.ip_bd = ip_bd;
		this.port_bd = port_bd;
		this.name_bd = name_bd;
		this.name_table = name_table;
		this.login = login;
		this.password = password;
		this.name_note = name_note;
		this.data_note = data_note;
		this.info_note = info_note;
		inset = "";
		
	}
	
	@Override
	public void run(){
		Flag = 0;
		
		try{
			
			try{

				
				String url = "jdbc:postgresql://" + ip_bd + ":" + port_bd + "/" + name_bd;
		        Class.forName("org.postgresql.Driver");
		        Connection con = DriverManager.getConnection(url, login, password);
		        Statement stm = con.createStatement();
				
		        PreparedStatement stmt = con.prepareStatement("INSERT INTO notes (time_note, information, name_note) VALUES (?, ?, ?)");
		       
		        stmt.setString(1, data_note);
		        stmt.setString(2, info_note);
		        stmt.setString(3, name_note);
		        stmt.executeUpdate();
		        
		        Flag = 1;
		        
		        stm.close();
		        con.close();
		        
			}catch (SQLException e){

		        Flag = 2;
			}
			
		}catch(Exception e1){
			//System.out.println(e1.toString());

	        Flag = 2;
		}
		
		
	}

}
