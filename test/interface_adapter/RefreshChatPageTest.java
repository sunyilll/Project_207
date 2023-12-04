package interface_adapter;

import data_access.RefreshChatPageDataAccessObject;
import entity.ChatChannel;
import entity.User;
import interface_adapter.refresh_chat_page.RefreshChatPageController;
import interface_adapter.refresh_chat_page.RefreshChatPagePresenter;
import interface_adapter.refresh_chat_page.RefreshChatPageState;
import interface_adapter.refresh_chat_page.RefreshChatPageViewModel;
import kotlin.Pair;
import org.junit.jupiter.api.Test;
import use_case.refresh_chat_page.RefreshChatPageInputData;
import use_case.refresh_chat_page.RefreshChatPageInteractor;

import java.beans.PropertyChangeListener;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class RefreshChatPageTest {
    private final User testUser1 = new User("test1", "test1", "test1");
    private final User testUser2 = new User("test1", "test1", "test1");
    private final Map<String, User> testMap = new HashMap<>();

    private final LocalDateTime currentDateTime = LocalDateTime.now();
    private final ChatChannel channel = new ChatChannel(testMap, currentDateTime, "sendbird_group_channel_12586989_cbf2eb24180c0399084a22b8acb6519571db23f7");
    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
    private final RefreshChatPageViewModel refreshChatPageViewModel = new RefreshChatPageViewModel();
    private final RefreshChatPageState testState = new RefreshChatPageState(testUser1, channel);
    private final RefreshChatPageInputData testInputData = new RefreshChatPageInputData(channel);
    private final RefreshChatPageInputData badTestInputData = new RefreshChatPageInputData(new ChatChannel(testMap, currentDateTime, "non-existent-channel"));
    private final ChatChannel badChannel = new ChatChannel(testMap, currentDateTime, "non-existent-channel");
    private final RefreshChatPageDataAccessObject refreshChatPageDataAccessObject = new RefreshChatPageDataAccessObject();
    private boolean propertyChanged = true;

    @Test
    void testRefreshChatPageInputData(){
        assertEquals(channel, testInputData.getChannel());
    }
    @Test
    void testRefreshChatPageControllerSuccess(){
        refreshChatPageViewModel.setState(testState);
        RefreshChatPagePresenter refreshChatPagePresenter = new RefreshChatPagePresenter(refreshChatPageViewModel, viewManagerModel);

        RefreshChatPageInteractor testInteractor = new RefreshChatPageInteractor(refreshChatPageDataAccessObject, refreshChatPagePresenter);
        RefreshChatPageController controller = new RefreshChatPageController(testInteractor);
        controller.execute(channel);

        // Assert
        assertTrue(refreshChatPageViewModel.getState().getRefreshSuccessful());
    }
    @Test
    void testRefreshChatPageControllerFail(){
        refreshChatPageViewModel.setState(testState);
        RefreshChatPagePresenter refreshChatPagePresenter = new RefreshChatPagePresenter(refreshChatPageViewModel, viewManagerModel);

        RefreshChatPageInteractor testInteractor = new RefreshChatPageInteractor(refreshChatPageDataAccessObject, refreshChatPagePresenter);
        RefreshChatPageController controller = new RefreshChatPageController(testInteractor);
        controller.execute(badChannel);

        assertFalse(refreshChatPageViewModel.getState().getRefreshSuccessful());
    }
    @Test
    public void testRefreshChatPageStateConstructorCopiesProperties() {
        // Arrange: create a mock RefreshChatPageState with some data
        RefreshChatPageState state = new RefreshChatPageState();
        ArrayList<Pair<String, String>> expectedMessageList = new ArrayList<>();
        expectedMessageList.add(new Pair<>("User1", "Hello"));
        expectedMessageList.add(new Pair<>("User2", "Hi there"));

        RefreshChatPageState originalState = new RefreshChatPageState();
        originalState.setChannel(channel);
        originalState.setMessageList(expectedMessageList);
        originalState.setUser_id("user123");
        originalState.setRefreshSuccessful(true);
        originalState.setErrorMessage("Error occurred");

        // Act: create a new RefreshChatPageState by copying the original
        RefreshChatPageState copiedState = new RefreshChatPageState(originalState);

        // Assert: verify that all properties are copied correctly
        assertEquals(channel, copiedState.getChannel());
        assertEquals(expectedMessageList, copiedState.getMessageList());
        assertEquals("user123", copiedState.getUser_id());
        assertTrue(copiedState.getRefreshSuccessful());
        assertEquals("Error occurred", copiedState.getErrorMessage());
    }
    @Test
    public void testSetChannel() {
        // Arrange
        RefreshChatPageState state = new RefreshChatPageState();
        ChatChannel expectedChannel = channel;

        // Act
        state.setChannel(expectedChannel);

        // Assert
        assertEquals(expectedChannel, state.getChannel(), "The channel should be set correctly.");
    }
    @Test
    public void testToString() {
        // Arrange
        RefreshChatPageState state = new RefreshChatPageState();
        state.setChannel(channel);
        state.setUser_id("user123");
        state.setErrorMessage("Error occurred");
        String expectedString = "RefreshChatPageState{" +
                "refreshSuccessful=false, " +
                "errorMessage='Error occurred'}";

        // Act
        String actualString = state.toString();

        // Assert
        assertEquals(expectedString, actualString, "The toString method should return the correct string representation.");
    }


    @Test
    void testAddPropertyChangeListener() {
        RefreshChatPageViewModel refreshChatPageViewModel1 = new RefreshChatPageViewModel();

        PropertyChangeListener listener = evt -> propertyChanged = true;

        refreshChatPageViewModel1.addPropertyChangeListener(listener);

        RefreshChatPageState initialState = new RefreshChatPageState();
        initialState.setUser_id("hh");
        refreshChatPageViewModel1.setState(initialState);

        RefreshChatPageState newState = new RefreshChatPageState();
        newState.setUser_id("hh");
        refreshChatPageViewModel1.setState(newState);

        assertTrue(propertyChanged, "The property change listener should have been triggered.");
    }
}
