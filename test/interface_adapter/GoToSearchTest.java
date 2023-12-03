package interface_adapter;

import interface_adapter.go_to_search.GoToSearchController;
import interface_adapter.go_to_search.GoToSearchPresenter;
import interface_adapter.search_course.SearchCourseState;
import interface_adapter.search_course.SearchCourseViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.go_to_search.GoToSearchInputBoundary;
import use_case.go_to_search.GoToSearchInteractor;
import use_case.go_to_search.GoToSearchOutputBoundary;

import static junit.framework.TestCase.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class GoToSearchTest {

    private GoToSearchController goToSearchController;
    private MockGoToSearchInputBoundary mockInputBoundary;
    private SearchCourseViewModel mockViewModel = new MockSearchCourseViewModel();
    private ViewManagerModel mockViewManager = new MockViewManagerModel();
    private GoToSearchPresenter goToSearchPresenter;

    @BeforeEach
    void setUp() {
        // Initialize the fake input boundary
        mockInputBoundary = new MockGoToSearchInputBoundary();
        // Create the controller with the fake interactor
        goToSearchController = new GoToSearchController(mockInputBoundary);

    }

    @Test
    void testController() {
        // Act
        goToSearchController.execute();

        // Assert
        assertTrue(mockInputBoundary.isExecuteCalled());
    }
    @Test
    void testPrepareSuccessView() {
        GoToSearchPresenter goToSearchPresenter = new GoToSearchPresenter(mockViewModel,mockViewManager);
        GoToSearchInteractor goToSearchInteractor = new GoToSearchInteractor(goToSearchPresenter);

        GoToSearchController goToSearchController = new GoToSearchController(goToSearchInteractor);
        goToSearchController.execute();
        mockViewModel.getState().setSearchTypeSelected(true);

        // Assert
        assertTrue(mockViewModel.getState().getSearchTypeSelected());}

    @Test
    void testPrepareFailView() {
        GoToSearchPresenter goToSearchPresenter = new GoToSearchPresenter(mockViewModel,mockViewManager);
        GoToSearchInteractor goToSearchInteractor = new GoToSearchInteractor(goToSearchPresenter);

        GoToSearchController goToSearchController = new GoToSearchController(goToSearchInteractor);
        goToSearchController.execute();

        // Assert
        assertFalse(mockViewModel.getState().getSearchTypeSelected());
    }


    private static class MockGoToSearchInputBoundary implements GoToSearchInputBoundary {
        private boolean executeCalled = false;

        @Override
        public void execute() {
            executeCalled = true;
        }

        public boolean isExecuteCalled() {
            return executeCalled;
        }
    }
    class MockSearchCourseViewModel extends SearchCourseViewModel {
        private SearchCourseState state = new SearchCourseState();

        @Override
        public SearchCourseState getState() {
            return this.state;
        }

        @Override
        public void setState(SearchCourseState state) {
            this.state = state;
        }
    }

    class MockViewManagerModel extends ViewManagerModel {
        boolean propertyChangedFired = false;
        String activeViewName;

        @Override
        public void setActiveView(String viewName) {
            this.activeViewName = viewName;
        }

        @Override
        public void firePropertyChanged() {
            propertyChangedFired = true;
        }
    }
    public class RealGoToSearchPresenter implements GoToSearchOutputBoundary {
        private SearchCourseViewModel viewModel;
        private ViewManagerModel viewManager;

        public RealGoToSearchPresenter(SearchCourseViewModel viewModel, ViewManagerModel viewManager) {
            this.viewModel = viewModel;
            this.viewManager = viewManager;
        }

        @Override
        public void prepareSuccessView() {
            SearchCourseState state = viewModel.getState();
            state.setSearchTypeSelected(true); // This will set the state to true
            viewModel.firePropertyChanged();
            viewManager.setActiveView(viewModel.getViewName());
            viewManager.firePropertyChanged();
        }

        }
    }

