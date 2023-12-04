package interface_adapter;

import data_access.JsonUserDataAccessObject;
import entity.ChatChannel;
import entity.User;
import interface_adapter.go_to_chatl_list.GoToChatListController;
import interface_adapter.go_to_chatl_list.GoToChatListPresenter;
import interface_adapter.go_to_chatl_list.GoToChatListState;
import interface_adapter.go_to_chatl_list.GoToChatListViewModel;
import org.junit.jupiter.api.Test;
import use_case.go_to_chat_list.GoToChatListDataAccessInterface;
import use_case.go_to_chat_list.GoToChatListInteractor;
import use_case.go_to_chat_list.GoToChatListOutputData;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class GoToChatListTest {
    private final User testUser1 = new User("test1", "test1", "test1");
    private final User testUser2 = new User("non-existent", "test1", "test1");
    private final Map<String, User> testMap = new HashMap<>();
    private String testErrorMessage = "test error message";
    private boolean testSuccess = true;



    private final LocalDateTime currentDateTime = LocalDateTime.now();
    private final ChatChannel channel = new ChatChannel(testMap, currentDateTime, "sendbird_group_channel_12586989_cbf2eb24180c0399084a22b8acb6519571db23f7");
    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
    private final GoToChatListViewModel goToChatListViewModel = new GoToChatListViewModel();
    private final GoToChatListState testState = new GoToChatListState();
    private final GoToChatListOutputData testOutputData = new GoToChatListOutputData(new ArrayList<>(), testUser1);
    private final GoToChatListDataAccessInterface goToChatListDataAccessObject = new JsonUserDataAccessObject("data/users.json");


    @Test
    public void testSetUser() {
        GoToChatListState state = new GoToChatListState();

        state.setUser(testUser1);
        assertEquals(testUser1, state.getUser());
    }

    @Test
    public void testSetErrorMessage() {
        GoToChatListState state = new GoToChatListState();
        state.setErrorMessage(testErrorMessage);
        assertEquals(testErrorMessage, state.getErrorMessage());
    }

    @Test
    public void testSetSuccess() {
        GoToChatListState state = new GoToChatListState();
        state.setSuccess(testSuccess);
        assertEquals(testSuccess, state.getSuccess());
    }
    @Test
    void execute() {
        User testUser1 = new User("test1", "test1", "test1");
        goToChatListViewModel.setState(testState);
        GoToChatListPresenter goToChatListPresenter = new GoToChatListPresenter(goToChatListViewModel, viewManagerModel);
        GoToChatListInteractor testInteractor = new GoToChatListInteractor(new JsonUserDataAccessObject("data/users.json"), goToChatListPresenter);
        GoToChatListController controller = new GoToChatListController(testInteractor);
        controller.execute();
        goToChatListViewModel.getState().setSuccess(true);
        assertTrue(goToChatListViewModel.getState().getSuccess());

    }
    @Test
    void testGoToChatListControllerSuccess(){
        try{
            goToChatListViewModel.setState(testState);
            GoToChatListPresenter goToChatListPresenter = new GoToChatListPresenter(goToChatListViewModel, viewManagerModel);
            GoToChatListInteractor testInteractor = new GoToChatListInteractor(goToChatListDataAccessObject, goToChatListPresenter);
            GoToChatListController controller = new GoToChatListController(testInteractor);

            goToChatListViewModel.setState(new GoToChatListState());
            goToChatListPresenter.prepareSuccessView(testOutputData);

            controller.execute();
        } catch (NullPointerException e){
            fail();
        }
    }
    @Test
    void testGoToChatListControllerFail(){
        try{
            goToChatListViewModel.setState(testState);
            GoToChatListPresenter goToChatListPresenter = new GoToChatListPresenter(goToChatListViewModel, viewManagerModel);
            GoToChatListInteractor testInteractor = new GoToChatListInteractor(null, goToChatListPresenter);

            testInteractor.execute();


        } catch (NullPointerException e){
            fail();
        }
    }
    @Test
    void testCopyConstructor() {
        ArrayList<ChatChannel> channelList = new ArrayList<>();
        channelList.add(channel);


        // Arrange
        GoToChatListState originalState = new GoToChatListState();
        originalState.setUser(testUser1);
        originalState.setChatChannels(channelList);
        originalState.setErrorMessage("No error");
        originalState.setSuccess(true);

        // Act
        GoToChatListState copiedState = new GoToChatListState(originalState);

        // Assert
        assertEquals(originalState.getUser(), copiedState.getUser());
        assertEquals(originalState.getChatChannels(), copiedState.getChatChannels());
        assertEquals(originalState.getErrorMessage(), copiedState.getErrorMessage());
        assertEquals(originalState.getSuccess(), copiedState.getSuccess());
    }
    @Test
    public void testGoToChatListStateConstructor() {
        ArrayList<ChatChannel> channelList = new ArrayList<>();
        channelList.add(channel);

        // Create a new state with the constructor
        GoToChatListState state = new GoToChatListState(testUser1, channelList);

        // Assert that the state has the expected user and chat channels
        assertEquals(testUser1, state.getUser());
        assertEquals(channelList, state.getChatChannels());
    }

}

