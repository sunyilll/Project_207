package interface_adapter;

import interface_adapter.search_course_result.SearchCourseResultState;
import interface_adapter.search_course_result.SearchCourseResultViewModel;
import org.junit.jupiter.api.Test;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class SearchCourseResultTest {
    private SearchCourseResultViewModel  searchCourseResultViewModel = new SearchCourseResultViewModel();
    private boolean propertyChanged = true;

    @Test
    void setAndGetSortedIds() {
        SearchCourseResultState state = new SearchCourseResultState();
        List<String> ids = Arrays.asList("id1", "id2", "id3");
        state.setSortedIds(ids);
        assertEquals(ids, state.getSortedIds());
        assertEquals(Integer.valueOf(ids.size()), state.getNumbersResults());
    }

    @Test
    void setAndGetResultUsers() {
        SearchCourseResultState state = new SearchCourseResultState();
        Map<String, Map<String, String>> users = new HashMap<>();
        users.put("user1", new HashMap<>());
        state.setResultUsers(users);
        assertEquals(users, state.getResultUsers());
    }

    @Test
    void setAndGetResultUserTags() {
        SearchCourseResultState state = new SearchCourseResultState();
        Map<String, List<String>> tags = new HashMap<>();
        tags.put("user1", Arrays.asList("tag1", "tag2"));

        Map<String, Map<String, String>> users = new HashMap<>();
        users.put("user1", new HashMap<>());
        state.setResultUsers(users);

        state.setResultUserTags(tags);
        assertEquals(tags, state.getResultUserTags());
    }

    @Test
    void setAndGetCourseCode() {
        SearchCourseResultState state = new SearchCourseResultState();
        String courseCode = "CS101";
        state.setCourseCode(courseCode);
        assertEquals(courseCode, state.getCourseCode());
    }

    @Test
    void setAndGetSearchForTutor() {
        SearchCourseResultState state = new SearchCourseResultState();
        state.setSearchForTutor(true);
        assertTrue(state.getSearchForTutor());
    }

    @Test
    void setAndGetError() {
        SearchCourseResultState state = new SearchCourseResultState();
        String error = "Error message";
        state.setError(error);
        assertEquals(error, state.getError());
    }
    @Test
    void getState_ShouldReturnCurrentState() {
        // Arrange
        SearchCourseResultViewModel viewModel = new SearchCourseResultViewModel();
        SearchCourseResultState expectedState = new SearchCourseResultState();
        viewModel.setState(expectedState);

        // Act
        SearchCourseResultState actualState = viewModel.getState();

        // Assert
        assertEquals(expectedState, actualState, "The returned state should match the expected state.");
    }


    @Test
    void testAddPropertyChangeListener() {
        Map<String, Map<String, String>> users = new HashMap<>();
        users.put("user1", new HashMap<>());
        users.put("user2", new HashMap<>());

        SearchCourseResultViewModel sendMessageViewModel = new SearchCourseResultViewModel();

        PropertyChangeListener listener = evt -> propertyChanged = true;

        sendMessageViewModel.addPropertyChangeListener(listener);

        SearchCourseResultState initialState = new SearchCourseResultState();
        initialState.setResultUsers(users);
        searchCourseResultViewModel.setState(initialState);

        SearchCourseResultState newState = new SearchCourseResultState();
        newState.setResultUsers(users);
        sendMessageViewModel.setState(newState);

        assertTrue(propertyChanged, "The property change listener should have been triggered.");
    }
    @Test
    void firePropertyChanged() {
        // Arrange
        SearchCourseResultViewModel viewModel = new SearchCourseResultViewModel();
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
}
