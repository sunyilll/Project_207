package interface_adapter.go_to_channel;

import entity.ChatChannel;
import entity.User;
import use_case.go_to_channel.GoToChannelInputBoundary;
import use_case.go_to_channel.GoToChannelInputData;

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
