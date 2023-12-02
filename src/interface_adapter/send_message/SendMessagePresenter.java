package interface_adapter.send_message;

import interface_adapter.ViewManagerModel;
import use_case.send_message.SendMessageOutputBoundary;
import use_case.send_message.SendMessageOutputData;

public class SendMessagePresenter implements SendMessageOutputBoundary {

    private final SendMessageViewModel sendMessageViewModel;

    private ViewManagerModel viewManagerModel;

    public SendMessagePresenter(SendMessageViewModel sendMessageViewModel, ViewManagerModel viewManagerModel) {
        this.sendMessageViewModel = sendMessageViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    public void prepareSuccessView(SendMessageOutputData message) {

        SendMessageState sendMessageState = sendMessageViewModel.getState();
        sendMessageState.setMessageSentSuccessful(true);
        this.sendMessageViewModel.setState(sendMessageState);
        sendMessageViewModel.firePropertyChanged();


    }

    public void prepareFailView(String error) {
        SendMessageState sendMessageState = sendMessageViewModel.getState();
        sendMessageState.setMessageSentSuccessful(false);
        sendMessageState.setErrorMessage(error);
        sendMessageViewModel.firePropertyChanged();

    }


}
