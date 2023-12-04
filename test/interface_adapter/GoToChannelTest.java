package interface_adapter;

import entity.ChatChannel;
import entity.User;
import interface_adapter.go_to_channel.GoToChannelController;
import interface_adapter.go_to_channel.GoToChannelPresenter;
import interface_adapter.go_to_channel.GoToChannelState;
import interface_adapter.go_to_channel.GoToChannelViewModel;
import org.junit.jupiter.api.Test;
import use_case.go_to_channel.GoToChannelInputData;
import use_case.go_to_channel.GoToChannelInteractor;
import use_case.go_to_channel.GoToChannelOutputData;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GoToChannelTest {
    private final User testUser1 = new User("test1", "test1", "test1");
    private final User testUser2 = new User("t", "t", "t");
    private final Map<String, User> testMap = new HashMap<>();

    private final LocalDateTime currentDateTime = LocalDateTime.now();
    private final ChatChannel channel = new ChatChannel(testMap, currentDateTime, "sendbird_group_channel_12586989_cbf2eb24180c0399084a22b8acb6519571db23f7");
    private final ChatChannel badChannel = new ChatChannel(testMap, currentDateTime, "non-existent-channel");
    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
    private final GoToChannelViewModel goToChannelViewModel = new GoToChannelViewModel();
    private final GoToChannelState testState = new GoToChannelState(testUser1, channel);
    private final GoToChannelInputData testInputData = new GoToChannelInputData(testUser1, channel);
    private final GoToChannelOutputData testOutputData = new GoToChannelOutputData(testUser1, channel);
    @Test
    void testGoToChannelControllerSuccess(){
        goToChannelViewModel.setState(testState);
        GoToChannelPresenter goToChannelPresenter = new GoToChannelPresenter(goToChannelViewModel, viewManagerModel);
        GoToChannelInteractor testInteractor = new GoToChannelInteractor(goToChannelPresenter);
        GoToChannelController controller = new GoToChannelController(testInteractor);

        controller.execute(testUser1, channel);
        assertTrue(goToChannelViewModel.getState().getSuccess());
    }
    @Test
    void testGoToChannelControllerFailure(){
        goToChannelViewModel.setState(testState);
        GoToChannelPresenter goToChannelPresenter = new GoToChannelPresenter(goToChannelViewModel, viewManagerModel);
        GoToChannelInteractor testInteractor = new GoToChannelInteractor(goToChannelPresenter);
        GoToChannelController controller = new GoToChannelController(testInteractor);

        goToChannelViewModel.getState().setSuccess(false);
        String error = "An error occurred";

        // Act
        goToChannelPresenter.prepareFailView(error);

        // Assert
        assertFalse(goToChannelViewModel.getState().getSuccess());
    }
    @Test
    void testCopyConstructor() {
        // Arrange
        GoToChannelState originalState = new GoToChannelState();
        originalState.setCurrentUser(testUser1);
        originalState.setCurrentChannel(channel);
        originalState.setErrorMessage("No error");
        originalState.setSuccess(true);

        // Act
        GoToChannelState copiedState = new GoToChannelState(originalState);

        // Assert
        assertEquals(originalState.getCurrentUser(), copiedState.getCurrentUser());
        assertEquals(originalState.getCurrentChannel(), copiedState.getCurrentChannel());
        assertEquals(originalState.getErrorMessage(), copiedState.getErrorMessage());
        assertEquals(originalState.getSuccess(), copiedState.getSuccess());
    }
    @Test
    void testStateSettersAndGetters() {
        GoToChannelState state = new GoToChannelState();
        state.setCurrentChannel(channel);
        state.setCurrentUser(testUser1);
        state.setErrorMessage("An error occurred");
        state.setSuccess(true);

        assertEquals(channel, state.getCurrentChannel());
        assertEquals(testUser1, state.getCurrentUser());
        assertEquals("An error occurred", state.getErrorMessage());
        assertTrue(state.getSuccess());
    }

    @Test
    void testToString() {
        GoToChannelState state = new GoToChannelState();
        state.setCurrentChannel(channel);
        state.setCurrentUser(testUser1);
        state.setErrorMessage("An error occurred");
        state.setSuccess(true);

        String expectedString = "GoToChannelState(currentUser=" + state.getCurrentUser() +
                ", currentChannel=" + state.getCurrentChannel() +
                ", errorMessage=" + state.getErrorMessage() +
                ", success=" + state.getSuccess() + ")";
        assertEquals(expectedString, state.toString());
    }

}
