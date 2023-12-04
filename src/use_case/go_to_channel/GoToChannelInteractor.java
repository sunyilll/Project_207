package use_case.go_to_channel;

import data_access.JsonUserDataAccessObject;
import entity.ChatChannel;
import entity.User;

import java.util.ArrayList;
import java.util.Set;

public class GoToChannelInteractor implements GoToChannelInputBoundary{
    final JsonUserDataAccessObject goToChannelDataAccessObject;
    final GoToChannelOutputBoundary goToChannelPresenter;
    public GoToChannelInteractor(GoToChannelOutputBoundary goToChannelPresenter) {
        this.goToChannelDataAccessObject = new JsonUserDataAccessObject("./user.json");
//        this.goToChannelDataAccessObject = goToChannelDataAccessObject;
        this.goToChannelPresenter = goToChannelPresenter;
    }

    @Override
    public void execute(GoToChannelInputData goToChannelInputData) {
        User currentUser = goToChannelInputData.getCurrentUser();
        ChatChannel currentChannel = goToChannelInputData.getCurrentChannel();
        if (currentChannel.getChannelURL() != "DNE"){
            ;
        } else{
            Set<String> keys = currentChannel.getMembers().keySet();

            ArrayList<String> keyList = new ArrayList<String>(keys);
// Looping through the keys and doing something with them
            for (String key : keys) {
                // Here, 'key' represents each key in the map
                keyList.add(key);
                // You can perform any action with the key here
            }
            String channelUrl = goToChannelDataAccessObject.getChatChannelsWith2Users(keyList.get(0), keyList.get(1));
            currentChannel.setChannelURL(channelUrl);
        }

        GoToChannelOutputData goToChannelOutputData = new GoToChannelOutputData(currentUser, currentChannel);
        goToChannelPresenter.prepareSuccessView(goToChannelOutputData);

    }
}
