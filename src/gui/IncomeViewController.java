package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;

public class IncomeViewController implements Initializable {

	@FXML
	private MenuButton mbCategory;
	@FXML
	private TextField txtValue;
	@FXML
	private TextField txtInstalment;
	@FXML
	private DatePicker dpDueDate;
	@FXML
	private TextField txtDescription;

	@FXML
	private Button btSave;
	@FXML
	private Button btCancel;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

}
