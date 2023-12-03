package use_case.go_to_personal_profile;

import entity.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GoToPersonalProfileInputDataTest {
    private final User testUser1 = new User("test1id", "test1name", "test1pswd");
    private final GoToPersonalProfileInputData testInputData = new GoToPersonalProfileInputData("test1id");

//    @Test
//    void isUserLoggedIn() {
//    }

    @Test
    void getUser() {
        assertEquals("test1id", testInputData.getUserId());
    }
}