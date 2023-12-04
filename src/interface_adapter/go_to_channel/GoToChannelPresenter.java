package interface_adapter.go_to_channel;

import interface_adapter.ViewManagerModel;
import use_case.go_to_channel.GoToChannelOutputBoundary;
import use_case.go_to_channel.GoToChannelOutputData;

public class GoToChannelPresenter implements GoToChannelOutputBoundary {
    private final GoToChannelViewModel goToChannelViewModel;
    private ViewManagerModel viewManagerModel;
    public GoToChannelPresenter(GoToChannelViewModel goToChannelViewModel, ViewManagerModel viewManagerModel) {
        this.goToChannelViewModel = goToChannelViewModel;
        this.viewManagerModel = viewManagerModel;
    }
    public void prepareSuccessView(GoToChannelOutputData goToChannelOutputData) {
        GoToChannelState goToChannelState = goToChannelViewModel.getState();
        goToChannelState.setSuccess(true);
        goToChannelState.setCurrentChannel(goToChannelOutputData.getCurrentChannel());
        goToChannelState.setCurrentUser(goToChannelOutputData.getCurrentUser());
//        System.out.println(goToChannelState.getCurrentUser().getUserID());

        this.goToChannelViewModel.setState(goToChannelState);
        goToChannelViewModel.firePropertyChanged();

        viewManagerModel.setActiveView("channel");
        viewManagerModel.firePropertyChanged();

    }
    public void prepareFailView(String error) {
        GoToChannelState goToChannelState = goToChannelViewModel.getState();
        goToChannelState.setSuccess(false);
        goToChannelState.setErrorMessage(error);
        goToChannelViewModel.setState(goToChannelState);
        goToChannelViewModel.firePropertyChanged();
    }
}
