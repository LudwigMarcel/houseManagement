package util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;

import model.entities.Expense;
import model.entities.Income;

public class JsonHandler {

    private static final String EXPENSES_FILE_PATH = "expenses.json";
    private static final String INCOMES_FILE_PATH = "incomes.json";
    private ObjectMapper mapper = new ObjectMapper();

    public void saveData(List<Expense> expenses, List<Income> incomes) {
        DataWrapper dataWrapper = new DataWrapper(expenses, incomes);
        try {
            mapper.writeValue(new File(EXPENSES_FILE_PATH), dataWrapper.getExpenses());
            mapper.writeValue(new File(INCOMES_FILE_PATH), dataWrapper.getIncomes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Expense> loadExpenses() {
        List<Expense> expenses = new ArrayList<>();
        if (new File(EXPENSES_FILE_PATH).exists()) {
            try {
                expenses = mapper.readValue(Files.readAllBytes(Paths.get(EXPENSES_FILE_PATH)),
                        mapper.getTypeFactory().constructCollectionType(List.class, Expense.class));
            } catch (MismatchedInputException e) {
                // File is empty, initialize as empty list
                expenses = new ArrayList<>();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return expenses;
    }

    public List<Income> loadIncomes() {
        List<Income> incomes = new ArrayList<>();
        if (new File(INCOMES_FILE_PATH).exists()) {
            try {
                incomes = mapper.readValue(Files.readAllBytes(Paths.get(INCOMES_FILE_PATH)),
                        mapper.getTypeFactory().constructCollectionType(List.class, Income.class));
            } catch (MismatchedInputException e) {
                // File is empty, initialize as empty list
                incomes = new ArrayList<>();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return incomes;
    }

    public static class DataWrapper {
        private List<Expense> expenses;
        private List<Income> incomes;

        public DataWrapper(List<Expense> expenses, List<Income> incomes) {
            this.expenses = expenses;
            this.incomes = incomes;
        }

        public List<Expense> getExpenses() {
            return expenses;
        }

        public void setExpenses(List<Expense> expenses) {
            this.expenses = expenses;
        }

        public List<Income> getIncomes() {
            return incomes;
        }

        public void setIncomes(List<Income> incomes) {
            this.incomes = incomes;
        }
    }
}
