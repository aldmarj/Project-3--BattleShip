package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("ChatRoomGUI.fxml"));
			
			BorderPane root = (BorderPane)loader.load();
			Scene scene = new Scene(root,1000,1000);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			Server server;
			server = new Server(8888,this);
			server.start();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/*StringBuilder sb = new StringBuilder();
	public void updateText(StringBuilder sb) {
		Controller.incomingText.setText(sb.toString());
		
	}*/
	
	public static void main(String[] args) {
		launch(args);
	}

	

	
}
