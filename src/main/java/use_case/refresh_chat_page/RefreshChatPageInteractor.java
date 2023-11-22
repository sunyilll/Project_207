package main.java.use_case.refresh_chat_page;

import kotlin.Pair;
import main.java.entity.ChatChannel;

import java.util.ArrayList;

public class RefreshChatPageInteractor implements RefreshChatPageInputBoundary{
    final RefreshChatPageDataAccessInterface refreshChatPageDataAccessObject;
    final RefreshChatPageOutputBoundary refreshChatPagePresenter;
    public RefreshChatPageInteractor(RefreshChatPageDataAccessInterface refreshChatPageDataAccessObject, RefreshChatPageOutputBoundary refreshChatPagePresenter){
        this.refreshChatPageDataAccessObject = refreshChatPageDataAccessObject;
        this.refreshChatPagePresenter = refreshChatPagePresenter;
    }
    @Override
    public void execute(RefreshChatPageInputData refreshChatPageInputData) {
        ChatChannel channel = refreshChatPageInputData.getChannel();
        try{
            ArrayList<Pair<String, String>> messageList = refreshChatPageDataAccessObject.getMessageList(channel);
            System.out.println(messageList);
            System.out.println("DAO");
            RefreshChatPageOutputData refreshChatPageOutputData = new RefreshChatPageOutputData(messageList);
            refreshChatPagePresenter.prepareSuccessView(refreshChatPageOutputData);
        } catch (RuntimeException e){
            refreshChatPagePresenter.prepareFailView(e.toString());
        }

    }
}
