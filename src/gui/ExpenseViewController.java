/*
 * modificar todas as datas to LocalDate
 */

package gui;

import java.net.URL;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

public class ExpenseViewController implements Initializable {

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

			// Converte as entradas do MenuButton para Integer
			Category category = Category.valueOf(mbCategory.getText());
			TransactionType type = TransactionType.valueOf(mbType.getText());

			// Converte o texto do TextField para Double e Integer
			Double value = Double.parseDouble(txtValue.getText());
			Integer instalment = Integer.parseInt(txtInstalment.getText());

			// Converte o DatePicker para Date
			LocalDate localDate = dpDueDate.getValue();

			Date dueDate = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

			// Adiciona a despesa utilizando o método management.addExpense
			management.addExpense(new Expense(category.getCode(), type.getCode(), value, instalment, dueDate,
					txtDescription.getText()));

			stage.close();
		} catch (NumberFormatException e) {
			System.err.println("Erro ao converter número: " + e.getMessage());
			// Adicione uma mensagem de erro ou notificação para o usuário
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

		// Adiciona categorias ao MenuButton
		for (Category category : Category.values()) {
			MenuItem item = new MenuItem(category.name());
			item.setOnAction(event -> mbCategory.setText(category.name()));
			mbCategory.getItems().add(item);
		}

		// Adiciona tipos ao MenuButton
		for (TransactionType type : TransactionType.values()) { // Supondo que você tenha um enum Type
			MenuItem item = new MenuItem(type.name());
			item.setOnAction(event -> mbType.setText(type.name()));
			mbType.getItems().add(item);
		}

	}

}
