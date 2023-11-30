package main.java.use_case.go_to_chat;

import main.java.entity.ChatChannel;
import main.java.entity.User;

public class GoToChannelInteractor implements GoToChannelInputBoundary{
//    final GoToChannelDataAccessInterface goToChannelDataAccessObject;
    final GoToChannelOutputBoundary goToChannelPresenter;
    public GoToChannelInteractor(GoToChannelOutputBoundary goToChannelPresenter) {
//        this.goToChannelDataAccessObject = goToChannelDataAccessObject;
        this.goToChannelPresenter = goToChannelPresenter;
    }

    @Override
    public void execute(GoToChannelInputData goToChannelInputData) {
        User currentUser = goToChannelInputData.getCurrentUser();
        ChatChannel currentChannel = goToChannelInputData.getCurrentChannel();
        try {
            GoToChannelOutputData goToChannelOutputData = new GoToChannelOutputData(currentUser, currentChannel);
            goToChannelPresenter.prepareSuccessView(goToChannelOutputData);
        } catch (RuntimeException e) {
            goToChannelPresenter.prepareFailView(e.toString());

        }
    }
}
