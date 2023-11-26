package main.java.app;

import main.java.data_access.FileCourseDataAccessObject;
import main.java.interface_adapter.ViewManagerModel;
import main.java.interface_adapter.search_course.SearchCourseController;
import main.java.interface_adapter.search_course.SearchCoursePresenter;
import main.java.interface_adapter.search_course.SearchCourseState;
import main.java.interface_adapter.search_course.SearchCourseViewModel;
import main.java.interface_adapter.search_course_result.SearchCourseResultViewModel;
import main.java.use_case.search_course.SearchCourseDataAccessInterface;
import main.java.use_case.search_course.SearchCourseInputBoundary;
import main.java.use_case.search_course.SearchCourseInteractor;
import main.java.use_case.search_course.SearchCourseOutputBoundary;
import main.java.view.SearchCourseView;

import javax.swing.*;
import java.io.IOException;

public class SearchCourseUseCaseFactory {
    private SearchCourseUseCaseFactory(){}  // avoid intialization
    public static SearchCourseView create(
            ViewManagerModel viewManagerModel,
            SearchCourseViewModel searchCourseViewModel,
            SearchCourseResultViewModel searchCourseResultViewModel,
            SearchCourseDataAccessInterface searchCourseDAO){
        try {
            SearchCourseController controller = createSearchUseCase(viewManagerModel, searchCourseViewModel, searchCourseResultViewModel, searchCourseDAO);
            return new SearchCourseView(searchCourseViewModel, controller);
        } catch (IOException e){
            JOptionPane.showMessageDialog(null, "Fail to Create SearchCourseView in Factory. Idk WHY!");
        }
        return null;
    }

    private static SearchCourseController createSearchUseCase(
            ViewManagerModel viewManagerModel,
            SearchCourseViewModel searchCourseViewModel,
            SearchCourseResultViewModel searchCourseResultViewModel,
            SearchCourseDataAccessInterface searchCourseDAO) throws IOException {
        SearchCourseOutputBoundary searchCoursePresenter = new SearchCoursePresenter(viewManagerModel, searchCourseViewModel, searchCourseResultViewModel);
        SearchCourseInputBoundary searchCourseInteractor = new SearchCourseInteractor(searchCourseDAO, searchCoursePresenter);
        return new SearchCourseController(searchCourseInteractor);

    }
}
