package main.java.interface_adapter.refresh_chat_page;

import main.java.entity.ChatChannel;
import main.java.use_case.refresh_chat_page.RefreshChatPageInputBoundary;
import main.java.use_case.refresh_chat_page.RefreshChatPageInputData;

public class RefreshChatPageController {
    final RefreshChatPageInputBoundary refreshChatPageInteractor;
    public RefreshChatPageController(RefreshChatPageInputBoundary refreshChatPageInteractor){
        this.refreshChatPageInteractor = refreshChatPageInteractor;
    }
    public void execute(ChatChannel channel){
        RefreshChatPageInputData refreshChatPageInputData = new RefreshChatPageInputData(channel);
        refreshChatPageInteractor.execute(refreshChatPageInputData);
    }
}
