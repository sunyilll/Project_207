package main.java.app;

import main.java.interface_adapter.ViewManagerModel;
import main.java.interface_adapter.go_to_chatl_list.GoToChatListController;
import main.java.interface_adapter.go_to_chatl_list.GoToChatListPresenter;
import main.java.interface_adapter.go_to_chatl_list.GoToChatListViewModel;
import main.java.interface_adapter.go_to_personal_profile.GoToPersonalProfileController;
import main.java.interface_adapter.go_to_personal_profile.GoToPersonalProfilePresenter;
import main.java.interface_adapter.go_to_personal_profile.GoToPersonalProfileViewModel;
import main.java.interface_adapter.go_to_search.GoToSearchController;
import main.java.interface_adapter.go_to_search.GoToSearchPresenter;
import main.java.interface_adapter.search_course.SearchCourseViewModel;
import main.java.interface_adapter.search_course_result.SearchCourseResultViewModel;
import main.java.use_case.go_to_chat_list.GoToChatListDataAccessInterface;
import main.java.use_case.go_to_chat_list.GoToChatListInputBoundary;
import main.java.use_case.go_to_chat_list.GoToChatListInteractor;
import main.java.use_case.go_to_chat_list.GoToChatListOutputBoundary;
import main.java.use_case.go_to_personal_profile.GoToPersonalProfileInputBoundary;
import main.java.use_case.go_to_personal_profile.GoToPersonalProfileInteractor;
import main.java.use_case.go_to_personal_profile.GoToPersonalProfileOutputBoundary;
import main.java.use_case.go_to_search.GoToSearchInputBoundary;
import main.java.use_case.go_to_search.GoToSearchInteractor;
import main.java.view.SearchCourseResultView;

import javax.swing.*;
import java.io.IOException;

public class SearchCourseResultUseCaseFactory {
    private SearchCourseResultUseCaseFactory(){}
    public static SearchCourseResultView create(
            ViewManagerModel viewManagerModel,
            SearchCourseViewModel searchCourseViewModel,
            SearchCourseResultViewModel searchCourseResultViewModel,
            GoToPersonalProfileViewModel goToPersonalProfileViewModel,
            GoToChatListViewModel goToChatListViewModel,
            GoToChatListDataAccessInterface goToChatListDataAccessObject
    ){
        try{
            GoToSearchController goToSearchController = createGoToSearchUseCase(viewManagerModel, searchCourseViewModel);
            GoToPersonalProfileController goToPersonalProfileController = createGoToPersonalProfileUseCase(viewManagerModel,
                    goToPersonalProfileViewModel);
            GoToChatListController goToChatListController = createGoToChatListUseCase(viewManagerModel,
                    goToChatListViewModel, goToChatListDataAccessObject);
            return new SearchCourseResultView(searchCourseResultViewModel, goToSearchController, goToChatListController,
                    goToChatListViewModel, goToPersonalProfileController, goToPersonalProfileViewModel);
        } catch (IOException e){
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }
        return null;

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
