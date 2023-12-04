package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.edit_profile.EditProfileController;
import interface_adapter.edit_profile.EditProfilePresenter;
import interface_adapter.edit_profile.EditProfileViewModel;
import interface_adapter.go_to_chat_list.GoToChatListController;
import interface_adapter.go_to_chat_list.GoToChatListPresenter;
import interface_adapter.go_to_chat_list.GoToChatListViewModel;
import interface_adapter.go_to_personal_profile.GoToPersonalProfileController;
import interface_adapter.go_to_personal_profile.GoToPersonalProfilePresenter;
import interface_adapter.go_to_personal_profile.GoToPersonalProfileViewModel;
import interface_adapter.go_to_search.GoToSearchController;
import interface_adapter.go_to_search.GoToSearchPresenter;
import interface_adapter.search_course.SearchCourseViewModel;
import use_case.edit_profile.EditProfileDataAccessInterface;
import use_case.edit_profile.EditProfileInputBoundary;
import use_case.edit_profile.EditProfileInteractor;
import use_case.edit_profile.EditProfileOutputBoundary;
import use_case.go_to_chat_list.GoToChatListDataAccessInterface;
import use_case.go_to_chat_list.GoToChatListInputBoundary;
import use_case.go_to_chat_list.GoToChatListInteractor;
import use_case.go_to_chat_list.GoToChatListOutputBoundary;
import use_case.go_to_personal_profile.GoToPersonalProfileDataAccessInterface;
import use_case.go_to_personal_profile.GoToPersonalProfileInputBoundary;
import use_case.go_to_personal_profile.GoToPersonalProfileInteractor;
import use_case.go_to_personal_profile.GoToPersonalProfileOutputBoundary;
import use_case.go_to_search.GoToSearchInputBoundary;
import use_case.go_to_search.GoToSearchInteractor;
import view.PersonalProfileView;

import javax.swing.*;
import java.io.IOException;

public class PersonalProfileUseCaseFactory {

    /* Prevent instantiation. */
    private PersonalProfileUseCaseFactory() {}

    public static PersonalProfileView create(
            ViewManagerModel viewManagerModel,
            GoToPersonalProfileViewModel goToPersonalProfileViewModel,
            GoToPersonalProfileDataAccessInterface goToPersonalProfileDataAccessObject,
            GoToChatListViewModel goToChatListViewModel,
            GoToChatListDataAccessInterface goToChatListDataAccessObject,
            SearchCourseViewModel searchCourseViewModel,
            EditProfileViewModel editProfileViewModel,
            EditProfileDataAccessInterface editProfileDataAccessObject
            ) {

        try {
            GoToPersonalProfileController goToPersonalProfileController = createGoToPersonalProfileUseCase(viewManagerModel,
                    goToPersonalProfileViewModel, goToPersonalProfileDataAccessObject);
            GoToChatListController goToChatListController = createGoToChatListUseCase(viewManagerModel,
                    goToChatListViewModel, goToChatListDataAccessObject);
            GoToSearchController goToSearchController = createGoToSearchUseCase(viewManagerModel, searchCourseViewModel);
            EditProfileController editProfileController = createEditProfileUseCase(viewManagerModel, editProfileViewModel, editProfileDataAccessObject);
            return new PersonalProfileView(goToPersonalProfileViewModel, goToPersonalProfileController,
                    goToChatListViewModel, goToChatListController, goToSearchController, editProfileViewModel, editProfileController, viewManagerModel);
        } catch (IOException e) {
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

    private static EditProfileController createEditProfileUseCase(
            ViewManagerModel viewManagerModel,
            EditProfileViewModel editProfileViewModel,
            EditProfileDataAccessInterface editProfileDataAccessObject) throws IOException {

        EditProfileOutputBoundary editProfilePresenter =
                new EditProfilePresenter(editProfileViewModel, viewManagerModel);

        EditProfileInputBoundary editProfileInteractor =
                new EditProfileInteractor(editProfilePresenter, editProfileDataAccessObject);

        return new EditProfileController(editProfileInteractor);
    }
}
