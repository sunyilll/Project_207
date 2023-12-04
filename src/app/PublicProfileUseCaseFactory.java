package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.go_to_channel.GoToChannelController;
import interface_adapter.go_to_channel.GoToChannelPresenter;
import interface_adapter.go_to_channel.GoToChannelViewModel;
import interface_adapter.go_to_public_profile.GoToPublicProfileController;
import interface_adapter.go_to_public_profile.GoToPublicProfilePresenter;
import interface_adapter.go_to_public_profile.GoToPublicProfileViewModel;
import use_case.go_to_channel.GoToChannelDataAccessInterface;
import use_case.go_to_channel.GoToChannelInputBoundary;
import use_case.go_to_channel.GoToChannelInteractor;
import use_case.go_to_channel.GoToChannelOutputBoundary;
import use_case.go_to_public_profile.GoToPublicProfileDataAccessInterface;
import use_case.go_to_public_profile.GoToPublicProfileInputBoundary;
import use_case.go_to_public_profile.GoToPublicProfileInteractor;
import use_case.go_to_public_profile.GoToPublicProfileOutputBoundary;
import view.PublicProfileView;

import javax.swing.*;
import java.io.IOException;

public class PublicProfileUseCaseFactory {
    private PublicProfileUseCaseFactory() {}

    public static PublicProfileView create(
            ViewManagerModel viewManagerModel,
            GoToPublicProfileViewModel goToPublicProfileViewModel,
            GoToChannelViewModel goToChannelViewModel,
            GoToChannelDataAccessInterface goToChannelDataAccessObject) {

        try {
            GoToChannelController goToChannelController = createGoToChannelUseCase(viewManagerModel,
                    goToChannelViewModel, goToChannelDataAccessObject);
            return new PublicProfileView(goToPublicProfileViewModel, goToChannelViewModel, goToChannelController, viewManagerModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }
    private static GoToChannelController createGoToChannelUseCase(
            ViewManagerModel viewManagerModel,
            GoToChannelViewModel goToChannelViewModel,
            GoToChannelDataAccessInterface goToChannelDataAccessObject) throws IOException {

        GoToChannelOutputBoundary channelPresenter =
                new GoToChannelPresenter(goToChannelViewModel, viewManagerModel);

        GoToChannelInputBoundary channelInteractor =
                new GoToChannelInteractor(channelPresenter);

        return new GoToChannelController(channelInteractor);
    }

    private static GoToPublicProfileController createGoToPublicProfileUseCase(
            ViewManagerModel viewManagerModel,
            GoToPublicProfileViewModel goToPublicProfileViewModel,
            GoToPublicProfileDataAccessInterface goToPublicProfileDataAccessObject) throws IOException {

        GoToPublicProfileOutputBoundary publicProfilePresenter =
                new GoToPublicProfilePresenter(goToPublicProfileViewModel, viewManagerModel);

        GoToPublicProfileInputBoundary publicProfileInteractor =
                new GoToPublicProfileInteractor(publicProfilePresenter, goToPublicProfileDataAccessObject);

        return new GoToPublicProfileController(publicProfileInteractor);
    }

}
