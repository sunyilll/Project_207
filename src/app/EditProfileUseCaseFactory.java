package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.edit_profile.EditProfileViewModel;
import interface_adapter.go_to_personal_profile.GoToPersonalProfileController;
import interface_adapter.go_to_personal_profile.GoToPersonalProfilePresenter;
import interface_adapter.go_to_personal_profile.GoToPersonalProfileViewModel;
import interface_adapter.save_profile.SaveProfileController;
import interface_adapter.save_profile.SaveProfilePresenter;
import interface_adapter.save_profile.SaveProfileViewModel;
import use_case.go_to_personal_profile.GoToPersonalProfileDataAccessInterface;
import use_case.go_to_personal_profile.GoToPersonalProfileInputBoundary;
import use_case.go_to_personal_profile.GoToPersonalProfileInteractor;
import use_case.go_to_personal_profile.GoToPersonalProfileOutputBoundary;
import use_case.save_profile.SaveProfileDataAccessInterface;
import use_case.save_profile.SaveProfileInputBoundary;
import use_case.save_profile.SaveProfileInteractor;
import use_case.save_profile.SaveProfileOutputBoundary;
import view.ProfileEditView;

import javax.swing.*;
import java.io.IOException;

public class EditProfileUseCaseFactory {
    private EditProfileUseCaseFactory() {}
    public static ProfileEditView create(
            ViewManagerModel viewManagerModel,
            EditProfileViewModel editProfileViewModel,
            SaveProfileViewModel saveProfileViewModel,
            SaveProfileDataAccessInterface saveProfileDataAccessObject,
            GoToPersonalProfileViewModel goToPersonalProfileViewModel,
            GoToPersonalProfileDataAccessInterface goToPersonalProfileDataAccessObject) {


        try {
            GoToPersonalProfileController goToPersonalProfileController = createGoToPersonalProfileUseCase(viewManagerModel,
                    goToPersonalProfileViewModel, goToPersonalProfileDataAccessObject);
            SaveProfileController saveProfileController = createSaveProfileUseCase(viewManagerModel, saveProfileViewModel, saveProfileDataAccessObject);
            return new ProfileEditView(viewManagerModel, saveProfileController, saveProfileViewModel, goToPersonalProfileViewModel, goToPersonalProfileController, editProfileViewModel);
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
    private static SaveProfileController createSaveProfileUseCase(
            ViewManagerModel viewManagerModel,
            SaveProfileViewModel saveProfileViewModel,
            SaveProfileDataAccessInterface saveProfileDataAccessObject) throws IOException {

        SaveProfileOutputBoundary saveProfilePresenter =
                new SaveProfilePresenter(saveProfileViewModel, viewManagerModel);

        SaveProfileInputBoundary saveProfileInteractor =
                new SaveProfileInteractor(saveProfilePresenter, saveProfileDataAccessObject);

        return new SaveProfileController(saveProfileInteractor);
    }
}
