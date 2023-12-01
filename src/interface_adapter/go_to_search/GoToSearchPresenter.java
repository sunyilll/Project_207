package interface_adapter.go_to_search;

import interface_adapter.ViewManagerModel;
import interface_adapter.search_course.SearchCourseState;
import interface_adapter.search_course.SearchCourseViewModel;
import use_case.go_to_search.GoToSearchOutputBoundary;

public class GoToSearchPresenter implements GoToSearchOutputBoundary {
    private final SearchCourseViewModel viewModel;
    private ViewManagerModel viewManager;
    public GoToSearchPresenter(SearchCourseViewModel searchCourseViewModel, ViewManagerModel viewManager) {
        this.viewModel = searchCourseViewModel;
        this.viewManager = viewManager;
    }
    @Override
    public void prepareSuccessView() {
        SearchCourseState s = viewModel.getState();
        //todo: 改变s，加入search not selected，更改两个buttons的setdisable
        viewManager.setActiveView(viewModel.getViewName());
        viewManager.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        //todo: implement me
    }
}
