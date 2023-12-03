package interface_adapter.add_course_to_profile;

import interface_adapter.search_course_result.SearchCourseResultState;
import interface_adapter.search_course_result.SearchCourseResultViewModel;
import use_case.add_course_to_profile.AddCourseToProfileOutputBoundary;

public class AddCourseToProfilePresenter implements AddCourseToProfileOutputBoundary {
    final SearchCourseResultViewModel searchCourseResultViewModel;
    public AddCourseToProfilePresenter(SearchCourseResultViewModel searchCourseResultViewModel){
        this.searchCourseResultViewModel = searchCourseResultViewModel;
    }
    @Override
    public void prepareSuccessView() {
        SearchCourseResultState s = searchCourseResultViewModel.getState();
        s.setCourseAddedToProfile(true);
        searchCourseResultViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String errorMessage) {

    }

}
