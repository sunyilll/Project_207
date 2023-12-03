package use_case.go_to_personal_profile;

import entity.ChatChannel;
import entity.User;
import interface_adapter.ViewManagerModel;
import interface_adapter.go_to_personal_profile.GoToPersonalProfilePresenter;
import interface_adapter.go_to_personal_profile.GoToPersonalProfileState;
import interface_adapter.go_to_personal_profile.GoToPersonalProfileViewModel;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class GoToPersonalProfileInteractorTest {
    private final User testUser1 = new User("test1", "test1", "test1");
    private final User testUser2 = new User("non-existent", "test1", "test1");
    private final Map<String, User> testMap = new HashMap<>();

    private final LocalDateTime currentDateTime = LocalDateTime.now();
    private final ChatChannel channel = new ChatChannel(testMap, currentDateTime, "sendbird_group_channel_12586989_cbf2eb24180c0399084a22b8acb6519571db23f7");
    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
    private final GoToPersonalProfileInputData testInputData = new GoToPersonalProfileInputData("test1");
    private final GoToPersonalProfileInputData badTestInputData = new GoToPersonalProfileInputData(null);
    private final GoToPersonalProfileState testState = new GoToPersonalProfileState();
    private final GoToPersonalProfileViewModel goToPersonalProfileViewModel = new GoToPersonalProfileViewModel();
    private final GoToPersonalProfileOutputData testOutputData = new GoToPersonalProfileOutputData(testUser1);
    @Test
    void execute() {
        testState.setUser(testUser1);
        goToPersonalProfileViewModel.setState(testState);
        GoToPersonalProfilePresenter goToPersonalProfilePresenter = new GoToPersonalProfilePresenter(goToPersonalProfileViewModel, viewManagerModel);
        GoToPersonalProfileInteractor testInteractor = new GoToPersonalProfileInteractor(goToPersonalProfilePresenter);
        testInteractor.execute(testInputData);
        assertTrue(goToPersonalProfileViewModel.getState().isGoToPersonalProfileSuccess());

    }
    @Test
    void testGoToPersonalProfileInteractorFail(){
        try{
            goToPersonalProfileViewModel.setState(testState);
            GoToPersonalProfilePresenter goToPersonalProfilePresenter = new GoToPersonalProfilePresenter(goToPersonalProfileViewModel, viewManagerModel);
            GoToPersonalProfileInteractor testInteractor = new GoToPersonalProfileInteractor(goToPersonalProfilePresenter);

            testInteractor.execute(badTestInputData);
            fail();
        } catch (NullPointerException e){
            assertTrue(true);
        }

    }
}