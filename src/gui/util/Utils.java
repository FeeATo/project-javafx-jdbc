package gui.util;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

public class Utils {
	public static Stage currentStage(ActionEvent event) {
		//window é superclasse de stage
		//Node->Scene->Stage->Window
		//pega o Stage atual com base no evento passado
		return (Stage)((Node)event.getSource()).getScene().getWindow();
	}
}
