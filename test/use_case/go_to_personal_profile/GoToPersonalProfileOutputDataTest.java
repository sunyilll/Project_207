package use_case.go_to_personal_profile;

import entity.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GoToPersonalProfileOutputDataTest {
    private final User testUser1 = new User("test1", "test1", "test1");
    private final GoToPersonalProfileOutputData testOutputData = new GoToPersonalProfileOutputData(testUser1);

    @Test
    void getUser() {
        assertEquals(testUser1, testOutputData.getUser());
    }
}