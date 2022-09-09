package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import application.Main;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import model.services.DepartmentService;
import model.services.SellerService;

public class MainViewController implements Initializable {

	@FXML
	private MenuItem menuItemSeller;

	@FXML
	private MenuItem menuItemDepartment;

	@FXML
	private MenuItem menuItemAbout;

	@FXML
	public void onMenuItemSellerAction() {
		loadView("/gui/SellerList.fxml", (SellerListController controller)->{
			controller.setSellerService(new SellerService());
			controller.updateTableView();
		});
	}

	@FXML
	public void onMenuItemDepartmentAction() {
		//a��o de inicializa��o do DepartmentListController, envia o controller da View como par�metro
		loadView("/gui/DepartmentList.fxml", (DepartmentListController controller)->{
			controller.setDepartmentService(new DepartmentService());
			controller.updateTableView();
		});
	}

	@FXML
	public void onMenuItemAboutAction() {
		loadView("/gui/About.fxml", x->{});
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// � executado quando a janela � instanciada
	}

	//synchronized garante que n�o vai ter interrup��o na execu��o do c�digo por ser multi thread
	//Consumer pq n�o vai precisar de retorno e � com um tipo gen�rico
	private synchronized <T> void loadView(String absoluteName, Consumer<T> initializingAction) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			VBox newVBox = loader.load();
			
			Scene mainScene = Main.getMainScene();
			
			//.getRoot() pega o elemento pai de todos da view
			//.getContent pega o que t� dentro do scrollPane
			VBox mainVBox = (VBox)((ScrollPane) mainScene.getRoot()).getContent();
			
			Node mainMenu = mainVBox.getChildren().get(0);
			
			/*precisa dar  o .clear() antes porque se n�o ele vai ficar adicionando conte�do a mais
			 toda vez que chamar esse m�todo. A� j� que deu clear, precisa adicionar o que j� estava antes*/
			mainVBox.getChildren().clear();			
			mainVBox.getChildren().add(mainMenu);
			mainVBox.getChildren().addAll(newVBox.getChildren());
			
			//vai retornar um controlador do tipo T (do tipo do initializingAction)
			T controller = loader.getController(); //executa a fun��o lambda passada como par�metro
			initializingAction.accept(controller);
						
		}catch(IOException e) {
			e.printStackTrace();
			Alerts.showAlert("IO Exception", "Error loading view", e.getMessage(), AlertType.ERROR);
		}
	}
	
	

}
