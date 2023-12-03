package use_case.go_to_personal_profile;

import entity.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GoToPersonalProfileInputDataTest {
    private final User testUser1 = new User("test1", "test1", "test1");
    private final GoToPersonalProfileInputData testInputData = new GoToPersonalProfileInputData(testUser1);

//    @Test
//    void isUserLoggedIn() {
//    }

    @Test
    void getUser() {
        assertEquals(testUser1, testInputData.getUser());
    }
}