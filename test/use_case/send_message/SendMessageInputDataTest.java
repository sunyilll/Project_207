package use_case.send_message;

import data_access.SendMessageDataAccessObject;
import entity.ChatChannel;
import entity.User;
import interface_adapter.ViewManagerModel;
import interface_adapter.send_message.SendMessageState;
import interface_adapter.send_message.SendMessageViewModel;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class SendMessageInputDataTest {
    private final Map<String, User> testMap = new HashMap<>();

    private final LocalDateTime currentDateTime = LocalDateTime.now();
    private final ChatChannel channel = new ChatChannel(testMap, currentDateTime, "sendbird_group_channel_12586989_cbf2eb24180c0399084a22b8acb6519571db23f7");

    private final SendMessageInputData testInputData = new SendMessageInputData("test message", "test1", channel);

    @Test
    void getMessage() {
        assertEquals("test message", testInputData.getMessage());
    }

    @Test
    void getUserId() {
        assertEquals("test1", testInputData.getUserId());
    }

    @Test
    void getChannel() {
        assertEquals(channel, testInputData.getChannel());
    }
}