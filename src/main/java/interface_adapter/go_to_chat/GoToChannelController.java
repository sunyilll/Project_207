package main.java.interface_adapter.go_to_chat;

import main.java.entity.ChatChannel;
import main.java.entity.User;
import main.java.use_case.go_to_chat.GoToChannelInputBoundary;
import main.java.use_case.go_to_chat.GoToChannelInputData;

public class GoToChannelController {
    final GoToChannelInputBoundary goToChatInputUseCaseInteractor;
    public GoToChannelController(GoToChannelInputBoundary goToChatInputUseCaseInteractor) {
        this.goToChatInputUseCaseInteractor = goToChatInputUseCaseInteractor;
    }
    public void execute(User user, ChatChannel channel) {
        GoToChannelInputData goToChannelInputData = new GoToChannelInputData(user, channel);
        goToChatInputUseCaseInteractor.execute(goToChannelInputData);
    }
}
