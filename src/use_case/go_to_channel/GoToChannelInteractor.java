package use_case.go_to_channel;

import entity.ChatChannel;
import entity.User;

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
        GoToChannelOutputData goToChannelOutputData = new GoToChannelOutputData(currentUser, currentChannel);
        goToChannelPresenter.prepareSuccessView(goToChannelOutputData);

    }
}
