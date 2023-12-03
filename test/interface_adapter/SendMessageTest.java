package interface_adapter;

import data_access.SendMessageDataAccessObject;
import entity.ChatChannel;
import entity.User;
import interface_adapter.send_message.SendMessageController;
import interface_adapter.send_message.SendMessagePresenter;
import interface_adapter.send_message.SendMessageState;
import interface_adapter.send_message.SendMessageViewModel;
import org.junit.jupiter.api.Test;
import use_case.send_message.*;

import java.beans.PropertyChangeListener;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SendMessageTest {
    private SendMessageViewModel viewModel = new SendMessageViewModel();
    private ViewManagerModel viewManagerModel =  new ViewManagerModel();
    private SendMessageOutputBoundary sendMessageOutputBoundary;
    private SendMessageDataAccessInterface sendMessageDataAccessInterface = new MockSendMessageUserDataAccess();
    private SendMessageInputBoundary userSendMessageUseCaseInteractor = new SendMessageInteractor(sendMessageDataAccessInterface, sendMessageOutputBoundary);

    private final SendMessageDataAccessObject sendMessageDataAccessObject= new SendMessageDataAccessObject(
            "https://api-1F4C3D4F-01DB-4A99-8704-BE4CB1FE3AE5.sendbird.com/v3",
            "1F4C3D4F-01DB-4A99-8704-BE4CB1FE3AE5");
    private SendMessageState sendMessageState = new SendMessageState();
    private boolean propertyChanged = true;


    @Test
    void testSendMessageSuccess() {
        // Arrange
        SendMessageOutputData response = new SendMessageOutputData("hello");
        User user = new User("test1", "test1", "test1");
        Map<String, User> members = new HashMap<>();
        members.put(user.getUserID(), user);
        ChatChannel channel = new ChatChannel(members, LocalDateTime.now(), "sendbird_group_channel_12586989_cbf2eb24180c0399084a22b8acb6519571db23f7");

        // Act
        SendMessagePresenter presenter = new SendMessagePresenter(viewModel, viewManagerModel);
        SendMessageInteractor sendMessageInteractor = new SendMessageInteractor(sendMessageDataAccessObject, presenter);
        presenter.prepareSuccessView(response);

        SendMessageController controller = new SendMessageController(sendMessageInteractor);
        controller.execute(response.getMessage(), user.getUserID(), channel);

        // Assert
        assertTrue(viewModel.getState().isMessageSentSuccessful());
    }

    @Test
    void testSendMessageFailureEmptyMessage() {
        // Arrange
        SendMessageOutputData response = new SendMessageOutputData("");
        User user = new User("user123", "JohnDoe", "password123");
        Map<String, User> members = new HashMap<>();
        members.put(user.getUserID(), user);
        ChatChannel channel = new ChatChannel(members, LocalDateTime.now(), "sendbird_group_channel_12586989_cbf2eb24180c0399084a22b8acb6519571db23f7");
        SendMessagePresenter presenter = new SendMessagePresenter(viewModel, viewManagerModel);

        // Act
        presenter.prepareFailView(response.getMessage());
        SendMessageInteractor sendMessageInteractor = new SendMessageInteractor(sendMessageDataAccessObject, presenter);
        SendMessageController controller = new SendMessageController(sendMessageInteractor);
        controller.execute(response.getMessage(), user.getUserID(), channel);

        // Assert
        assertFalse(viewModel.getState().isMessageSentSuccessful());
        assertNotNull(viewModel.getState().getErrorMessage());
    }

    @Test
    void testSendMessageFailureUserNotInChannel() {
        // Arrange
        String message = "Hello, World!";
        User user = new User("userNotInChannel", "JaneDoe", "password123");
        Map<String, User> members = new HashMap<>();
        members.put(user.getUserID(), user);
        ChatChannel channel = new ChatChannel(members, LocalDateTime.now(), "sendbird_group_channel_12586989_cbf2eb24180c0399084a22b8acb6519571db23f7");

        SendMessagePresenter presenter = new SendMessagePresenter(viewModel, viewManagerModel);
        SendMessageInteractor sendMessageInteractor = new SendMessageInteractor(sendMessageDataAccessObject, presenter);

        SendMessageController controller = new SendMessageController(sendMessageInteractor);
        // Act
        controller.execute(message, user.getUserID(), channel);

        // Assert
        assertFalse(viewModel.getState().isMessageSentSuccessful());
        assertNotNull(viewModel.getState().getErrorMessage());
    }

    @Test
    void gettersAndSettersTest() {
        User user = new User("user123", "JohnDoe", "password123");
        Map<String, User> members = new HashMap<>();
        members.put(user.getUserID(), user);
        ChatChannel channel = new ChatChannel(members, LocalDateTime.now(), "sendbird_group_channel_12586989_cbf2eb24180c0399084a22b8acb6519571db23f7");

        SendMessageState state = new SendMessageState();
        state.setMessgae("hi");
        assertEquals("hi", state.getMessage());

        state.setUser_id("User1");
        assertEquals("User1", state.getUser_id());

        state.setErrorMessage("error");
        assertEquals("error", state.getErrorMessage());

        state.setChannel(channel);
        assertEquals(channel, state.getChannel());

        state.setMessageSentSuccessful(true);
        assertTrue(state.isMessageSentSuccessful());
    }
    @Test
    void constructorSetsFieldsCorrectly() {
        // Arrange
        User user = new User("user123", "JohnDoe", "password123");
        Map<String, User> members = new HashMap<>();
        members.put(user.getUserID(), user);
        ChatChannel channel = new ChatChannel(members, LocalDateTime.now(), "sendbird_group_channel_12586989_cbf2eb24180c0399084a22b8acb6519571db23f7");


        // Act
        SendMessageState state = new SendMessageState(user, channel);

        // Assert
        assertEquals(user.getUserID(), state.getUser_id(), "User ID should be set from the User object");
        assertEquals(channel, state.getChannel(), "Channel should be set from the ChatChannel object");
    }

    @Test
    void copyConstructorTest() {
        User user = new User("user123", "JohnDoe", "password123");
        Map<String, User> members = new HashMap<>();
        members.put(user.getUserID(), user);
        ChatChannel channel = new ChatChannel(members, LocalDateTime.now(), "sendbird_group_channel_12586989_cbf2eb24180c0399084a22b8acb6519571db23f7");

        SendMessageState original = new SendMessageState();
        original.setMessgae("hi");
        original.setUser_id("User1");
        original.setErrorMessage("error");
        original.setChannel(channel);
        original.setMessageSentSuccessful(true);

        SendMessageState copy = new SendMessageState(original);
        assertEquals("hi", copy.getMessage());
        assertEquals("User1", copy.getUser_id());
        assertEquals("error", copy.getErrorMessage());
        assertEquals(channel, copy.getChannel());
        assertTrue(copy.isMessageSentSuccessful());
    }


    @Test
    void testAddPropertyChangeListener() {
        SendMessageViewModel sendMessageViewModel = new SendMessageViewModel();

        PropertyChangeListener listener = evt -> propertyChanged = true;

        sendMessageViewModel.addPropertyChangeListener(listener);

        SendMessageState initialState = new SendMessageState();
        initialState.setMessgae("hi");
        sendMessageViewModel.setState(initialState);

        SendMessageState newState = new SendMessageState();
        newState.setMessgae("hi");
        sendMessageViewModel.setState(newState);

        assertTrue(propertyChanged, "The property change listener should have been triggered.");
    }
    @Test
    void toStringTest() {
        // Set some values
        sendMessageState.setMessageSentSuccessful(true);
        sendMessageState.setErrorMessage("error");

        // Check the toString method
        String expectedString = "SendMessageState{" +
                "messageSentSuccessful=true, " +
                "errorMessage='error'}";
        assertEquals(expectedString, sendMessageState.toString());
    }

    public class MockSendMessageUserDataAccess implements SendMessageDataAccessInterface {
        private Map<String, User> users = new HashMap<>();

        @Override
        public boolean sendMessage(String message, String user_id, ChatChannel channel) {
            return false;
        }
    }
}

