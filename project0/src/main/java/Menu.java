import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    private static User u;

    public static void main(String []args){
        u = null;
        int select = 99999;
        while (u == null && select >= 2) {
            try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Bank_Demo", "JavaDemo", "javapass")) {
                UserDAO ud = new UserDAO(conn);
                UserDAO.setDAO(ud);
                AccountDAO ad = new AccountDAO(conn);
                AccountDAO.setDAO(ad);
                TransactionDAO td = new TransactionDAO(conn);
                TransactionDAO.setDAO(td);
                Scanner scan = new Scanner(System.in);
                select = 99999;
                Pad.newScreen();
                while (select > 3) {
                    System.out.println(Pad.balancedTable(20, "Options:", "Enter:", ' ', ""));
                    System.out.println(Pad.balancedTable(20, "Close", "1", ' ', ""));
                    System.out.println(Pad.balancedTable(20, "Login", "2", ' ', ""));
                    System.out.println(Pad.balancedTable(20, "Register", "3", ' ', ""));
                    select = SafeScan.getPosInt(scan, 3);
                    switch (select) {
                        case 2:
                            System.out.print("Enter username: ");
                            String username = scan.next();
                            System.out.print("Enter password: ");
                            String password = scan.next();
                            u = ud.loginUser(username, password);
                            if (u != null) {
                                u.controller();
                            } else {
                                System.out.println("Didn't find anything. Was your password right?");
                                select = 99999;
                            }
                            break;
                        case 3:
                            u = registerUser(scan, ud);
                            if (u == null){
                                System.out.println("Couldn't register you. Try a different username.");
                                select = 99999;
                            }
                            else {
                                u.controller();
                            }
                            break;
                        default:
                            break;

                    }
                }
            u = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static User getU(){
        return u;
    }

    public static User registerUser(Scanner scan, UserDAO ud){
        System.out.print("Enter username: ");
        String username = scan.next();
        System.out.print("Enter password: ");
        String password = scan.next();
        System.out.print("Enter first name: ");
        String firstName = scan.next();
        System.out.print("Enter last name: ");
        String lastName = scan.next();
        return ud.registerUser(username, firstName, lastName, password, false);
    }
}
