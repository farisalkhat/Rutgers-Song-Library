//Code by Faris Al-khatahtbeh(fa301) and Miguel Macaoay (mtm236)

package songLib.app;
	
import java.io.IOException;

import songLib.view.songLibController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;



public class SongLib extends Application {
	
	
	@Override
	public void start(Stage primaryStage) 
	throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/songLib/view/songLibView.fxml"));
		VBox root = (VBox)loader.load();
		
		//GridPane root = (GridPane)loader.load();
		
		songLibController listController = loader.getController();
		listController.start(primaryStage);
		
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Offlaner's Jukebox");
		primaryStage.setResizable(false);  
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);

	}
}




