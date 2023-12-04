package use_case.go_to_channel;

import java.nio.channels.Channel;

public interface GoToChannelDataAccessInterface {
    Channel getChannel(String currentUserid, String targetUserid);
}
