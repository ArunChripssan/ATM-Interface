import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;;

public class AtmInterface {
    private Scanner scanner;
    private List<User> users;
    private User currentUser;

    AtmInterface() {
        scanner = new Scanner(System.in);
        users = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            users.add(new User("user" + i, "1111", 1000));
        }
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void start() {
        System.out.println("wellcome to our ATM");

        boolean loggedin = false;
        while (!loggedin) {
            System.out.println("Enter your User Id: ");
            String userId = scanner.nextLine().trim();

            System.out.println("Enter your User Pin: ");
            String userPin = scanner.nextLine().trim();

            for (User user : users) {
                if (user.getUserId().equals(userId) && user.getUserPin().equals(userPin)) {
                    currentUser = user;
                    loggedin = true;
                    break;
                }
            }
            if (!loggedin) {
                System.out.println("invalid");
            }

        }
        while (true) {
            Display();
            String userChoice = scanner.nextLine().trim();
            switch (userChoice) {
                case "1":
                    PerformDeposit();
                    break;
                case "2":
                    PerformWithdraw();
                    break;
                case "3":
                    PerformTransfer();
                    break;
                case "4":
                    AccBalance();
                    break;
                case "5":
                    transactionHistory();
                    break;
                case "6":
                    System.out.println("Thankyou for visiting our ATM");
                    return;
                default:
                    System.out.println("Invalid Choice");

            }
        }
    }

    public void Display() {
        System.out.println("1 : CASH DEPOSIT");
        System.out.println("2 : CASH WITHDRAW");
        System.out.println("3 : CASH TRANSFER");
        System.out.println("4 : CHECK BALANCE");
        System.out.println("5 : TRANSACTION HISTORY");
        System.out.println("6 : Quit");
    }

    public void PerformDeposit() {
        System.out.println("Enter the Amount of Deposit : ");
        double dAmount = scanner.nextDouble();
        currentUser.deposit(dAmount);
        System.out.println("Your amount was Deposited Successfully");
    }

    public void PerformWithdraw() {
        System.out.println("Enter the Amount of Withdraw : ");
        double dAmount = Double.parseDouble(scanner.nextLine().trim());
        if (currentUser.withdraw(dAmount)) {
            System.out.println("Your amount was Withdrawn Successfully");

        } else {
            System.out.println("Insufficient Amount");
        }

    }

    private void PerformTransfer() {
        System.out.print("Enter the receiver's user ID: ");
        String receiverID = scanner.nextLine().trim();

        User receiver = null;
        for (User user : users) {
            if (user.getUserId().equals(receiverID)) {
                receiver = user;
                break;
            }
        }

        if (receiver == null) {
            System.out.println("Receiver not found.");
            return;
        }

        System.out.print("Enter Amount of transfer : ");
        double amount = Double.parseDouble(scanner.nextLine().trim());

        currentUser.transfer(receiver, amount);
        System.out.println("Transfer successful");
    }

    public void AccBalance() {
        currentUser.getUserId();
        double balance = currentUser.getAccBalance();
        // for (double Checkbalance : balance) {
        System.out.println(balance);
        // }
    }

    private void transactionHistory() {
        System.out.println("Transaction History for " + currentUser.getUserId() + ":");
        List<String> history = currentUser.getTrannsactionHistory();
        for (String entry : history) {
            System.out.println(entry);
        }
    }

}
