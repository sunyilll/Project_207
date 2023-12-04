package use_case.go_to_chat_list;

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
    private final GoToChatListState testState = new GoToChatListState();
    private final GoToChatListOutputData testOutputData = new GoToChatListOutputData(new ArrayList<>(), testUser1);
    private final JsonUserDataAccessObject goToChatListDataAccessObject = new JsonUserDataAccessObject("./users.json");


    @Test
    void execute() {
        User testUser1 = new User("test2", "test2", "test2");
        testState.setUser(testUser1);
        goToChatListViewModel.setState(testState);
        goToChatListDataAccessObject.saveCurrentUser(testUser1.getUserID());

        GoToChatListPresenter goToChatListPresenter = new GoToChatListPresenter(goToChatListViewModel, viewManagerModel);
        GoToChatListInteractor testInteractor = new GoToChatListInteractor(goToChatListDataAccessObject, goToChatListPresenter);
        testInteractor.execute();
        assertTrue(goToChatListViewModel.getState().getSuccess());



    }
    @Test
    void testGoToChatListInteractorFail(){
        try{
            goToChatListViewModel.setState(testState);
            GoToChatListPresenter goToChatListPresenter = new GoToChatListPresenter(goToChatListViewModel, viewManagerModel);
            GoToChatListInteractor testInteractor = new GoToChatListInteractor(null, goToChatListPresenter);

            testInteractor.execute();


        } catch (NullPointerException e){
            fail();
        }

    }
}