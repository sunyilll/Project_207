package use_case.go_to_search;

import entity.ChatChannel;
import entity.User;
import interface_adapter.ViewManagerModel;
import interface_adapter.go_to_search.GoToSearchPresenter;
import interface_adapter.search_course.SearchCourseState;
import interface_adapter.search_course.SearchCourseViewModel;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class GoToSearchInteractorTest {
    private final Map<String, User> testMap = new HashMap<>();
    private final LocalDateTime currentDateTime = LocalDateTime.now();
    private final ChatChannel channel = new ChatChannel(testMap, currentDateTime, "sendbird_group_channel_12586989_cbf2eb24180c0399084a22b8acb6519571db23f7");
    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
    private final SearchCourseViewModel searchCourseViewModel = new SearchCourseViewModel();
    private final SearchCourseState searchCourseState = new SearchCourseState();
    @Test
    void execute() {
        searchCourseViewModel.setState(searchCourseState);
        GoToSearchPresenter goToSearchPresenter = new GoToSearchPresenter(searchCourseViewModel, viewManagerModel);
        GoToSearchInteractor testInteractor = new GoToSearchInteractor(goToSearchPresenter);
        testInteractor.execute();
        assertEquals("search course", viewManagerModel.getActiveView());
    }
}