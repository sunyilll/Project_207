package main.java.app;

import main.java.interface_adapter.ViewManagerModel;
import main.java.interface_adapter.go_to_personal_profile.GoToPersonalProfileController;
import main.java.interface_adapter.go_to_personal_profile.GoToPersonalProfilePresenter;
import main.java.interface_adapter.go_to_personal_profile.GoToPersonalProfileViewModel;
import main.java.use_case.go_to_personal_profile.GoToPersonalProfileInputBoundary;
import main.java.use_case.go_to_personal_profile.GoToPersonalProfileInteractor;
import main.java.use_case.go_to_personal_profile.GoToPersonalProfileOutputBoundary;
import main.java.view.PersonalProfileView;

import javax.swing.*;
import java.io.IOException;

public class ToPersonalProfileUseCaseFactory {

    /* Prevent instantiation. */
    private ToPersonalProfileUseCaseFactory() {}

    public static PersonalProfileView create(
            ViewManagerModel viewManagerModel,
            GoToPersonalProfileViewModel personalProfileViewModel) {

        try {
            GoToPersonalProfileController goToPersonalProfileController = createLoginUseCase(viewManagerModel,
                    personalProfileViewModel);
            return new PersonalProfileView(goToPersonalProfileController, personalProfileViewModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static GoToPersonalProfileController createLoginUseCase(
            ViewManagerModel viewManagerModel,
            GoToPersonalProfileViewModel personalProfileViewModel) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        GoToPersonalProfileOutputBoundary personalProfilePresenter =
                new GoToPersonalProfilePresenter(personalProfileViewModel, viewManagerModel);

        GoToPersonalProfileInputBoundary personalProfileInteractor =
                new GoToPersonalProfileInteractor(personalProfilePresenter);

        return new GoToPersonalProfileController(personalProfileInteractor);
    }
}
