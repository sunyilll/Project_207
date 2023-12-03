package use_case.refresh_chat_page;

import entity.ChatChannel;
import entity.User;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class RefreshChatPageInputDataTest {
    private final User testUser1 = new User("test1", "test1", "test1");
    private final User testUser2 = new User("test1", "test1", "test1");
    private final Map<String, User> testMap = new HashMap<>();

    private final LocalDateTime currentDateTime = LocalDateTime.now();
    private final ChatChannel channel = new ChatChannel(testMap, currentDateTime, "sendbird_group_channel_12586989_cbf2eb24180c0399084a22b8acb6519571db23f7");
    private final RefreshChatPageInputData testInputData = new RefreshChatPageInputData(channel);

    @Test
    void getChannel() {
        assertEquals(channel, testInputData.getChannel());
    }
}