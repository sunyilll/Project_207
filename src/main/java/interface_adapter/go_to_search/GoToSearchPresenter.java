package main.java.interface_adapter.go_to_search;

import main.java.interface_adapter.ViewManagerModel;
import main.java.interface_adapter.go_to_personal_profile.GoToPersonalProfileState;
import main.java.interface_adapter.go_to_personal_profile.GoToPersonalProfileViewModel;
import main.java.interface_adapter.search_course.SearchCourseState;
import main.java.interface_adapter.search_course.SearchCourseViewModel;
import main.java.use_case.go_to_search.GoToSearchOutputBoundary;
import main.java.view.SearchCourseView;

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
