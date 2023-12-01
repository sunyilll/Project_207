package interface_adapter.go_to_personal_profile;
import interface_adapter.ViewManagerModel;
import use_case.go_to_personal_profile.GoToPersonalProfileOutputBoundary;
import use_case.go_to_personal_profile.GoToPersonalProfileOutputData;

public class GoToPersonalProfilePresenter implements GoToPersonalProfileOutputBoundary {
    private final GoToPersonalProfileViewModel viewModel;
    private final ViewManagerModel viewManager;

    public GoToPersonalProfilePresenter(GoToPersonalProfileViewModel viewModel, ViewManagerModel viewManager) {
        this.viewModel = viewModel;
        this.viewManager = viewManager;
    }
    @Override
    public void prepareSuccessView(GoToPersonalProfileOutputData response) {
        GoToPersonalProfileState state = viewModel.getState();
        state.setUser(response.getUser());
        this.viewModel.setState(state);
        viewModel.firePropertyChanged();

        viewManager.setActiveView(viewModel.getViewName());
        viewManager.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        // TODO: implement this
    }
}
