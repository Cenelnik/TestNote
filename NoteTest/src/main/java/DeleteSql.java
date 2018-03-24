

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.scene.control.Alert;

public class DeleteSql extends Thread{

	public String delete = "";
	String Qest = "";
	String ip_bd = "";
	String port_bd = "";
	String name_bd = "";
	String name_table = "";
	String login = "";
	String password = "";
	
	int Flag = 0;
	String Quest = "";
	
	public void Load(String ip_bd, String port_bd, String name_bd, String name_table, String login, String password, String name_note, String data_note, String id_note) {
		
		delete = "";
		this.ip_bd = ip_bd;
		this.port_bd = port_bd;
		this.name_bd = name_bd;
		this.name_table = name_table;
		this.login = login;
		this.password = password;
	    Quest = "";
	
	    String where = " WHERE ";
		
        int i = 0;
        
        if(name_note.length() > 0){
        	if(i > 0){
        		
        		where = where + " AND name_note=" +  "'" +  name_note +  "'";
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
        		where = where + " time_note=" +  "'" + data_note +  "'";
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
        	Quest = "DELETE FROM " + name_table ;
        }else{
        	Quest = "DELETE FROM " +  name_table + where;
        }
	    
	}
	
	public void run(){
		Flag = 0;
		
	try{
			
			try{
		        
		        String url = "jdbc:postgresql://" + ip_bd + ":" + port_bd + "/" + name_bd;
		        Class.forName("org.postgresql.Driver");
		        Connection con = DriverManager.getConnection(url, login, password);
		        Statement stm = con.createStatement();
		        stm.executeUpdate(Quest);
		        stm.close();
		        con.close();
		        Flag = 1;
		       // System.out.println(Quest);
			}catch (SQLException e){
				
				//System.out.println("ERROR SQL " + e.toString());
				Flag = 2;
			}
			
		}catch(Exception e1){
			//System.out.println(e1.toString());
			Flag = 2;
		}
		
	}
	    
/*
		
	
		
		
	*/
		
		
}
