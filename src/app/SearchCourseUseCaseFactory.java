package app;

import algorithmn.MatchStudentAlgorithm;
import algorithmn.MatchTutorAlgorithm;
import interface_adapter.ViewManagerModel;
import interface_adapter.go_to_chatl_list.GoToChatListController;
import interface_adapter.go_to_chatl_list.GoToChatListPresenter;
import interface_adapter.go_to_chatl_list.GoToChatListViewModel;
import interface_adapter.go_to_personal_profile.GoToPersonalProfileController;
import interface_adapter.go_to_personal_profile.GoToPersonalProfilePresenter;
import interface_adapter.go_to_personal_profile.GoToPersonalProfileViewModel;
import interface_adapter.go_to_search.GoToSearchController;
import interface_adapter.go_to_search.GoToSearchPresenter;
import interface_adapter.search_course.SearchCourseController;
import interface_adapter.search_course.SearchCoursePresenter;
import interface_adapter.search_course.SearchCourseViewModel;
import interface_adapter.search_course_result.SearchCourseResultViewModel;
import use_case.GetUserDataAccessInterface;
import use_case.go_to_chat_list.GoToChatListDataAccessInterface;
import use_case.go_to_chat_list.GoToChatListInputBoundary;
import use_case.go_to_chat_list.GoToChatListInteractor;
import use_case.go_to_chat_list.GoToChatListOutputBoundary;
import use_case.go_to_personal_profile.GoToPersonalProfileInputBoundary;
import use_case.go_to_personal_profile.GoToPersonalProfileInteractor;
import use_case.go_to_personal_profile.GoToPersonalProfileOutputBoundary;
import use_case.go_to_search.GoToSearchInputBoundary;
import use_case.go_to_search.GoToSearchInteractor;
import use_case.search_course.SearchCourseDataAccessInterface;
import use_case.search_course.SearchCourseInputBoundary;
import use_case.search_course.SearchCourseInteractor;
import use_case.search_course.SearchCourseOutputBoundary;
import view.SearchCourseView;

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
            GoToChatListDataAccessInterface goToChatListDataAccessObject,
            MatchTutorAlgorithm matchTutorAlgorithm,
            MatchStudentAlgorithm matchStudentAlgorithm){
        try {
            GoToSearchController goToSearchController = createGoToSearchUseCase(viewManagerModel, searchCourseViewModel);
            GoToPersonalProfileController goToPersonalProfileController = createGoToPersonalProfileUseCase(viewManagerModel,
                    goToPersonalProfileViewModel);
            GoToChatListController goToChatListController = createGoToChatListUseCase(viewManagerModel,
                    goToChatListViewModel, goToChatListDataAccessObject);
            SearchCourseController searchController = createSearchUseCase(viewManagerModel, searchCourseViewModel, searchCourseResultViewModel, searchCourseDAO,
                    getUserDAO, matchTutorAlgorithm, matchStudentAlgorithm);
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
            GetUserDataAccessInterface getUserDAO,
            MatchTutorAlgorithm matchTutorAlgorithm,
            MatchStudentAlgorithm matchStudentAlgorithm
    ) throws IOException {
        SearchCourseOutputBoundary searchCoursePresenter = new SearchCoursePresenter(viewManagerModel, searchCourseViewModel, searchCourseResultViewModel);
        SearchCourseInputBoundary searchCourseInteractor = new SearchCourseInteractor(searchCourseDAO, searchCoursePresenter,
                getUserDAO, matchTutorAlgorithm, matchStudentAlgorithm);
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
