import static org.junit.jupiter.api.Assertions.*;

import com.project0.Menu;
import com.project0.UserDAO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

class LoginTest {

    @BeforeAll
    static void setup() {
        Menu.setConnections();
    }

    @Test
    void login() { assertNotNull(
                Menu.login(
                        new Scanner("rarasey \n password \n"),
                        UserDAO.getDAO()));
    }
    @Test
    void failLogin() {
        assertAll(
            () -> assertNull(Menu.login(
                    new Scanner("rrasey \n password \n"),
                    UserDAO.getDAO())),
            () -> assertNull(Menu.login(
                    new Scanner("rarasey \n pasword \n"),
                    UserDAO.getDAO())));
    }

}