package main.java.app;

import main.java.data_access.FileCourseDataAccessObject;
import main.java.interface_adapter.ViewManagerModel;
import main.java.interface_adapter.go_to_chatl_list.GoToChatListController;
import main.java.interface_adapter.go_to_chatl_list.GoToChatListPresenter;
import main.java.interface_adapter.go_to_chatl_list.GoToChatListViewModel;
import main.java.interface_adapter.go_to_personal_profile.GoToPersonalProfileController;
import main.java.interface_adapter.go_to_personal_profile.GoToPersonalProfilePresenter;
import main.java.interface_adapter.go_to_personal_profile.GoToPersonalProfileViewModel;
import main.java.interface_adapter.go_to_search.GoToSearchController;
import main.java.interface_adapter.go_to_search.GoToSearchPresenter;
import main.java.interface_adapter.search_course.SearchCourseController;
import main.java.interface_adapter.search_course.SearchCoursePresenter;
import main.java.interface_adapter.search_course.SearchCourseState;
import main.java.interface_adapter.search_course.SearchCourseViewModel;
import main.java.interface_adapter.search_course_result.SearchCourseResultViewModel;
import main.java.use_case.GetUserDataAccessInterface;
import main.java.use_case.go_to_chat_list.GoToChatListDataAccessInterface;
import main.java.use_case.go_to_chat_list.GoToChatListInputBoundary;
import main.java.use_case.go_to_chat_list.GoToChatListInteractor;
import main.java.use_case.go_to_chat_list.GoToChatListOutputBoundary;
import main.java.use_case.go_to_personal_profile.GoToPersonalProfileInputBoundary;
import main.java.use_case.go_to_personal_profile.GoToPersonalProfileInteractor;
import main.java.use_case.go_to_personal_profile.GoToPersonalProfileOutputBoundary;
import main.java.use_case.go_to_search.GoToSearchInputBoundary;
import main.java.use_case.go_to_search.GoToSearchInteractor;
import main.java.use_case.login.LoginUserDataAccessInterface;
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
            SearchCourseDataAccessInterface searchCourseDAO,
            GetUserDataAccessInterface getUserDAO,
            GoToPersonalProfileViewModel goToPersonalProfileViewModel,
            GoToChatListViewModel goToChatListViewModel,
            GoToChatListDataAccessInterface goToChatListDataAccessObject){
        try {
            GoToSearchController goToSearchController = createGoToSearchUseCase(viewManagerModel, searchCourseViewModel);
            GoToPersonalProfileController goToPersonalProfileController = createGoToPersonalProfileUseCase(viewManagerModel,
                    goToPersonalProfileViewModel);
            GoToChatListController goToChatListController = createGoToChatListUseCase(viewManagerModel,
                    goToChatListViewModel, goToChatListDataAccessObject);
            SearchCourseController searchController = createSearchUseCase(viewManagerModel, searchCourseViewModel, searchCourseResultViewModel, searchCourseDAO, getUserDAO);
            return new SearchCourseView(searchCourseViewModel, searchController,
                    goToPersonalProfileViewModel, goToPersonalProfileController, goToChatListViewModel, goToChatListController, goToSearchController);
        } catch (IOException e){
            JOptionPane.showMessageDialog(null, "Fail to Create SearchCourseView in Factory. Idk WHY!");
        }
        return null;
    }

    private static SearchCourseController createSearchUseCase(
            ViewManagerModel viewManagerModel,
            SearchCourseViewModel searchCourseViewModel,
            SearchCourseResultViewModel searchCourseResultViewModel,
            SearchCourseDataAccessInterface searchCourseDAO,
            GetUserDataAccessInterface getUserDAO) throws IOException {
        SearchCourseOutputBoundary searchCoursePresenter = new SearchCoursePresenter(viewManagerModel, searchCourseViewModel, searchCourseResultViewModel);
        SearchCourseInputBoundary searchCourseInteractor = new SearchCourseInteractor(searchCourseDAO, searchCoursePresenter, getUserDAO);
        return new SearchCourseController(searchCourseInteractor);
    }

    private static GoToSearchController createGoToSearchUseCase(
            ViewManagerModel viewManagerModel,
            SearchCourseViewModel searchCourseViewModel
    ){
        GoToSearchPresenter presenter = new GoToSearchPresenter(searchCourseViewModel, viewManagerModel);
        GoToSearchInputBoundary interactor = new GoToSearchInteractor(presenter);
        return new GoToSearchController(interactor);
    }

    private static GoToPersonalProfileController createGoToPersonalProfileUseCase(
            ViewManagerModel viewManagerModel,
            GoToPersonalProfileViewModel goToPersonalProfileViewModel) throws IOException {

        GoToPersonalProfileOutputBoundary personalProfilePresenter =
                new GoToPersonalProfilePresenter(goToPersonalProfileViewModel, viewManagerModel);

        GoToPersonalProfileInputBoundary personalProfileInteractor =
                new GoToPersonalProfileInteractor(personalProfilePresenter);

        return new GoToPersonalProfileController(personalProfileInteractor);
    }

    private static GoToChatListController createGoToChatListUseCase(
            ViewManagerModel viewManagerModel,
            GoToChatListViewModel goToChatListViewModel,
            GoToChatListDataAccessInterface goToChatListDataAccessObject) throws IOException {

        GoToChatListOutputBoundary goToChatListPresenter =
                new GoToChatListPresenter(goToChatListViewModel, viewManagerModel);

        GoToChatListInputBoundary goToChatListInteractor =
                new GoToChatListInteractor(goToChatListDataAccessObject, goToChatListPresenter);

        return new GoToChatListController(goToChatListInteractor);
    }
}
