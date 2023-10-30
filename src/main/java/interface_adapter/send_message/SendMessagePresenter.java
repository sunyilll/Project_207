package main.java.interface_adapter.send_message;

import main.java.use_case.send_message.SendMessageOutputBoundary;
import main.java.view.ViewManager;
import main.java.use_case.send_message.SendMessageOutputData;

public class SendMessagePresenter implements SendMessageOutputBoundary {

    private final SendMessageViewModel sendMessageViewModel;

    private ViewManager viewManager;

    public SendMessagePresenter(SendMessageViewModel sendMessageViewModel, ViewManager viewManager) {
        this.sendMessageViewModel = sendMessageViewModel;
        this.viewManager = viewManager;
    }

    public void prepareSuccessView(SendMessageOutputData message) {

    }

    public static void prepareFailView(String error) {

    }


}
