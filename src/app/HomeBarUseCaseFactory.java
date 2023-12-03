package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.go_to_chatl_list.GoToChatListController;
import interface_adapter.go_to_chatl_list.GoToChatListPresenter;
import interface_adapter.go_to_chatl_list.GoToChatListViewModel;
import interface_adapter.go_to_personal_profile.GoToPersonalProfileController;
import interface_adapter.go_to_personal_profile.GoToPersonalProfilePresenter;
import interface_adapter.go_to_personal_profile.GoToPersonalProfileViewModel;
import interface_adapter.go_to_search.GoToSearchController;
import use_case.go_to_chat_list.GoToChatListDataAccessInterface;
import use_case.go_to_chat_list.GoToChatListInputBoundary;
import use_case.go_to_chat_list.GoToChatListInteractor;
import use_case.go_to_chat_list.GoToChatListOutputBoundary;
import use_case.go_to_personal_profile.GoToPersonalProfileDataAccessInterface;
import use_case.go_to_personal_profile.GoToPersonalProfileInputBoundary;
import use_case.go_to_personal_profile.GoToPersonalProfileInteractor;
import use_case.go_to_personal_profile.GoToPersonalProfileOutputBoundary;
import view.HomeBar;

import javax.swing.*;
import java.io.IOException;

// FIXME: Delete me.
// This class is for testing purposes only.

public class HomeBarUseCaseFactory {

    /* Prevent instantiation. */
    private HomeBarUseCaseFactory() {}

    public static HomeBar create(
            ViewManagerModel viewManagerModel,
            GoToPersonalProfileViewModel goToPersonalProfileViewModel,
            GoToPersonalProfileDataAccessInterface goToPersonalProfileDataAccessObject,
            GoToChatListViewModel goToChatListViewModel,
            GoToChatListDataAccessInterface goToChatListDataAccessObject,
            GoToSearchController goToSearchController
            ) {

        try {
            GoToPersonalProfileController goToPersonalProfileController = createGoToPersonalProfileUseCase(viewManagerModel,
                    goToPersonalProfileViewModel, goToPersonalProfileDataAccessObject);
            GoToChatListController goToChatListController = createGoToChatListUseCase(viewManagerModel,
                    goToChatListViewModel, goToChatListDataAccessObject);
            return new HomeBar(goToPersonalProfileViewModel, goToPersonalProfileController,
                    goToChatListViewModel, goToChatListController, goToSearchController);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
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
}
