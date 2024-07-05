package model.entities;

import java.time.LocalDate;
import java.util.Objects;

import model.entities.enums.Category;
import model.entities.enums.TransactionType;

public class Expense {

	private static Integer lastID = 0;
	private Integer id;
	private Category category;
	private TransactionType transactionType;
	private Double value;
	private Integer installment = 1;
	private LocalDate dueDate;
	private String description;

	public Expense() {
	}

	// Credit
	public Expense(Category category, TransactionType transactionType, Double value, Integer installment, LocalDate dueDate,
			String description) {
		this.id = ++lastID;
		this.category = category;
		this.transactionType = transactionType;
		this.value = value;
		this.installment = installment;
		this.dueDate = dueDate;
		this.description = description;
	}

	// Debit
	public Expense(Category category, TransactionType transactionType, Double value, LocalDate dueDate, String description) {
		this.id = ++lastID;
		this.category = category;
		this.transactionType = transactionType;
		this.value = value;
		this.dueDate = dueDate;
		this.description = description;
	}

	public Integer getId() {
		return id;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public TransactionType getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public Integer getInstallment() {
		return installment;
	}

	public void setInstallment(Integer installment) {
		this.installment = installment;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Expense [id=" + id + ", category=" + category + ", transactionType=" + transactionType + ", value="
				+ value + ", installment=" + installment + ", dueDate=" + dueDate + ", description=" + description
				+ "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(category, description, dueDate, id, installment, transactionType, value);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Expense other = (Expense) obj;
		return Objects.equals(category, other.category) && Objects.equals(description, other.description)
				&& Objects.equals(dueDate, other.dueDate) && Objects.equals(id, other.id)
				&& Objects.equals(installment, other.installment)
				&& Objects.equals(transactionType, other.transactionType) && Objects.equals(value, other.value);
	}
}
