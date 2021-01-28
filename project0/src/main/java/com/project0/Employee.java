package com.project0;

import java.util.ArrayList;
import java.util.Scanner;

public class Employee extends User{
    public Employee(String username, String firstName, String lastName) {
        super(username, firstName, lastName);
    }

    public void controller(){
        Scanner scan = new Scanner(System.in);
        AccountDAO ad = AccountDAO.getDAO();
        int select = 0;
        while(select != 1) {
            Pad.newScreen();
            ArrayList<Account> acc = ad.getPending();
            System.out.println("Logged in as: " + Menu.getU().getFullName());
            System.out.println();
            System.out.println(Pad.balancedTable(20, "Options:", "Enter:", ' ', ""));
            System.out.println(Pad.balancedTable(20, "Logout", "1", ' ', ""));
            System.out.println(Pad.balancedTable(20, "View transactions", "2", ' ', ""));
            System.out.println(Pad.balancedTable(20, "View customers", "3", ' ', ""));
            if (acc.size() > 0) {
                System.out.println(Pad.balancedTable(20, "Process pending accounts", "4", ' ', ""));
                select = SafeScan.getPosInt(scan, 4);
            }
            else {
                select = SafeScan.getPosInt(scan, 3);
            }
            switch (select) {
                case 2:
                    viewTransactions();
                    SafeScan.scrollPause(scan);
                    break;
                case 3:
                    viewCustomers(scan);
                    break;
                case 4:
                    processAccounts(scan, acc);
                    break;
                default:
                    break;

            }
        }
    }

    private void viewTransactions(){
        TransactionDAO td = TransactionDAO.getDAO();
        ArrayList<Transaction> trans = td.readAll();
        for (Transaction t : trans) {
            System.out.println(t.toFullString());
        }
    }

    private void viewCustomers(Scanner scan){
        UserDAO ud = UserDAO.getDAO();
        ArrayList<Customer> customers = ud.readCustomers();
        int i = 0;
        int target = -1;
        while (target < 0 || target >= customers.size()) {
            System.out.println(Pad.leftPad(20, "Username", ' ') +
                    Pad.leftPad(20, "First Name", ' ') +
                    Pad.leftPad(20, "Last Name", ' ') +
                    Pad.leftPad(20, "To Select:", ' '));
            for (Customer c : customers) {
                i++;
                System.out.println(Pad.leftPad(20, c.getUsername(), ' ') +
                        Pad.leftPad(20, c.getFirstName(), ' ') +
                        Pad.leftPad(20, c.getLastName(), ' ') +
                        Pad.leftPad(20, Integer.toString(i), ' '));
            }
            target = SafeScan.getPosInt(scan, i) - 1;
        }
        customers.get(target).controller();
    }

    private void processAccounts(Scanner scan, ArrayList<Account> accounts){

        System.out.println(accounts.size());
        if (accounts.size() == 0){
            return;
        }
        int target = -1;
        while (target < 0 || target >= accounts.size()) {
            System.out.println(Pad.balancedTable(20,"Accounts pending approval:", "Enter:", ' ', ""));
            int i = 0;
            for (Account a : accounts) {
                i++;
                System.out.println(Pad.leftPad(20, Integer.toString(a.getId()), ' ') +
                        Pad.leftPad(20, a.getHolders().get(0).getFullName(), ' ') +
                        Pad.leftPad(20, Float.toString(a.getBalance()), ' ') +
                        Pad.leftPad(20, Integer.toString(i), ' '));
            }
            target = SafeScan.getPosInt(scan, i) - 1;
        }
        System.out.println("To approve account, enter y");
        System.out.println("To reject account, enter n");
        System.out.println("To cancel action, enter c");
        char selection = SafeScan.getChar(scan, new char[]{'y', 'n', 'c'});
        switch (selection){
            case 'y':
                accounts.get(target).approve();
                break;
            case 'n':
                accounts.get(target).reject();
                break;
            default:
                break;
        }
    }
}
