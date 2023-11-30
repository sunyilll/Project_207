package main.java.app;

import main.java.interface_adapter.ViewManagerModel;
import main.java.interface_adapter.go_to_search.GoToSearchController;
import main.java.interface_adapter.go_to_search.GoToSearchPresenter;
import main.java.interface_adapter.search_course.SearchCourseViewModel;
import main.java.interface_adapter.search_course_result.SearchCourseResultViewModel;
import main.java.use_case.go_to_search.GoToSearchInputBoundary;
import main.java.use_case.go_to_search.GoToSearchInteractor;
import main.java.view.SearchCourseResultView;

public class SearchCourseResultUseCaseFactory {
    private SearchCourseResultUseCaseFactory(){}
    public static SearchCourseResultView create(
            ViewManagerModel viewManagerModel,
            SearchCourseViewModel searchCourseViewModel,
            SearchCourseResultViewModel searchCourseResultViewModel
    ){
        GoToSearchController goToSearchController = createGoToSearchUseCase(viewManagerModel, searchCourseViewModel);
        return new SearchCourseResultView(searchCourseResultViewModel, goToSearchController);
    }
    private static GoToSearchController createGoToSearchUseCase(
            ViewManagerModel viewManagerModel,
            SearchCourseViewModel searchCourseViewModel
    ){
        GoToSearchPresenter presenter = new GoToSearchPresenter(searchCourseViewModel, viewManagerModel);
        GoToSearchInputBoundary interactor = new GoToSearchInteractor(presenter);
        return new GoToSearchController(interactor);
    }
}
