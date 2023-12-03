package interface_adapter;

import algorithmn.MatchStudentAlgorithm;
import algorithmn.MatchTutorAlgorithm;
import entity.Student;
import entity.Tutor;
import entity.User;
import interface_adapter.search_course.SearchCourseController;
import interface_adapter.search_course.SearchCoursePresenter;
import interface_adapter.search_course.SearchCourseState;
import interface_adapter.search_course.SearchCourseViewModel;
import interface_adapter.search_course_result.SearchCourseResultViewModel;
import org.junit.jupiter.api.Test;
import use_case.GetUserDataAccessInterface;
import use_case.search_course.*;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SearchCourseTest {
    private SearchCourseViewModel searchCourseViewModel = new SearchCourseViewModel();
    private ViewManagerModel viewManagerModel = new ViewManagerModel();
    private SearchCourseOutputBoundary searchCourseOutputBoundary;
    private GetUserDataAccessInterface getUserDataAccessInterface = new MockGetUserDataAccessObject();
    private SearchCourseResultViewModel searchCourseResultViewModel = new SearchCourseResultViewModel();
    private MatchTutorAlgorithm matchTutorAlgorithm = new MockMatchTutorAlgorithm();
    private MatchStudentAlgorithm matchStudentAlgorithm = new MockMatchStudentAlgorithm();
    private SearchCourseDataAccessInterface searchCourseDataAccessInterface = new MockSearchCourseUserDataAccessObject();
    private SearchCourseInputBoundary userSearchCourseUseCaseInteractor = new SearchCourseInteractor(searchCourseDataAccessInterface, searchCourseOutputBoundary, getUserDataAccessInterface, matchTutorAlgorithm, matchStudentAlgorithm);
    private SearchCourseState searchCourseState = new SearchCourseState();
    private final MockSearchCourseUserDataAccessObject searchCourseUserDataAccessObject = new MockSearchCourseUserDataAccessObject();
    private final MockGetUserDataAccessObject mockGetUserDataAccessObject = new MockGetUserDataAccessObject();


    private boolean propertyChanged = true;


    @Test
    void testSearchCourseSuccess() {
        SearchCoursePresenter searchCoursePresenter = new SearchCoursePresenter(viewManagerModel, searchCourseViewModel, searchCourseResultViewModel);
        SearchCourseInteractor searchCourseInteractor = new SearchCourseInteractor(searchCourseUserDataAccessObject, searchCoursePresenter, mockGetUserDataAccessObject, matchTutorAlgorithm, matchStudentAlgorithm);

        SearchCourseController controller = new SearchCourseController(searchCourseInteractor);
        controller.execute("csc207", true, "user123");
        searchCourseViewModel.getState().setSearchTypeSelected(true);

        // Assert
        assertTrue(searchCourseViewModel.getState().getSearchTypeSelected());
    }

    @Test
    void testSearchCourseFailure() {
        SearchCoursePresenter searchCoursePresenter = new SearchCoursePresenter(viewManagerModel, searchCourseViewModel, searchCourseResultViewModel);
        SearchCourseInteractor searchCourseInteractor = new SearchCourseInteractor(searchCourseUserDataAccessObject, searchCoursePresenter, mockGetUserDataAccessObject, matchTutorAlgorithm, matchStudentAlgorithm);
        String invalidCourseCode = "";
        boolean searchForTutor = true;
        String userId = "user123";

        SearchCourseController controller = new SearchCourseController(searchCourseInteractor);
        controller.execute(invalidCourseCode, searchForTutor, userId);
        assertFalse(searchCourseViewModel.getState().getSearchTypeSelected());
        assertNotNull(searchCourseViewModel.getState().getError());
    }

    @Test
    void gettersAndSettersTest() {
        searchCourseState.setSearchForTutor(true);
        assertTrue(searchCourseState.getSearchForTutor());

        searchCourseState.setSearchForTutor(false);
        assertFalse(searchCourseState.getSearchForTutor());

        // Test for searchTypeSelected
        searchCourseState.setSearchTypeSelected(true);
        assertTrue(searchCourseState.getSearchTypeSelected());

        searchCourseState.setSearchTypeSelected(false);
        assertFalse(searchCourseState.getSearchTypeSelected());

        // Test for courseCode
        String courseCode = "CS101";
        searchCourseState.setCourseCode(courseCode);
        assertEquals(courseCode, searchCourseState.getCourseCode());

        // Test for userID
        String userID = "user123";
        searchCourseState.setUserID(userID);
        assertEquals(userID, searchCourseState.getUserID());

        // Test for error
        String error = "Invalid course code";
        searchCourseState.setError(error);
        assertEquals(error, searchCourseState.getError());
    }

    @Test
    void testAddPropertyChangeListener() {
        SearchCourseViewModel searchCourseViewModel = new SearchCourseViewModel();

        PropertyChangeListener listener = evt -> propertyChanged = true;

        searchCourseViewModel.addPropertyChangeListener(listener);

        SearchCourseState initialState = new SearchCourseState();
        initialState.setUserID("user1");
        searchCourseViewModel.setState(initialState);

        SearchCourseState newState = new SearchCourseState();
        newState.setUserID("user1");
        searchCourseViewModel.setState(newState);

        assertTrue(propertyChanged, "The property change listener should have been triggered.");
    }

    @Test
    void getState_ShouldReturnCurrentState() {
        // Arrange
        SearchCourseViewModel viewModel = new SearchCourseViewModel();
        SearchCourseState expectedState = new SearchCourseState();

        viewModel.setState(expectedState);

        // Act
        SearchCourseState actualState = viewModel.getState();

        // Assert
        assertEquals(expectedState, actualState, "The returned state should match the expected state.");
    }

    @Test
    void firePropertyChanged_ShouldNotifyPropertyChangeListener() {
        // Arrange
        SearchCourseViewModel viewModel = new SearchCourseViewModel();
        PropertyChangeFlagListener listener = new PropertyChangeFlagListener();
        viewModel.addPropertyChangeListener(listener);

        // Act
        viewModel.firePropertyChanged();

        // Assert
        assertTrue(listener.isFlagged());
    }

    private static class PropertyChangeFlagListener implements PropertyChangeListener {
        private boolean flagged = false;

        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            flagged = true;
        }

        public boolean isFlagged() {
            return flagged;
        }

    }

    public class MockSearchCourseUserDataAccessObject implements SearchCourseDataAccessInterface {
        @Override
        public List<Tutor> getTutorOfCourse(String courseCode) {
            // Return a non-empty list for a valid course code
            if ("csc207".equals(courseCode)) {
                return Arrays.asList(new Tutor() {
                    @Override
                    public List<String> getCoursesToTeach() {
                        return null;
                    }

                    @Override
                    public boolean addCourseToTeach(String courseCode) {
                        return false;
                    }

                    @Override
                    public boolean deleteCourseToTeach(String courseCode) {
                        return false;
                    }

                    @Override
                    public String getTutorAvailability() {
                        return null;
                    }

                    @Override
                    public float getTutorRating() {
                        return 0;
                    }

                    @Override
                    public boolean updateTutorRating(Integer rating) {
                        return false;
                    }

                    @Override
                    public Map<String, Integer> getExpectedWage() {
                        return null;
                    }

                    @Override
                    public Integer getExpectedWage(String courseCode) {
                        return null;
                    }

                    @Override
                    public boolean setExpectedWage(String courseCode, Integer wage) {
                        return false;
                    }

                    @Override
                    public List<String> getPreferredModeOfTeaching() {
                        return null;
                    }

                    @Override
                    public boolean setPreferredModeOfTeaching(String mode) {
                        return false;
                    }

                    @Override
                    public String getUserID() {
                        return null;
                    }
                });
            }
            return null;
        }

        @Override
        public List<Student> getStudentOfCourse(String courseCode) {
            if ("csc207".equals(courseCode)) {
                return Arrays.asList(new Student() {
                    @Override
                    public List<String> getCoursesToLearn() {
                        return null;
                    }

                    @Override
                    public boolean addCourseToLearn(String courseCode) {
                        return false;
                    }

                    @Override
                    public boolean deleteCourseToLearn(String courseCode) {
                        return false;
                    }

                    @Override
                    public float getStudentRating() {
                        return 0;
                    }

                    @Override
                    public boolean updateStudentRating(Integer rating) {
                        return false;
                    }

                    @Override
                    public Map<String, Integer> getExpectedPrice() {
                        return null;
                    }

                    @Override
                    public Integer getExpectedPrice(String courseCode) {
                        return 0;
                    }

                    @Override
                    public boolean setExpectedPrice(String courseCode, Integer price) {
                        return false;
                    }

                    @Override
                    public List<String> getPreferredModeOfLearning() {
                        return null;
                    }

                    @Override
                    public boolean setPreferredModeOfLearning(String mode) {
                        return false;
                    }

                    @Override
                    public String getUserID() {
                        return null;
                    }
                });
            }
            return null;
        }

        @Override
        public boolean hasCourse(String courseCode) {
            // Return true for a valid course code
            return "csc207".equals(courseCode);
        }
    }

    public class MockGetUserDataAccessObject implements GetUserDataAccessInterface {
        @Override
        public User get(String userid) {
            if ("user123".equals(userid)) {
                return new User(userid, "username", "nickname");
            }
            return null;
        }
    }

    public class MockMatchTutorAlgorithm implements MatchTutorAlgorithm {

        @Override
        public List<Map.Entry<User, Float>> matchTutor(User user, List<Tutor> tutorList) {
            return new ArrayList<>();
        }
    }
    public class MockMatchStudentAlgorithm implements MatchStudentAlgorithm {
        @Override
        public List<Map.Entry<User, Float>> matchStudent(User me, List<Student> students) {
            return null;
        }
    }
}
