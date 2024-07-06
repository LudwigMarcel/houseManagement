package gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import gui.util.Alerts;
import gui.util.Constraints;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.entities.Expense;
import model.entities.Income;
import model.entities.Management;
import model.entities.enums.AuxDelete;

public class DeleteViewController implements Initializable {

	@FXML
	private Button btDelete;
	@FXML
	private Button btCancel;
	@FXML
	private TextField txtID;
	@FXML
	private MenuItem miExpense;
	@FXML
	private MenuItem miIncome;
	@FXML
	private MenuButton mbType;

	private Management management;

	@FXML
	public void onBtCancelAction() {
		Stage stage = (Stage) btCancel.getScene().getWindow();
		stage.close();
	}

	@FXML
	public void onBtDeleteAction(ActionEvent event) {
		Stage stage = (Stage) btDelete.getScene().getWindow();

		this.management = new Management();

		try {
			List<Expense> auxExpense = management.getExpenses();
			List<Income> auxIncome = management.getIncomes();

			AuxDelete deleteE = AuxDelete.valueOf(mbType.getText());
			AuxDelete deleteI = AuxDelete.valueOf(mbType.getText());

			Integer id = Integer.parseInt(txtID.getText());
			
			if (auxExpense.stream().anyMatch(expense -> expense.getId() == id)
					|| auxIncome.stream().anyMatch(income -> income.getId() == id)) {
				
				if (deleteE.toString().equals("Despesa")) {
					management.deleteExpense(id);
					stage.close();
				}
				if (deleteI.toString().equals("Entrada")) {
					management.deleteIncome(id);
					stage.close();
				}
				if (id.equals(null)) {
					throw new NumberFormatException();
				}
				if (deleteE.equals(null) || deleteI.equals(null)) {
					throw new IllegalArgumentException();
				}
			}
		} catch (NumberFormatException e) {
			Alerts.showAlert("Erro", "Informe uma tabela e/ou um ID válidos", null, AlertType.ERROR);
		} catch (NullPointerException e) {
			Alerts.showAlert("Erro", "Informe uma tabela e/ou um ID válidos", null, AlertType.ERROR);
		} catch (IllegalArgumentException e) {
			Alerts.showAlert("Erro", "Informe uma tabela e/ou um ID válidos", null, AlertType.ERROR);
		}
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		Constraints.setTextFieldInteger(txtID);

		// Adiciona expense/income ao MenuButton
		for (AuxDelete delete : AuxDelete.values()) {
			MenuItem item = new MenuItem(delete.name());
			item.setOnAction(event -> mbType.setText(delete.name()));
			mbType.getItems().add(item);
		}

	}
}
