package use_case;
import data_access.RefreshChatPageDataAccessObject;
import entity.ChatChannel;
import entity.User;
import interface_adapter.ViewManagerModel;
import interface_adapter.refresh_chat_page.RefreshChatPagePresenter;
import interface_adapter.refresh_chat_page.RefreshChatPageState;
import interface_adapter.refresh_chat_page.RefreshChatPageViewModel;
import org.junit.jupiter.api.Test;
import use_case.refresh_chat_page.RefreshChatPageInputData;
import use_case.refresh_chat_page.RefreshChatPageInteractor;
import use_case.refresh_chat_page.RefreshChatPageOutputData;

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
    private final RefreshChatPageDataAccessObject refreshChatPageDataAccessObject = new RefreshChatPageDataAccessObject();
    @Test
    void testRefreshChatPageInputData(){
        assertEquals(channel, testInputData.getChannel());
    }
    @Test
    void testRefreshChatPageInteractorSuccess(){
        refreshChatPageViewModel.setState(testState);
        RefreshChatPagePresenter refreshChatPagePresenter = new RefreshChatPagePresenter(refreshChatPageViewModel, viewManagerModel);

        RefreshChatPageInteractor testInteractor = new RefreshChatPageInteractor(refreshChatPageDataAccessObject, refreshChatPagePresenter);
        testInteractor.execute(testInputData);
        assertTrue(refreshChatPageViewModel.getState().getRefreshSuccessful());
    }
    @Test
    void testRefreshChatPageInteractorFail(){
        refreshChatPageViewModel.setState(testState);
        RefreshChatPagePresenter refreshChatPagePresenter = new RefreshChatPagePresenter(refreshChatPageViewModel, viewManagerModel);

        RefreshChatPageInteractor testInteractor = new RefreshChatPageInteractor(refreshChatPageDataAccessObject, refreshChatPagePresenter);
        testInteractor.execute(badTestInputData);
        assertFalse(refreshChatPageViewModel.getState().getRefreshSuccessful());
    }
}
