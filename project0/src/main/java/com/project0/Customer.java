package com.project0;

import java.util.ArrayList;
import java.util.Scanner;

public class Customer extends User {
    public Customer(String username, String firstName, String lastName) {
        super(username, firstName, lastName);
    }

    public void controller() {
        Scanner scan = new Scanner(System.in);
        AccountDAO ad = AccountDAO.getDAO();
        boolean isEmployee = Menu.getU() instanceof Employee;
        int select = 0;
        while(select != 1) {
            Pad.newScreen();
            ArrayList<Account> acc = ad.getAccounts(this);
            System.out.println("Logged in as: " + Menu.getU().getFullName());
            System.out.println();
            System.out.println(Pad.balancedTable(20, "Options:", "Enter:", ' ', ""));
            System.out.println(Pad.balancedTable(20, "Logout", "1", ' ', ""));
            System.out.println(Pad.balancedTable(20, "Apply for account", "2", ' ', ""));
            if (acc.size() > 0) {
                System.out.println(Pad.balancedTable(20, "View accounts", "3", ' ', ""));
                select = SafeScan.getPosInt(scan, 3);
            }
            else {
                select = SafeScan.getPosInt(scan, 2);
            }
            switch (select) {
                case 2:
                    if (isEmployee){
                        System.out.println("Employees do not have access to this feature.");
                        SafeScan.scrollPause(scan);
                        break;
                    }
                    Account a = applyAccount(scan);
                    if (a != null) {
                        ad.insertNew(a);
                    }
                    break;
                case 3:
                    ad.read(accessAccounts(scan, acc)).controller(this);
                    break;
                default:
                    break;

            }
        }
    }

    private Account accessAccounts(Scanner scan, ArrayList<Account> accounts){
        int target = -1;
        while (target < 0 || target >= accounts.size()) {
            System.out.println(Pad.leftPad(20, "Account ID:", ' ') +
                    Pad.leftPad(20, "Balance:", ' ') +
                    Pad.leftPad(20, "Status:", ' ') +
                    Pad.leftPad(20, "To Select:", ' '));
            System.out.println(Pad.leftPad(80, "", '-'));
            int i = 0;
            for (Account a : accounts) {
                i++;
                System.out.println(Pad.leftPad(20, Integer.toString(a.getId()), ' ')+
                                Pad.leftPad(20, Float.toString(a.getBalance()), ' ')+
                                Pad.leftPad(20, a.getStatusString(), ' ') +
                                Pad.leftPad(20, Integer.toString(i), ' '));
            }
            target = SafeScan.getPosInt(scan, i) - 1;
        }
        return accounts.get(target);
    }

    private Account applyAccount(Scanner scan){
        System.out.println("Enter starting balance: ");
        float balance = SafeScan.getPosFloat(scan);
        System.out.println("Confirm account application? (y/n):");
        char selection = SafeScan.getChar(scan, new char[]{'y', 'n'});
        if (selection == 'y') {
            ArrayList<Customer> self = new ArrayList<Customer>();
            self.add(this);
            return new Account(self, balance, 2);
        }
        else {
            return null;
        }
    }
}
