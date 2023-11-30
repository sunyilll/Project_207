package main.java.app;

import main.java.interface_adapter.ViewManagerModel;
import main.java.interface_adapter.go_to_chatl_list.GoToChatListController;
import main.java.interface_adapter.go_to_chatl_list.GoToChatListPresenter;
import main.java.interface_adapter.go_to_chatl_list.GoToChatListViewModel;
import main.java.interface_adapter.go_to_personal_profile.GoToPersonalProfileController;
import main.java.interface_adapter.go_to_personal_profile.GoToPersonalProfilePresenter;
import main.java.interface_adapter.go_to_personal_profile.GoToPersonalProfileViewModel;
import main.java.use_case.go_to_chat_list.GoToChatListDataAccessInterface;
import main.java.use_case.go_to_chat_list.GoToChatListInputBoundary;
import main.java.use_case.go_to_chat_list.GoToChatListInteractor;
import main.java.use_case.go_to_chat_list.GoToChatListOutputBoundary;
import main.java.use_case.go_to_personal_profile.GoToPersonalProfileInputBoundary;
import main.java.use_case.go_to_personal_profile.GoToPersonalProfileInteractor;
import main.java.use_case.go_to_personal_profile.GoToPersonalProfileOutputBoundary;
import main.java.view.HomeBar;

import javax.swing.*;
import java.io.IOException;

// FIXME: Delete me.
// This class is for testing purposes only.

public class HomeBarViewFactory {

    /* Prevent instantiation. */
    private HomeBarViewFactory() {}

    public static HomeBar create(
            ViewManagerModel viewManagerModel,
            GoToPersonalProfileViewModel goToPersonalProfileViewModel,
            GoToChatListViewModel goToChatListViewModel, GoToChatListDataAccessInterface goToChatListDataAccessObject
            //TODO: add GoToSearchViewModel
            ) {

        try {
            GoToPersonalProfileController goToPersonalProfileController = createGoToPersonalProfileUseCase(viewManagerModel,
                    goToPersonalProfileViewModel);
            GoToChatListController goToChatListController = createGoToChatListUseCase(viewManagerModel,
                    goToChatListViewModel, goToChatListDataAccessObject);
            return new HomeBar(goToPersonalProfileViewModel, goToPersonalProfileController,
                    goToChatListViewModel, goToChatListController);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
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
