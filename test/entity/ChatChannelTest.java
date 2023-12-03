package entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ChatChannelTest {
    private final Map<String, User> testMap = new HashMap<>();

    private final LocalDateTime currentDateTime = LocalDateTime.now();
    private final ChatChannel channel = new ChatChannel(testMap, currentDateTime, "sendbird_group_channel_12586989_cbf2eb24180c0399084a22b8acb6519571db23f7");


    @Test
    void getChatHistory() {
        assertEquals(0, channel.getChatHistory().size());
    }

    @Test
    void getChannelURL() {
        assertEquals("sendbird_group_channel_12586989_cbf2eb24180c0399084a22b8acb6519571db23f7", channel.getChannelURL());
    }

    @Test
    void getMembers() {
        assertEquals(testMap, channel.getMembers());
    }
}