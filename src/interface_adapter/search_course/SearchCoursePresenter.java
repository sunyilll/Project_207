package interface_adapter.search_course;

import interface_adapter.ViewManagerModel;
import interface_adapter.search_course_result.SearchCourseResultState;
import interface_adapter.search_course_result.SearchCourseResultViewModel;
import use_case.search_course.SearchCourseOutputBoundary;
import use_case.search_course.SearchCourseOutputData;

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
        resultState.setResultUsers(users.getUsersInfo());
        resultState.setResultUserTags(users.getUsers_tags());
        resultState.setSortedIds(users.getSortedUserIds());
        resultState.setCourseCode(users.getCourseCode());
        resultState.setSearchForTutor(users.getSearchForTutor());
        resultState.setCourseAddedToProfile(false);
        searchCourseResultViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(searchCourseResultViewModel.getViewName());
        viewManagerModel.firePropertyChanged();

    }
    @Override
    public void prepareFailView(String error) {
        SearchCourseState searchState = searchCourseViewModel.getState();
        searchState.setError(error);
        searchCourseViewModel.firePropertyChanged();
    }
}
