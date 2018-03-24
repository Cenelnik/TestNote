


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.scene.control.Alert;

public class SelectSql extends Thread {
	
	public String select = "";
	String Qest = "";
	String ip_bd = "";
	String port_bd = "";
	String name_bd = "";
	String name_table = "";
	String login = "";
	String password = "";
	
	ResultSet rs;
	Connection con;
	Statement stm;
	
	String ErrorSql = "";
	
	int Flag = 0;
	

	public void Load(String ip_bd, String port_bd, String name_bd, String name_table, String login, String password, String name_note, String data_note, String id_note) {
		//System.out.println(" Name " + name_note + "DATA " + data_note + "ID" + id_note );
		
		//Cli
		select = "";
		this.ip_bd = ip_bd;
		this.port_bd = port_bd;
		this.name_bd = name_bd;
		this.name_table = name_table;
		this.login = login;
		this.password = password;
		
		
		Qest = "";
		
		String where = " WHERE ";
		
        int i = 0;
        
        if(name_note.length() > 0){
        	if(i > 0){
        		
        		where = where + " AND name_note=" +  "'" + name_note +  "'";
	        	i++;
        	}else{
        		where = where + " name_note=" + "'" + name_note + "'";
            	i++;
        	}
        	
        	
        }
        
        if(data_note.length() > 0){
        	
        	if(i > 0){
        		
        		where = where + " AND time_note=" + "'" + data_note + "'";
	        	i++;
        	}else{
        		where = where + " time_note=" +  "'" +  data_note +  "'";
            	i++;
        	}
        	
        }
        
        if(id_note.length() > 0){
        	
        	if(i > 0){
        		where = where + " AND id=" + id_note;
	        	i++;
        	}else{
        		where = where + " id=" + id_note;
            	i++;
        	}
        	
        }
        
        if(i == 0){
        	Qest = "SELECT * FROM " +  name_table;
        }else{
        	Qest = "SELECT * FROM " +  name_table + where;
        }
        //System.out.println(Qest);
        
	}
	
	@Override
	public void run(){
		
		 Flag = 0;
   
		 try {
				
			 try {
				 
				
			        
				 String url = "jdbc:postgresql://" + ip_bd + ":" + port_bd + "/" + name_bd;	    		        
				 Class.forName("org.postgresql.Driver");	    		        
				 con = DriverManager.getConnection(url, login, password);   
				 stm = con.createStatement();
   
				 rs = stm.executeQuery(Qest);   
				 while (rs.next()) {    
					 select = " " + rs.getString("information") + " : "  + rs.getString("time_note") + " : " + rs.getString("id") + " : " + rs.getString("name_note");
			            
					 //System.out.println("Content:" + select);
			        
				 }
     
				 Flag = 1;
				 //System.out.println("SelectSql - DONE"); 
				 rs.close();
				 stm.close();
				 con.close();				 
				 
				 
				
			 }catch(SQLException e1){
				
				 con.close();
				 ErrorSql = " " + e1.getSQLState()  + " : "  + e1.toString() + " : " + e1.getErrorCode() + " : "  + e1.getMessage();
				 //System.out.println("ERROR SQL:" + select);
				 Flag = 2;
			 }		
		 
		   
		 } catch (Exception e) {
			
			select = " " + "Ошибка подключения, необходимо проверить настройки подулючения к БД"  + " : "   + " : "  + " : " ;
			Flag = 2;
		 }

	}

	
}
