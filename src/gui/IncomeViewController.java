/*
 * tratamento dos dados de imput
 * atualizar tabela ao salvar nova despesa
 */
package gui;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import gui.util.Alerts;
import gui.util.Constraints;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.entities.Income;
import model.entities.Management;
import model.entities.enums.Category;

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

	private Management management;

	@FXML
	public void onBtSaveAction() {
		try {
			Stage stage = (Stage) btSave.getScene().getWindow();

			// Converte as entradas do MenuButton para Integer
			Category category = Category.valueOf(mbCategory.getText());

			// Converte o texto do TextField para Double e Integer
			Double value = Double.parseDouble(txtValue.getText());

			if (txtInstalment.getText() == null || txtInstalment.getText().isEmpty()) {
				txtInstalment.setText("1");
			}
			Integer instalment = Integer.parseInt(txtInstalment.getText());

			Integer id = management.getLastIdIncome();

			// Converte o DatePicker para Date
			LocalDate dueDate = dpDueDate.getValue();

			// Adiciona a despesa utilizando o método management.addExpense
			management.addIncome(new Income(id, category, value, instalment, dueDate, txtDescription.getText()));

			stage.close();

		} catch (NumberFormatException e) {
			Alerts.showAlert("Valor inválido", "Insira um valor válido '0.00'", null, AlertType.ERROR);
		}
	}

	@FXML
	public void onBtCancelAction() {
		Stage stage = (Stage) btCancel.getScene().getWindow();
		stage.close();
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		this.management = new Management();
		initializeNodes();

		// Adiciona categorias ao MenuButton
		for (Category category : Category.values()) {
			MenuItem item = new MenuItem(category.name());
			item.setOnAction(event -> mbCategory.setText(category.name()));
			mbCategory.getItems().add(item);
		}
	}

	public void initializeNodes() {
		Constraints.setTextFieldDouble(txtValue);
		Constraints.setTextFieldInteger(txtInstalment);
		Constraints.setTextFieldMaxLength(txtDescription, 100);
	}
}
