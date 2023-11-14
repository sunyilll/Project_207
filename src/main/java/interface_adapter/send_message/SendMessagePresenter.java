package main.java.interface_adapter.send_message;

import main.java.interface_adapter.ViewManagerModel;
import main.java.use_case.send_message.SendMessageOutputBoundary;
import main.java.view.ViewManager;
import main.java.use_case.send_message.SendMessageOutputData;

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
        sendMessageState.setErrorMessage(error);
        sendMessageViewModel.firePropertyChanged();

    }


}
