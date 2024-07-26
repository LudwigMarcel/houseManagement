package gui.util;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class Utils {
	public static Stage currentStage(ActionEvent event) {
		return (Stage) ((Node) event.getSource()).getScene().getWindow();
	}

	public static Stage currentStageSource(ActionEvent event) {
	    MenuItem menuItem = (MenuItem) event.getSource();
	    return (Stage) menuItem.getParentPopup().getOwnerWindow();
	}

}
