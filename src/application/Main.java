package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			//instanciou um novo tipo loader, assim dá pra manipular a tela antes de carregar
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/MainView.fxml"));
			
			
			Parent parent = loader.load();			
			
			//cena principal
			Scene mainScene = new Scene(parent);
			
			//palco da cena
			primaryStage.setScene(mainScene);
			primaryStage.setTitle("Simple JavaFX application");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
