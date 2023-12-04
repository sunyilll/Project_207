package interface_adapter.save_profile;

import interface_adapter.ViewManagerModel;
import interface_adapter.go_to_personal_profile.GoToPersonalProfileState;
import use_case.save_profile.SaveProfileOutputBoundary;
import use_case.save_profile.SaveProfileOutputData;

public class SaveProfilePresenter implements SaveProfileOutputBoundary {
    private final SaveProfileViewModel viewModel;
    private final ViewManagerModel viewManagerModel;

    public SaveProfilePresenter(SaveProfileViewModel viewModel, ViewManagerModel viewManagerModel) {
        this.viewModel = viewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(SaveProfileOutputData saveProfileOutputData) {
        SaveProfileState state = viewModel.getState();
        state.setSaveSuccess(true);
        viewModel.setState(state);
        viewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        System.out.println("Failed to save profile");
        // TODO
    }
}
