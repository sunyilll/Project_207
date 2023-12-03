package use_case.go_to_channel;

import entity.ChatChannel;
import entity.User;
import interface_adapter.ViewManagerModel;
import interface_adapter.go_to_channel.GoToChannelState;
import interface_adapter.go_to_channel.GoToChannelViewModel;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class GoToChannelOutputDataTest {
    private final User testUser1 = new User("test1", "test1", "test1");
    private final Map<String, User> testMap = new HashMap<>();

    private final LocalDateTime currentDateTime = LocalDateTime.now();
    private final ChatChannel channel = new ChatChannel(testMap, currentDateTime, "sendbird_group_channel_12586989_cbf2eb24180c0399084a22b8acb6519571db23f7");

    private final GoToChannelOutputData testOutputData = new GoToChannelOutputData(testUser1, channel);

    @Test
    void getCurrentUser() {
        assertEquals(testUser1, testOutputData.getCurrentUser());
    }

    @Test
    void getCurrentChannel() {
        assertEquals(channel, testOutputData.getCurrentChannel());
    }
}