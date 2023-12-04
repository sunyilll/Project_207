package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.add_course_to_profile.AddCourseToProfileController;
import interface_adapter.add_course_to_profile.AddCourseToProfilePresenter;
import interface_adapter.go_to_chat_list.GoToChatListController;
import interface_adapter.go_to_chat_list.GoToChatListPresenter;
import interface_adapter.go_to_chat_list.GoToChatListViewModel;
import interface_adapter.go_to_personal_profile.GoToPersonalProfileController;
import interface_adapter.go_to_personal_profile.GoToPersonalProfilePresenter;
import interface_adapter.go_to_personal_profile.GoToPersonalProfileViewModel;
import interface_adapter.go_to_public_profile.GoToPublicProfileController;
import interface_adapter.go_to_public_profile.GoToPublicProfilePresenter;
import interface_adapter.go_to_public_profile.GoToPublicProfileViewModel;
import interface_adapter.go_to_search.GoToSearchController;
import interface_adapter.go_to_search.GoToSearchPresenter;
import interface_adapter.search_course.SearchCourseViewModel;
import interface_adapter.search_course_result.SearchCourseResultViewModel;
import use_case.GetUserDataAccessInterface;
import use_case.add_course_to_profile.AddCourseToProfileDataAccessInterface;
import use_case.add_course_to_profile.AddCourseToProfileInputBoundary;
import use_case.add_course_to_profile.AddCourseToProfileInteractor;
import use_case.add_course_to_profile.AddCourseToProfileOutputBoundary;
import use_case.go_to_chat_list.GoToChatListDataAccessInterface;
import use_case.go_to_chat_list.GoToChatListInputBoundary;
import use_case.go_to_chat_list.GoToChatListInteractor;
import use_case.go_to_chat_list.GoToChatListOutputBoundary;
import use_case.go_to_personal_profile.GoToPersonalProfileDataAccessInterface;
import use_case.go_to_personal_profile.GoToPersonalProfileInputBoundary;
import use_case.go_to_personal_profile.GoToPersonalProfileInteractor;
import use_case.go_to_personal_profile.GoToPersonalProfileOutputBoundary;
import use_case.go_to_public_profile.GoToPublicProfileDataAccessInterface;
import use_case.go_to_public_profile.GoToPublicProfileInputBoundary;
import use_case.go_to_public_profile.GoToPublicProfileInteractor;
import use_case.go_to_public_profile.GoToPublicProfileOutputBoundary;
import use_case.go_to_search.GoToSearchInputBoundary;
import use_case.go_to_search.GoToSearchInteractor;
import view.SearchCourseResultView;

import javax.swing.*;
import java.io.IOException;

public class SearchCourseResultUseCaseFactory {
    private SearchCourseResultUseCaseFactory(){}
    public static SearchCourseResultView create(
            ViewManagerModel viewManagerModel,
            SearchCourseViewModel searchCourseViewModel,
            SearchCourseResultViewModel searchCourseResultViewModel,
            GoToPersonalProfileViewModel goToPersonalProfileViewModel,
            GoToPersonalProfileDataAccessInterface goToPersonalProfileDataAccessObject,
            GoToChatListViewModel goToChatListViewModel,
            GoToChatListDataAccessInterface goToChatListDataAccessObject,
            AddCourseToProfileDataAccessInterface addCourseToProfileDAI,
            GetUserDataAccessInterface getUserDAI,
            GoToPublicProfileViewModel goToPublicProfileViewModel,
            GoToPublicProfileDataAccessInterface goToPublicProfileDataAccessInterface
    ){
        try{
            GoToSearchController goToSearchController = createGoToSearchUseCase(viewManagerModel, searchCourseViewModel);
            GoToPersonalProfileController goToPersonalProfileController = createGoToPersonalProfileUseCase(viewManagerModel,
                    goToPersonalProfileViewModel, goToPersonalProfileDataAccessObject);
            GoToChatListController goToChatListController = createGoToChatListUseCase(viewManagerModel,
                    goToChatListViewModel, goToChatListDataAccessObject);
            AddCourseToProfileController addCourseToProfileController = createAddCourseProfileController(searchCourseResultViewModel,
                    addCourseToProfileDAI, getUserDAI);
            GoToPublicProfileController goToPublicProfileController = createGoToPublicProfileController(goToPublicProfileViewModel, viewManagerModel, goToPublicProfileDataAccessInterface);
            return new SearchCourseResultView(searchCourseResultViewModel, goToSearchController, goToChatListController, goToChatListViewModel,
                    goToPersonalProfileController, goToPersonalProfileViewModel, addCourseToProfileController, goToPublicProfileController);
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
            GoToPersonalProfileViewModel goToPersonalProfileViewModel,
            GoToPersonalProfileDataAccessInterface goToPersonalProfileDataAccessObject) throws IOException {

        GoToPersonalProfileOutputBoundary personalProfilePresenter =
                new GoToPersonalProfilePresenter(goToPersonalProfileViewModel, viewManagerModel);

        GoToPersonalProfileInputBoundary personalProfileInteractor =
                new GoToPersonalProfileInteractor(personalProfilePresenter, goToPersonalProfileDataAccessObject);

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

    private static AddCourseToProfileController createAddCourseProfileController(
            SearchCourseResultViewModel searchCourseResultViewModel,
            AddCourseToProfileDataAccessInterface addCourseToProfileDataAccessInterface,
            GetUserDataAccessInterface getUserDataAccessInterface
    ){
        AddCourseToProfileOutputBoundary presenter = new AddCourseToProfilePresenter(searchCourseResultViewModel);
        AddCourseToProfileInputBoundary interator = new AddCourseToProfileInteractor(addCourseToProfileDataAccessInterface,
                getUserDataAccessInterface, presenter);
        return new AddCourseToProfileController(interator);
    }
    private static GoToPublicProfileController createGoToPublicProfileController(
            GoToPublicProfileViewModel goToPublicProfileViewModel,
            ViewManagerModel viewManagerModel,
            GoToPublicProfileDataAccessInterface dao
    ){
        GoToPublicProfileOutputBoundary presenter = new GoToPublicProfilePresenter(goToPublicProfileViewModel, viewManagerModel);
        GoToPublicProfileInputBoundary interactor = new GoToPublicProfileInteractor(presenter, dao);
        return new GoToPublicProfileController(interactor);
    }
}
