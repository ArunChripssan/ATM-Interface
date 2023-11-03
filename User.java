import java.util.ArrayList;
import java.util.List;

class User {

    private String userId;
    private String userPin;
    private double accBalance;
    private ArrayList<String> trannsactionHistory;

    public User(String userId, String userPin, double accBalance) {
        this.userId = userId;
        this.userPin = userPin;
        this.accBalance = accBalance;
        this.trannsactionHistory = new ArrayList<>();
    }

    public String getUserId() {
        return userId;
    }

    public String getUserPin() {
        return userPin;
    }

    public double getAccBalance() {
        return accBalance;
    }

    public List<String> getTrannsactionHistory() {
        return trannsactionHistory;
    }

    public void deposit(double amount) {
        accBalance += amount;
        trannsactionHistory.add("Deposited amount  : " + amount);
    }

    public boolean withdraw(double amount) {
        if (accBalance >= amount) {
            accBalance -= amount;
            trannsactionHistory.add("withdrawed amount : " + amount);
            return true;
        }
        return false;
    }

    public void transfer(User receiver, double amount) {
        if (withdraw(amount)) {
            deposit(amount);
            trannsactionHistory.add("Amount transferd to" + getUserId() + ":" + amount);
        }
    }

}