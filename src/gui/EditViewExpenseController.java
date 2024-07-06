package gui;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
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
import model.entities.Expense;
import model.entities.Management;
import model.entities.enums.Category;
import model.entities.enums.TransactionType;

public class EditViewExpenseController implements Initializable {

	@FXML
	private TextField txtId;
	@FXML
	private MenuButton mbCategory;
	@FXML
	private MenuButton mbType;
	@FXML
	private TextField txtValue;
	@FXML
	private TextField txtInstalment;
	@FXML
	private DatePicker dpDueDate;
	@FXML
	private TextField txtDescription;

	@FXML
	private MenuItem miFood;
	@FXML
	private MenuItem miCredit;

	@FXML
	private Button btSave;
	@FXML
	private Button btCancel;

	private Management management;

	@FXML
	public void onBtSaveAction() {
		try {
			Stage stage = (Stage) btSave.getScene().getWindow();

			// Converte as entradas do MenuButton para Category/TransactionType
			Category category = Category.valueOf(mbCategory.getText());
			TransactionType type = TransactionType.valueOf(mbType.getText());

			// Converte o texto do TextField para Double e Integer
			Integer id = Integer.parseInt(txtId.getText());
			Double value = Double.parseDouble(txtValue.getText());
			
			if(txtInstalment.getText() == null || txtInstalment.getText().isEmpty()) {
				txtInstalment.setText("1");
			}			
			Integer instalment = Integer.parseInt(txtInstalment.getText());

			// Converte o DatePicker para Date
			LocalDate dueDate = dpDueDate.getValue();
			
			//Atualiza a despesa com base no id informado, e verifica se é um id valido
			List<Expense> auxExpense = management.getExpenses();

			if (auxExpense.stream().anyMatch(expense -> expense.getId() == id)) {
				management.updateExpense(id, category, type, value, instalment, dueDate, txtDescription.getText());
			}

			stage.close();

		} catch (NumberFormatException e) {
			Alerts.showAlert("Valor inválido", "Insira um valor válido", null, AlertType.ERROR);
		} catch (IllegalArgumentException e) {
			Alerts.showAlert("Dados faltantes", "Verifique a ausencia de dados", null, AlertType.ERROR);
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

		// Adiciona tipos ao MenuButton
		for (TransactionType type : TransactionType.values()) { 
			MenuItem item = new MenuItem(type.name());
			item.setOnAction(event -> mbType.setText(type.name()));
			mbType.getItems().add(item);
		}

	}

	public void initializeNodes() {
		Constraints.setTextFieldDouble(txtValue);
		Constraints.setTextFieldInteger(txtInstalment);
		Constraints.setTextFieldInteger(txtId);
		Constraints.setTextFieldMaxLength(txtDescription, 100);
	}

}
