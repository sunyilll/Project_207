package main.java.interface_adapter.search_course;

import main.java.interface_adapter.ViewManagerModel;
import main.java.interface_adapter.search_course_result.SearchCourseResultState;
import main.java.interface_adapter.search_course_result.SearchCourseResultViewModel;
import main.java.use_case.search_course.SearchCourseOutputBoundary;
import main.java.use_case.search_course.SearchCourseOutputData;

public class SearchCoursePresenter implements SearchCourseOutputBoundary {
    private ViewManagerModel viewManagerModel;
    private final SearchCourseViewModel searchCourseViewModel;
    private final SearchCourseResultViewModel searchCourseResultViewModel;
    public SearchCoursePresenter(ViewManagerModel viewManagerModel,
                                 SearchCourseViewModel searchCourseViewModel,
                                 SearchCourseResultViewModel searchCourseResultViewModel){
        this.viewManagerModel = viewManagerModel;
        this.searchCourseViewModel = searchCourseViewModel;
        this.searchCourseResultViewModel = searchCourseResultViewModel;
    }

    @Override
    public void prepareSuccessView(SearchCourseOutputData users) {
        SearchCourseResultState resultState = searchCourseResultViewModel.getState();
         // resultState.setUsersName(users.getUsersInfo()); // todo: implement me
        searchCourseResultViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(searchCourseResultViewModel.getViewName());
        viewManagerModel.firePropertyChanged();

    }
    @Override
    public void prepareFailView(String error) {
        SearchCourseState searchState = searchCourseViewModel.getState();
        searchState.setCourseCodeError(error);
        searchCourseViewModel.firePropertyChanged();
    }
}
