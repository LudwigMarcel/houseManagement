package gui;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

import application.Main;
import gui.util.Alerts;
import gui.util.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
	private MenuItem menuItemEditExpense;
	@FXML
	private MenuItem menuItemEditIncome;
	@FXML
	private MenuItem menuItemDelete;

	@FXML
	private Button btNewExpense;
	@FXML
	private Button btNewIncome;
	@FXML
	private Button btUpdateTable;
	@FXML
	private Button btUpdateTableByDate;
	@FXML
	private DatePicker dpCalendar;

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
		management.saveData();
	}

	@FXML
	public void onMenuItemDeleteAction(ActionEvent event) {
		Stage parentStage = Utils.currentStageSource(event);
		createDialogForm("/gui/DeleteView.fxml", parentStage);

	}

	@FXML
	public void onMenuItemEditExpenseAction(ActionEvent event) {
		Stage parentStage = Utils.currentStageSource(event);
		createDialogForm("/gui/EditViewExpense.fxml", parentStage);
	}

	@FXML
	public void onMenuItemEditIncomeAction(ActionEvent event) {
		Stage parentStage = Utils.currentStageSource(event);
		createDialogForm("/gui/EditViewIncome.fxml", parentStage);
	}

	@FXML
	public void onMenuItemAboutAction() {
		try {
			File filePath = new File("about.pdf");

			Desktop.getDesktop().open(filePath);
		} catch (IOException e) {
			Alerts.showAlert("Arquivo inexistente", "Arquivo inexistente", null, AlertType.ERROR);
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
		this.management = new Management();
		labelShowValue(LocalDate.now());
		updateTableView();
	}

	@FXML
	public void onUpdateTableByDate() {
		try {
			this.management = new Management();
			LocalDate dateFilter = dpCalendar.getValue();
			labelShowValue(dateFilter);
			obsExpense = FXCollections.observableArrayList(management.getExpenseByMonth(dateFilter));
			obsIncome = FXCollections.observableArrayList(management.getIncomeByMonth(dateFilter));

			tvExpense.setItems(obsExpense);
			tvIncome.setItems(obsIncome);
		} catch (NullPointerException e) {
			Alerts.showAlert("Data Inválida", "Favor informe um data válida", null, AlertType.ERROR);
		}

	}

	public void labelShowValue(LocalDate date) {
		NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();

		labelSubTotalExpense.setText(currencyFormat.format(management.getSubTotalExpense(date)));
		labelSubTotalIncome.setText(currencyFormat.format(management.getSubTotalIncome(date)));
		labelTotal.setText(currencyFormat.format(management.getTotal(date)));
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
		} catch (IOException e) {
			Alerts.showAlert("IO Exception", "Error loading view", e.getMessage(), AlertType.ERROR);
		}
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		this.management = new Management();
		labelShowValue(LocalDate.now());
		updateTableView();
		initializeNodes();

	}

	public void updateTableView() {
		obsExpense = FXCollections.observableArrayList(management.getExpenseByMonth());
		obsIncome = FXCollections.observableArrayList(management.getIncomeByMonth());

		tvExpense.setItems(obsExpense);
		tvIncome.setItems(obsIncome);
	}

	private void initializeNodes() {
		tcID.setCellValueFactory(new PropertyValueFactory<>("id"));
		tcCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
		tcTransactionType.setCellValueFactory(new PropertyValueFactory<>("transactionType"));
		tcValue.setCellValueFactory(new PropertyValueFactory<>("value"));
		tcInstallment.setCellValueFactory(new PropertyValueFactory<>("installment"));
		tcDate.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
		tcDescription.setCellValueFactory(new PropertyValueFactory<>("description"));

		tcIncomeID.setCellValueFactory(new PropertyValueFactory<>("id"));
		tcIncomeCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
		tcIncomeValue.setCellValueFactory(new PropertyValueFactory<>("value"));
		tcIncomeInstallment.setCellValueFactory(new PropertyValueFactory<>("installment"));
		tcIncomeDate.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
		tcIncomeDescription.setCellValueFactory(new PropertyValueFactory<>("description"));

		// refer funcionalidade, nao funciona...
		if (Main.getMainScene() != null) {
			Stage stage = (Stage) Main.getMainScene().getWindow();
			if (stage != null) {
				tvExpense.prefHeightProperty().bind(stage.heightProperty());
				tvIncome.prefHeightProperty().bind(stage.heightProperty());
			}
		}
	}
}
