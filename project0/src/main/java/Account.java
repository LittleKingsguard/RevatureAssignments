import java.util.ArrayList;
import java.util.Arrays;

public class Account {
    private int id;
    private ArrayList<Transaction> history;
    private ArrayList<Customer> holders;
    private float balance;
    private int status;

    public int getId() {
        return id;
    }

    public ArrayList<Transaction> getHistory() {
        return history;
    }

    public ArrayList<Customer> getHolders() {
        return holders;
    }

    public float getBalance() {
        return balance;
    }

    public int getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", history=" + history +
                ", holders=" + holders +
                ", balance=" + balance +
                ", status=" + status +
                '}';
    }

    public Account(int id, ArrayList<Transaction> history, ArrayList<Customer> holders, float balance, int status) {
        this.id = id;
        this.history = history;
        this.holders = holders;
        this.balance = balance;
        this.status = status;
    }

    public Account(int id, ArrayList<Customer> holders, float balance, int status) {
        this.id = id;
        this.holders = holders;
        this.balance = balance;
        this.status = status;
    }


}
