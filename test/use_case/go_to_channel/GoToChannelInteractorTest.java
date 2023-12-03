package use_case.go_to_channel;

import entity.ChatChannel;
import entity.User;
import interface_adapter.ViewManagerModel;
import interface_adapter.go_to_channel.GoToChannelPresenter;
import interface_adapter.go_to_channel.GoToChannelState;
import interface_adapter.go_to_channel.GoToChannelViewModel;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class GoToChannelInteractorTest {
    private final User testUser1 = new User("test1", "test1", "test1");
    private final Map<String, User> testMap = new HashMap<>();

    private final LocalDateTime currentDateTime = LocalDateTime.now();
    private final ChatChannel channel = new ChatChannel(testMap, currentDateTime, "sendbird_group_channel_12586989_cbf2eb24180c0399084a22b8acb6519571db23f7");
    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
    private final GoToChannelViewModel goToChannelViewModel = new GoToChannelViewModel();
    private final GoToChannelState testState = new GoToChannelState(testUser1, channel);
    private final GoToChannelInputData testInputData = new GoToChannelInputData(testUser1, channel);

    @Test
    void execute() {
        goToChannelViewModel.setState(testState);
        GoToChannelPresenter goToChannelPresenter = new GoToChannelPresenter(goToChannelViewModel, viewManagerModel);
        GoToChannelInteractor testInteractor = new GoToChannelInteractor(goToChannelPresenter);

        testInteractor.execute(testInputData);
        assertTrue(goToChannelViewModel.getState().getSuccess());

    }
}