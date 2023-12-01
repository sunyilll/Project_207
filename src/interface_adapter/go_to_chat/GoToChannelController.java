package interface_adapter.go_to_chat;

import entity.ChatChannel;
import entity.User;
import use_case.go_to_chat.GoToChannelInputBoundary;
import use_case.go_to_chat.GoToChannelInputData;

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
