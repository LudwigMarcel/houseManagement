package gui;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import gui.util.Utils;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.entities.Expense;
import model.entities.Income;
import model.entities.Management;

public class MainViewController implements Initializable {

	@FXML
	private MenuItem menuItemSave;
	@FXML
	private MenuItem menuItemAbout;

	@FXML
	private Button btNewExpense;
	@FXML
	private Button btNewIncome;
	@FXML
	private Button btUpdateTable;

	@FXML
	private Label labelSubTotalExpense;
	@FXML
	private Label labelSubTotalIncome;
	@FXML
	private Label labelTotal;

	@FXML
	private TableView<Expense> tvExpense;
	@FXML
	private TableColumn<Expense, Integer> tcID;
	@FXML
	private TableColumn<Expense, Integer> tcCategory;
	@FXML
	private TableColumn<Expense, Integer> tcTransactionType;
	@FXML
	private TableColumn<Expense, Double> tcValue;
	@FXML
	private TableColumn<Expense, Integer> tcInstallment;
	@FXML
	private TableColumn<Expense, Date> tcDate;
	@FXML
	private TableColumn<Expense, String> tcDescription;

	@FXML
	private TableView<Income> tvIncome;
	@FXML
	private TableColumn<Income, Integer> tcIncomeID;
	@FXML
	private TableColumn<Income, Integer> tcIncomeCategory;
	@FXML
	private TableColumn<Income, Double> tcIncomeValue;
	@FXML
	private TableColumn<Income, Integer> tcIncomeInstallment;
	@FXML
	private TableColumn<Income, Date> tcIncomeDate;
	@FXML
	private TableColumn<Income, String> tcIncomeDescription;

	private ObservableList<Expense> obsExpense;
	private ObservableList<Income> obsIncome;
	private Management management;

	@FXML
	public void onMenuItemSaveAction() {

	}

	@FXML
	public void onMenuItemAboutAction() {
		try {
			File filePath = new File("/gui/about.pdf");

			Desktop.getDesktop().open(filePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void onBtNewExpenseAction(ActionEvent event) {
		Stage parentStage = Utils.currentStage(event);
		createDialogForm("/gui/ExpenseView.fxml", parentStage);
	}

	@FXML
	public void onBtNewIncomeAction(ActionEvent event) {
		Stage parentStage = Utils.currentStage(event);
		createDialogForm("/gui/IncomeView.fxml", parentStage);
	}

	@FXML
	public void onBtUpdateTableAction() {
		Management management = new Management();
		management.getExpenses();
		management.getIncomes();
	}

	private void createDialogForm(String absoluteName, Stage parentStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Pane pane = loader.load();
			
			Stage dialogStage = new Stage();
			
			dialogStage.setTitle("Nova Despesa");
			dialogStage.setScene(new Scene(pane));
			dialogStage.setResizable(false);
			dialogStage.initOwner(parentStage);
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.showAndWait();
		}
		catch(IOException e) {
			
		}
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		this.management = new Management();

	}

}
