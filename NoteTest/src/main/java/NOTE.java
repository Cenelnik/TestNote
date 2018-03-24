

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class NOTE extends Application {

	public  Stage thisStage;
	
	private int counter = 0;

	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage primaryStage) {
		
		try {
	
			thisStage = primaryStage;
			
			Parent root = FXMLLoader.load(getClass().getResource("scence.fxml"));
			primaryStage.setTitle("NodePad+++");
			primaryStage.setScene(new Scene(root));
			primaryStage.show();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

}

