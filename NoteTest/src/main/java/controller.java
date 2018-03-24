

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class controller implements Initializable {
	
	@FXML
	private Button clear;
	
	@FXML
	private Button button_save_settings;
	
	@FXML
	private Button save;
	
	@FXML
	private Button open;
	
	@FXML
	private Button delete;
	
	@FXML
	private TextArea Area;
	
	@FXML
	private Label DateText;
	
	@FXML
	private TextField NameText;
	
	@FXML
	private Label IdText;
	
	@FXML
	private TextField ip_data_base;
	
	@FXML
	private TextField port_data_base;
	
	@FXML
	private TextField name_data_base;
	
	@FXML
	private TextField name_table_data_base;
	
	@FXML
	private TextField login_data_base;
	
	@FXML
	private TextField password_data_base;
	
	@FXML
	private TextField data_file;
	
	@FXML
	private TextField name_file;
	
	@FXML
	private TextField id_file;
	
	private String IdFile = "";
	private String NameFile = "";
	private String DataFile = "";
	
	private String LoginDataBase = "";
	private String PasswordDataBase = "";
	private String NameDataBase = "";
	private String NameTableDataBase = "";
	private String IpDataBase = "";
	private String PortDataBase = "";
	
	Alert alert = new Alert(Alert.AlertType.INFORMATION);
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		 
	      
	}
	
	public void ClearAction(ActionEvent event){
		Area.setText("");
	}
	
	
	
	
	public void Save_settings_file(ActionEvent event){
		int test = 0;
		alert.setTitle("Information");
	    alert.setHeaderText(null);
		
		if (ip_data_base.getText().length() > 0){
			test++;
			
		}else{
			alert.setContentText("Поле IP пустое, заполенно значением по умолчанию");
		    alert.showAndWait();
			ip_data_base.setText("127.0.0.1");
		}
		
		if (port_data_base.getText().length() > 0){
			test++;			
		}else{
			alert.setContentText("Поле ПОРТ пустое, заполенно значением по умолчанию");
		    alert.showAndWait();
			port_data_base.setText("5432");
		}

		
		if (name_data_base.getText().length() > 0){
			test++;			
		}else{
			alert.setContentText("Поле ИМЯ БД пустое, заполенно значением по умолчанию");
		    alert.showAndWait();
			name_data_base.setText("test_writer");
		}
		
		if (name_table_data_base.getText().length() > 0){
			test++;
		}else{
			alert.setContentText("Поле ИМЯ ТАБЛИЦЫ пустое, заполенно значением по умолчанию");
		    alert.showAndWait();
			name_table_data_base.setText("notes");
		}

		if (login_data_base.getText().length() > 0){
			test++;	
		}else{
			
		    alert.setContentText("Поле ЛОГИН пустое, заполенно значением по умолчанию");
		    alert.showAndWait();
			login_data_base.setText("postgres");
		}
		
		
		if (password_data_base.getText().length() > 0){
			test++;
			
		}else{
			alert.setContentText("Поле ПАРОЛЬ пустое, заполенно значением по умолчанию");
		    alert.showAndWait();
			password_data_base.setText("125125");
		}

		
		save.setDisable(false);
		open.setDisable(false);
		delete.setDisable(false);
		
		//System.out.println("test = " + test);
		
	}

	public void SaveAction(ActionEvent event){
		
		Date date_now = new Date();
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		String date = df.format(date_now);
		DateText.setText(" : " + date);
		
		
		InsertSql is = new InsertSql();
		is.Load(ip_data_base.getText(), port_data_base.getText(), name_data_base.getText(), name_table_data_base.getText(), login_data_base.getText(), password_data_base.getText(), NameText.getText(),date, Area.getText() );
		Thread t_insert = new Thread(is);
		t_insert.start();
		
		while(is.Flag == 0){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(is.Flag == 1){
			alert.setContentText("Запрос на INSERT выполнен успешно");
		    alert.showAndWait();
			
		}else{
			alert.setContentText(" Ошибка запроса ");
		    alert.showAndWait();
		}
	    
	}
	
	public void OpenAction(ActionEvent event){


		String open_node = "";
		
		//System.out.println(" Данные " + name_file.getText()+ " " +data_file.getText()+ "  " + id_file.getText());
		
		SelectSql ss = new SelectSql();
		ss.Load(ip_data_base.getText(), port_data_base.getText(),  name_data_base.getText(), name_table_data_base.getText(), login_data_base.getText(), password_data_base.getText(), name_file.getText(), data_file.getText(), id_file.getText());
		Thread t_select = new Thread(ss);
		t_select.start();
		
		while(ss.Flag == 0){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		open_node = ss.select;
		//System.out.println("Запрос на SELECT" + open_node);
		
		
		
		Area.setText("");
		
		//System.out.println(open_node);
		
		if (open_node.length() > 0) {
			
			if(open_node.split(":")[0].length() > 0){
				Area.setText(open_node.split(":")[0]);
			}else{
				Area.setText(" NULL ");
			}
			
			if(open_node.split(":")[2].compareTo("  ") != 0){
				IdText.setText(open_node.split(":")[2]);
			}else{
				IdText.setText(" UNKNOW ");
			}
			
			if(open_node.split(":")[1].compareTo("  ") != 0){
				DateText.setText(open_node.split(":")[1]);
			}else{
				DateText.setText(" UNKNOW ");
			}
			
			if(open_node.split(":")[3].compareTo(" ") != 0){
				NameText.setText(open_node.split(":")[3]);
			}else{
				NameText.setText(" UNKNOW ");
			}
			
			
			open_node = "";
		}else{
			Area.setText(" Note don't found");
			NameText.setText(" UNKNOW ");
			DateText.setText(" UNKNOW ");
			IdText.setText(" UNKNOW ");
			open_node = "";
		}
		
		
		
		if(ss.Flag == 1){
			alert.setContentText("Запрос на SELECT выполнен успешно");
		    alert.showAndWait();
			
		}else{
			alert.setContentText(" Ошибка запроса ");
		    alert.showAndWait();
		}
		
		
	}
	
	
	public void DeleteAction(ActionEvent event){
		//Area.setText("sdbdfsb");
		
		DeleteSql ds = new DeleteSql();
		ds.Load(ip_data_base.getText(), port_data_base.getText(), name_data_base.getText(), name_table_data_base.getText(), login_data_base.getText(), password_data_base.getText(), name_file.getText(),data_file.getText(), id_file.getText() );

		Thread t_delete = new Thread(ds);
		t_delete.start();

		while(ds.Flag == 0){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(ds.Flag == 1){
			alert.setContentText("Запрос на DELETE выполнен успешно");
		    alert.showAndWait();
			
		}else{
			alert.setContentText(" Ошибка запроса ");
		    alert.showAndWait();
		}
	    
	}

}
