package use_case.refresh_chat_page;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class RefreshChatPageOutputDataTest {
    private final RefreshChatPageOutputData testOutputData = new RefreshChatPageOutputData(new ArrayList<>());
    @Test
    void getMessageList() {
        assertEquals(new ArrayList<>(), testOutputData.getMessageList());
    }
}