import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class Menu {
    public static void main(String []args){
        try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Bank_Demo", "JavaDemo", "javapass")){
            UserDAO ud = new UserDAO(conn);
            UserDAO.setDAO(ud);
            AccountDAO ad = new AccountDAO(conn);
            AccountDAO.setDAO(ad);
            TransactionDAO td = new TransactionDAO(conn);
            TransactionDAO.setDAO(td);
            User u = ud.loginUser("rarasey", "<rawpassword text>");
            if (u != null){
                ArrayList<Account> acc = ad.getAccounts((Customer) u);
                System.out.println("Found User!");
                Account current = null;
                for (Account a: acc){
                    current = ad.read(a);
                    System.out.println(current.toString());
                }
            }
            else {
                System.out.println("Didn't find anything.");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
