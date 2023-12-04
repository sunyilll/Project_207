package use_case.go_to_chat_list;

import entity.ChatChannel;
import entity.User;

import java.sql.Array;
import java.util.ArrayList;

public class GoToChatListInteractor implements GoToChatListInputBoundary {
    final GoToChatListDataAccessInterface goToChatPageDataAccessObject;
    final GoToChatListOutputBoundary goToChatListPresenter;
    public GoToChatListInteractor(GoToChatListDataAccessInterface goToChatPageDataAccessObject, GoToChatListOutputBoundary goToChatListPresenter) {
        this.goToChatPageDataAccessObject = goToChatPageDataAccessObject;
        this.goToChatListPresenter = goToChatListPresenter;
    }
    @Override
    public void execute() {
        try{
            ArrayList<ChatChannel> chatChannels = goToChatPageDataAccessObject.getAllChatChannels();
            GoToChatListOutputData goToChatListOutputData = new GoToChatListOutputData(chatChannels, goToChatPageDataAccessObject.getCurrentUser());
            goToChatListPresenter.prepareSuccessView(goToChatListOutputData);
            return;
        } catch (RuntimeException e){
            ArrayList<ChatChannel> chatChannels = new ArrayList<>();
            GoToChatListOutputData goToChatListOutputData = new GoToChatListOutputData(chatChannels, goToChatPageDataAccessObject.getCurrentUser());
            goToChatListPresenter.prepareSuccessView(goToChatListOutputData);
        }
    }

}
