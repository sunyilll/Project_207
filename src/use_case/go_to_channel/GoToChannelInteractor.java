package use_case.go_to_channel;

import data_access.JsonUserDataAccessObject;
import entity.ChatChannel;
import entity.User;

public class GoToChannelInteractor implements GoToChannelInputBoundary{
    final JsonUserDataAccessObject goToChannelDataAccessObject;
    final GoToChannelOutputBoundary goToChannelPresenter;
    public GoToChannelInteractor(GoToChannelOutputBoundary goToChannelPresenter) {
//        this.goToChannelDataAccessObject = goToChannelDataAccessObject;
        this.goToChannelPresenter = goToChannelPresenter;
    }

    @Override
    public void execute(GoToChannelInputData goToChannelInputData) {
        User currentUser = goToChannelInputData.getCurrentUser();
        ChatChannel currentChannel = goToChannelInputData.getCurrentChannel();
        if (currentChannel.getChannelURL() != "DNE"){
            GoToChannelOutputData goToChannelOutputData = new GoToChannelOutputData(currentUser, currentChannel);
        } else{
            goToChannelDataAccessObject.

        }

        GoToChannelOutputData goToChannelOutputData = new GoToChannelOutputData(currentUser, currentChannel);
        goToChannelPresenter.prepareSuccessView(goToChannelOutputData);

    }
}
