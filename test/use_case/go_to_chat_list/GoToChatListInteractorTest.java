package use_case.go_to_chat_list;

import data_access.GoToChatListDataAccessObject;
import data_access.JsonUserDataAccessObject;
import entity.ChatChannel;
import entity.User;
import interface_adapter.ViewManagerModel;
import interface_adapter.go_to_chatl_list.GoToChatListPresenter;
import interface_adapter.go_to_chatl_list.GoToChatListState;
import interface_adapter.go_to_chatl_list.GoToChatListViewModel;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class GoToChatListInteractorTest {
    private final User testUser1 = new User("test1", "test1", "test1");
    private final User testUser2 = new User("non-existent", "test1", "test1");
    private final Map<String, User> testMap = new HashMap<>();

    private final LocalDateTime currentDateTime = LocalDateTime.now();
    private final ChatChannel channel = new ChatChannel(testMap, currentDateTime, "sendbird_group_channel_12586989_cbf2eb24180c0399084a22b8acb6519571db23f7");
    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
    private final GoToChatListViewModel goToChatListViewModel = new GoToChatListViewModel();
    private final GoToChatListState testState = new GoToChatListState(testUser1, new ArrayList<>());
    private final GoToChatListOutputData testOutputData = new GoToChatListOutputData(new ArrayList<>(), testUser1);
    private final GoToChatListDataAccessInterface goToChatListDataAccessObject = new JsonUserDataAccessObject()


    @Test
    void execute() {
        goToChatListViewModel.setState(testState);
        GoToChatListPresenter goToChatListPresenter = new GoToChatListPresenter(goToChatListViewModel, viewManagerModel);
        GoToChatListInteractor testInteractor = new GoToChatListInteractor(goToChatListDataAccessObject, goToChatListPresenter);
        testInteractor.execute(testInputData);
        assertTrue(goToChatListViewModel.getState().getSuccess());

        goToChatListViewModel.setState(testState);
        testInteractor.execute(badTestInputData);
        assertFalse(goToChatListViewModel.getState().getSuccess());

    }
}