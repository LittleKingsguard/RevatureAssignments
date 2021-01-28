import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Account {
    private int id;
    private ArrayList<Transaction> history;
    private ArrayList<Customer> holders;
    private float balance;
    private int status;

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStatusString(){
        switch (status){
            case 1:
                return "Active";
            case 2:
                return "Pending";
            case 3:
                return "Rejected";
            default:
                return "Closed";
        }

    }

    public void controller(Customer user){
        Scanner scan = new Scanner(System.in);
        boolean isEmployee = Menu.getU() instanceof Employee;
        System.out.print("Now working in account no. " + id);
        System.out.println(" as " + Menu.getU().getFullName());
        System.out.println("This account is " + getStatusString());
        System.out.println();
        System.out.println("The following people have account access:");
        for (Customer c : holders) {
            System.out.println(c.getFullName());
        }

        int selection = 0;
        while(selection != 5) {
            ArrayList<Transaction> in = new ArrayList<Transaction>();
            ArrayList<Transaction> out = new ArrayList<Transaction>();
            ArrayList<Transaction> pending = new ArrayList<Transaction>();
            for (Transaction trans : history) {
                if (trans == null) {
                    continue;
                }
                if (trans.getApproved() == 1 && trans.getDestination() == id) {
                    pending.add(trans);
                }
                if (trans.getApproved() != 2) {
                    continue;
                }
                if (trans.getOrigin() == this.id) {
                    out.add(trans);
                }
                if (trans.getDestination() == this.id) {
                    in.add(trans);
                }
            }
            int both = Math.min(in.size(), out.size());
            int excess = Math.max(in.size(), out.size()) - both;
            boolean inLarger = in.size() >= out.size();

            System.out.println();
            System.out.println(Pad.rightPad(40, "Recent Transactions:", ' ') + Pad.leftPad(40, "Current Balance: $" + balance, ' '));
            System.out.println(Pad.rightPad(80, "", '-'));
            System.out.println(Pad.rightPad(39, "Withdrawals", ' ') + "||" + Pad.leftPad(39, "Deposits", ' '));

            for (int i = 0; i < both; i++) {
                System.out.println(
                        Pad.rightPad(39, out.get(i).toString(id), ' ') +
                                "||" +
                                Pad.leftPad(39, in.get(i).toString(), ' '));
            }
            if (inLarger) {
                for (int i = 0; i < excess; i++) {
                    System.out.println(
                            Pad.rightPad(39, "", ' ') +
                                    "||" +
                                    Pad.leftPad(39, in.get(both + i).toString(id), ' '));
                }
            } else {
                for (int i = 0; i < excess; i++) {
                    System.out.println(
                            Pad.rightPad(39, out.get(both + i).toString(id), ' ') +
                                    "||" +
                                    Pad.leftPad(39, "", ' '));
                }
            }

            System.out.println();
            System.out.println();
            System.out.println("Transactions pending approval: " + pending.size());
            for (Transaction t : pending) {
                System.out.println(Pad.leftPad(40, t.toString(id), ' '));
            }
            System.out.println();
            System.out.println();

            if (isEmployee){
                SafeScan.scrollPause(scan);
                selection = 5;
            }
            else {
                System.out.println(Pad.balancedTable(20, "Option:", "Enter:", ' ', ""));
                System.out.println(Pad.balancedTable(20, "Withdraw", "1", ' ', ""));
                System.out.println(Pad.balancedTable(20, "Deposit", "2", ' ', ""));
                System.out.println(Pad.balancedTable(20, "Begin Transfer", "3", ' ', ""));
                System.out.println(Pad.balancedTable(20, "Handle Transfer", "4", ' ', ""));
                System.out.println(Pad.balancedTable(20, "Return", "5", ' ', ""));

                selection = SafeScan.getPosInt(scan, 5);
            }
            switch (selection) {
                case 1:
                    Transaction.creationController(id, 'W');
                    break;
                case 2:
                    Transaction.creationController(id, 'D');
                    break;
                case 3:
                    Transaction.creationController(id, 'T');
                    break;
                case 4:
                    this.transferProcessor(scan, pending);
                    break;
                default:
                    break;
            }
            Account refresh = AccountDAO.getDAO().read(this);
            this.balance = refresh.getBalance();
            this.history = refresh.getHistory();
        }
    }

    public void transferProcessor(Scanner scan, ArrayList<Transaction> pending){
        System.out.println(pending.size());
        if (pending.size() == 0){
            return;
        }
        int target = -1;
        while (target < 0 || target >= pending.size()) {
            System.out.println(Pad.balancedTable(20,"Transactions pending approval:", "Enter:", ' ', ""));
            int i = 0;
            for (Transaction t : pending) {
                i++;
                System.out.println(Pad.balancedTable(20, t.toString(id), Integer.toString(i), ' ', ""));

            }
            target = SafeScan.getPosInt(scan, i) - 1;
        }
        System.out.println("To approve transaction, enter y");
        System.out.println("To reject transaction, enter n");
        System.out.println("To cancel action, enter c");
        char selection = SafeScan.getChar(scan, new char[]{'y', 'n', 'c'});
        switch (selection){
            case 'y':
                pending.get(target).approve();
                break;
            case 'n':
                pending.get(target).reject();
                break;
            default:
                break;
        }
    }

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
        return "Account #" + id +
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
    public Account(ArrayList<Customer> holders, float balance, int status) {
        this.holders = holders;
        this.balance = balance;
        this.status = status;
    }


    public void approve(){
        this.setStatus(1);
        AccountDAO td = AccountDAO.getDAO();
        Account target = td.saveChanges(this);
        if (target == null){
            this.setStatus(3);
            System.out.println("Account denied.");
        }
        else {
            System.out.println("Account approved.");
        }
    }

    public void reject(){
        this.setStatus(3);
        AccountDAO td = AccountDAO.getDAO();
        Account target = td.saveChanges(this);
        if (target == null){
            this.setStatus(2);
            System.out.println("I'm afraid I can't do that, Dave.");
        }
        else {
            System.out.println("Account denied.");
        }
    }

}
