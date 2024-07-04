package model.entities;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import util.JsonHandler;

public class Management {

	private List<Expense> expenses;
	private List<Income> incomes;
	private JsonHandler jsonHandler;

	public Management() {
		this.expenses = new ArrayList<>();
		this.incomes = new ArrayList<>();
		this.jsonHandler = new JsonHandler();
		loadFromJson();
	}

	// Create Methods
	public void addExpense(Expense expense) {
		if (expense.getInstallment() > 1) {
			LocalDate dueDate = expense.getDueDate();
			for (int i = 0; i < expense.getInstallment(); i++) {
				expenses.add(new Expense(expense.getCategory(), expense.getTransactionType(), expense.getValue(),
						expense.getInstallment(), dueDate, expense.getDescription()));
				dueDate = dueDate.plusMonths(1);
			}
		} else {
			expenses.add(expense);
		}
		saveToJson();
	}

	public void addIncome(Income income) {
		if (income.getInstallment() > 1) {
			LocalDate dueDate = income.getDueDate();
			for (int i = 0; i < income.getInstallment(); i++) {
				incomes.add(new Income(income.getCategory(), income.getValue(), income.getInstallment(), dueDate,
						income.getDescription()));
				dueDate = dueDate.plusMonths(1);
			}
		} else {
			incomes.add(income);
		}
		saveToJson();
	}

	// Read Methods
	public Double getSubTotalExpense() {
		return getSubTotalExpense(LocalDate.now());
	}

	public Double getSubTotalExpense(LocalDate date) {
		Double subTotal = 0.0;
		int year = date.getYear();
		Month month = date.getMonth();
		for (Expense expense : expenses) {
			LocalDate dueDate = expense.getDueDate();
			if (dueDate.getYear() == year && dueDate.getMonth() == month) {
				subTotal += expense.getValue();
			}
		}
		return subTotal;
	}

	public Double getSubTotalExpenseYear() {
		return getSubTotalExpenseYear(LocalDate.now());
	}

	public Double getSubTotalExpenseYear(LocalDate date) {
		Double subTotal = 0.0;
		int year = date.getYear();
		for (Expense expense : expenses) {
			LocalDate dueDate = expense.getDueDate();
			if (dueDate.getYear() == year) {
				subTotal += expense.getValue();
			}
		}
		return subTotal;
	}

	public Double getSubTotalIncome() {
		return getSubTotalIncome(LocalDate.now());
	}

	public Double getSubTotalIncome(LocalDate date) {
		Double subTotal = 0.0;
		int year = date.getYear();
		Month month = date.getMonth();
		for (Income income : incomes) {
			LocalDate dueDate = income.getDueDate();
			if (dueDate.getYear() == year && dueDate.getMonth() == month) {
				subTotal += income.getValue();
			}
		}
		return subTotal;
	}

	public Double getSubTotalIncomeYear() {
		return getSubTotalIncomeYear(LocalDate.now());
	}

	public Double getSubTotalIncomeYear(LocalDate date) {
		Double subTotal = 0.0;
		int year = date.getYear();
		for (Income income : incomes) {
			LocalDate dueDate = income.getDueDate();
			if (dueDate.getYear() == year) {
				subTotal += income.getValue();
			}
		}
		return subTotal;
	}

	public Double getTotal() {
		LocalDate date = LocalDate.now();
		double subTotalExpense = getSubTotalExpense(date);
		double subTotalIncome = getSubTotalIncome(date);
		return subTotalIncome - subTotalExpense;
	}

	public Double getTotal(LocalDate date) {
		Double subTotalExpense = getSubTotalExpense(date);
		Double subTotalIncome = getSubTotalIncome(date);
		return subTotalIncome - subTotalExpense;
	}

	public Double getTotalYear() {
		LocalDate date = LocalDate.now();
		double subTotalExpense = getSubTotalExpenseYear(date);
		double subTotalIncome = getSubTotalIncomeYear(date);
		return subTotalIncome - subTotalExpense;
	}

	public Double getTotalYear(LocalDate date) {
		Double subTotalExpense = getSubTotalExpenseYear(date);
		Double subTotalIncome = getSubTotalIncomeYear(date);
		return subTotalIncome - subTotalExpense;
	}

	public List<Expense> getExpenses() {
		return expenses;
	}

	public List<Income> getIncomes() {
		return incomes;
	}

	public List<Expense> getExpensesByMonth(){
		return getExpenseByMonth(LocalDate.now());
	}
	
	public List<Expense> getExpenseByMonth(LocalDate date) {
		List<Expense> aux = new ArrayList<>();
		for (Expense expense : expenses) {

			if (expense.getDueDate().getYear() == date.getYear()
					&& expense.getDueDate().getMonthValue() == date.getMonthValue()) {
				aux.add(expense);
			}
		}
		return aux;
	}

	public List<Expense> getExpenseByCategory(Integer category, LocalDate date) {
		List<Expense> monthlyExpenses = getExpenseByMonth(date);
		List<Expense> aux = new ArrayList<>();
		for (Expense expense : monthlyExpenses) {
			if (expense.getCategory().equals(category)) {
				aux.add(expense);
			}
		}
		return aux;
	}

	public List<Income> getIncomeByMonth(){
		return getIncomeByMonth(LocalDate.now());
	}
	
	public List<Income> getIncomeByMonth(LocalDate date) {
		List<Income> aux = new ArrayList<>();
		for (Income income : incomes) {
			if (income.getDueDate().getYear() == date.getYear()
					&& income.getDueDate().getMonthValue() == date.getMonthValue()) {
				aux.add(income);
			}
		}
		return aux;
	}

	public List<Income> getIncomByCategory(Integer category, LocalDate date) {
		List<Income> monthlyIncomes = getIncomeByMonth(date);
		List<Income> aux = new ArrayList<>();
		for (Income income : monthlyIncomes) {
			if (income.getCategory().equals(category)) {
				aux.add(income);
			}
		}
		return aux;
	}

	// Update Methods
	public void updateExpense(Integer id, Integer category, Integer transactionType, Double value, Integer installment,
			LocalDate dueDate, String description) {
		for (Expense expense : expenses) {
			if (expense.getId().equals(id)) {
				expense.setCategory(category);
				expense.setTransactionType(transactionType);
				expense.setValue(value);
				expense.setInstallment(installment);
				expense.setDueDate(dueDate);
				expense.setDescription(description);
				saveToJson();
				return;
			}
		}
	}

	public void updateExpense(Integer id, Integer category, Integer transactionType, Double value, LocalDate dueDate,
			String description) {
		for (Expense expense : expenses) {
			if (expense.getId().equals(id)) {
				expense.setCategory(category);
				expense.setTransactionType(transactionType);
				expense.setValue(value);
				expense.setDueDate(dueDate);
				expense.setDescription(description);
				saveToJson();
				return;
			}
		}
	}

	public void updateIncome(Integer id, Integer category, Double value, Integer installment, LocalDate dueDate,
			String description) {
		for (Income income : incomes) {
			if (income.getId().equals(id)) {
				income.setCategory(category);
				income.setValue(value);
				income.setInstallment(installment);
				income.setDueDate(dueDate);
				income.setDescription(description);
				saveToJson();
				return;
			}
		}
	}

	public void updateIncome(Integer id, Integer category, Double value, LocalDate dueDate, String description) {
		for (Income income : incomes) {
			if (income.getId().equals(id)) {
				income.setCategory(category);
				income.setValue(value);
				income.setDueDate(dueDate);
				income.setDescription(description);
				saveToJson();
				return;
			}
		}
	}

	// Delete Methods
	public void deleteExpense(Integer expenseID) {
		expenses.removeIf(expense -> expense.getId().equals(expenseID));
		saveToJson();
	}

	public void deleteIncome(Integer incomeID) {
		incomes.removeIf(income -> income.getId().equals(incomeID));
		saveToJson();
	}

	private void saveToJson() {
		jsonHandler.saveData(expenses, incomes);
	}

	private void loadFromJson() {
		List<Expense> loadedExpenses = jsonHandler.loadExpenses();
		List<Income> loadedIncomes = jsonHandler.loadIncomes();

		if (loadedExpenses != null) {
			expenses.addAll(loadedExpenses);
		}

		if (loadedIncomes != null) {
			incomes.addAll(loadedIncomes);
		}
	}

	public void saveData() {
		saveToJson();
	}

	public void loadData() {
		loadFromJson();
	}
}
