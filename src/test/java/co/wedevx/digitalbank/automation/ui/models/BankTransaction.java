package co.wedevx.digitalbank.automation.ui.models;

public class BankTransaction {
    //data table (feature file) needs to be transformed into a Java object in (models)
    private String data;
    private String category;
    private String description;
    private double amount;
    private double balance;

    public BankTransaction(String data, String category, String description, double amount, double balance) {
        this.data = data;
        this.category = category;
        this.description = description;
        this.amount = amount;
        this.balance = balance;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
