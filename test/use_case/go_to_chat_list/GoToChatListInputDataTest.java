package use_case.go_to_chat_list;

import entity.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GoToChatListInputDataTest {
    private final User testUser1 = new User("test1", "test1", "test1");
    private final GoToChatListInputData testInputData = new GoToChatListInputData(testUser1);

    @Test
    void getUser() {
        assertEquals(testUser1, testInputData.getUser());
    }
}