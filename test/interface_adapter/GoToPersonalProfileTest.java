package interface_adapter;

import data_access.JsonUserDataAccessObject;
import entity.ChatChannel;
import entity.User;
import interface_adapter.go_to_personal_profile.GoToPersonalProfileController;
import interface_adapter.go_to_personal_profile.GoToPersonalProfilePresenter;
import interface_adapter.go_to_personal_profile.GoToPersonalProfileState;
import interface_adapter.go_to_personal_profile.GoToPersonalProfileViewModel;
import org.junit.jupiter.api.Test;
import use_case.go_to_personal_profile.GoToPersonalProfileDataAccessInterface;
import use_case.go_to_personal_profile.GoToPersonalProfileInteractor;
import use_case.go_to_personal_profile.GoToPersonalProfileOutputData;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class GoToPersonalProfileTest {
    private final User testUser1 = new User("test1", "test1", "test1");
    private final User testUser2 = new User("non-existent", "test1", "test1");
    private final Map<String, User> testMap = new HashMap<>();
    private final Map<String, Integer> testMap2 = new HashMap<>();


    private final LocalDateTime currentDateTime = LocalDateTime.now();
    private final ChatChannel channel = new ChatChannel(testMap, currentDateTime, "sendbird_group_channel_12586989_cbf2eb24180c0399084a22b8acb6519571db23f7");
    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
    private final GoToPersonalProfileState testState = new GoToPersonalProfileState();
    private final GoToPersonalProfileViewModel goToPersonalProfileViewModel = new GoToPersonalProfileViewModel();
    private final GoToPersonalProfileOutputData testOutputData = new GoToPersonalProfileOutputData(testUser1);
    private final GoToPersonalProfileDataAccessInterface dataAccessObject = new JsonUserDataAccessObject("data/users.json");



    @Test
    void execute() {
        try {
            goToPersonalProfileViewModel.setState(testState);
            GoToPersonalProfilePresenter goToPersonalProfilePresenter = new GoToPersonalProfilePresenter(goToPersonalProfileViewModel, viewManagerModel);
            GoToPersonalProfileInteractor testInteractor = new GoToPersonalProfileInteractor(goToPersonalProfilePresenter, new JsonUserDataAccessObject("data/users.json"));
            testInteractor.execute();
        } catch (NullPointerException e){
            fail();

        }

    }
    @Test
    void testGoToPersonalProfileControllerSuccess(){
        try{
            goToPersonalProfileViewModel.setState(testState);
            GoToPersonalProfilePresenter goToPersonalProfilePresenter = new GoToPersonalProfilePresenter(goToPersonalProfileViewModel, viewManagerModel);
            GoToPersonalProfileInteractor testInteractor = new GoToPersonalProfileInteractor(goToPersonalProfilePresenter, dataAccessObject);
            GoToPersonalProfileController controller = new GoToPersonalProfileController(testInteractor);

            controller.execute();
        } catch (NullPointerException e){
            fail();
        }
    }
    @Test
    void testGoToPersonalProfileControllerFail(){
        try{
            goToPersonalProfileViewModel.setState(testState);
            GoToPersonalProfilePresenter goToPersonalProfilePresenter = new GoToPersonalProfilePresenter(goToPersonalProfileViewModel, viewManagerModel);
            GoToPersonalProfileInteractor testInteractor = new GoToPersonalProfileInteractor(goToPersonalProfilePresenter, null);

            testInteractor.execute();
            fail();

        } catch (NullPointerException e){

        }

    }
    @Test
    void getUserID() {

        assertEquals(null, testState.getUserid());
    }

    @Test
    void getNickname() {
        assertEquals(null, testState.getNickname());
    }

    @Test
    void setNickname() {
        assertEquals(null, testState.getNickname());
    }
    @Test
    void setAndGetPronouns() {
        assertEquals(null, testState.getPronouns());
    }

    @Test
    void setAndGetDescription() {
        assertEquals(null, testState.getDescription());
    }
    @Test
    void setUserId_ShouldSetUserId() {
        String userId = "12345";
        testState.setUserid(userId);
        assertEquals(userId, testState.getUserid());
    }

    @Test
    void setNickname_ShouldSetNickname() {
        String nickname = "nickname";
        testState.setNickname(nickname);
        assertEquals(nickname, testState.getNickname());
    }

    @Test
    void setPronouns_ShouldSetPronouns() {
        String pronouns = "they/them";
        testState.setPronouns(pronouns);
        assertEquals(pronouns, testState.getPronouns());
    }

    @Test
    void addAndGetPersonalityTag() {
        assertEquals(0, testState.getPersonalityTags().size());
    }

    @Test
    void editCourseToTeach() {
        assertEquals(0, testState.getCoursesToTeach().size());
        assertEquals(0, testState.getCoursesToTeach().size());
    }

    @Test
    void editCourseToLearn() {
        assertEquals(0, testState.getCoursesToLearn().size());
        assertEquals(0, testState.getCoursesToLearn().size());
    }

    @Test
    void editTutorAvailability() {
        assertEquals(null, testState.getTutorAvailability());
    }

    @Test
    void updateTutorRating() {
        assertEquals(0, testState.getTutorRating());
        assertEquals(0, testState.getTutorRating());
        assertEquals(0, testState.getTutorRating());
    }

    @Test
    void updateStudentRating() {
        assertEquals(0, testState.getStudentRating());
        assertEquals(0, testState.getStudentRating());
        assertEquals(0, testState.getStudentRating());
    }

    @Test
    void editPreferredModeOfTeaching() {
        assertEquals(0, testState.getPreferredModeOfTeaching().size());
    }

    @Test
    void editPreferredModeOfLearning() {

        assertEquals(0, testState.getPreferredModeOfLearning().size());

    }
    @Test
    void setAndGetExpectedWage() {
        assertEquals(0, testState.getExpectedWage().size());

    }
    @Test
    void setAndGetExpectedPrice() {
        assertEquals(0, testState.getExpectedPrice().size());

    }
}
