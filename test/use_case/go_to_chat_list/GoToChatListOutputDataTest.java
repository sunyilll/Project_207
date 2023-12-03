package use_case.go_to_chat_list;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GoToChatListOutputDataTest {
    private final GoToChatListOutputData testOutputData = new GoToChatListOutputData(new ArrayList<>());


    @Test
    void getChatChannels() {
        assertEquals(new ArrayList<>(), testOutputData.getChatChannels());
    }
}