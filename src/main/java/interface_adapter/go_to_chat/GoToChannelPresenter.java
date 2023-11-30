package main.java.interface_adapter.go_to_chat;

import main.java.interface_adapter.ViewManagerModel;
import main.java.use_case.go_to_chat.GoToChannelOutputBoundary;
import main.java.use_case.go_to_chat.GoToChannelOutputData;

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
        this.goToChannelViewModel.setState(goToChannelState);
        goToChannelViewModel.firePropertyChanged();

        viewManagerModel.setActiveView("channel");
        viewManagerModel.firePropertyChanged();

    }
    public void prepareFailView(String error) {
        GoToChannelState goToChannelState = goToChannelViewModel.getState();
        goToChannelState.setErrorMessage(error);
        goToChannelViewModel.firePropertyChanged();
    }
}
