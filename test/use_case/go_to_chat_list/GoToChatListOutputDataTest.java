package use_case.go_to_chat_list;

import entity.User;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GoToChatListOutputDataTest {
    private final User testUser1 = new User("test1", "test1", "test1");
    private final GoToChatListOutputData testOutputData = new GoToChatListOutputData(new ArrayList<>(), testUser1);


    @Test
    void getChatChannels() {
        assertEquals(new ArrayList<>(), testOutputData.getChatChannels());
        assertEquals(testUser1, testOutputData.getUser());
    }
}