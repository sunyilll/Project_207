package use_case.refresh_chat_page;

public interface RefreshChatPageOutputBoundary {
    public void prepareSuccessView(RefreshChatPageOutputData refreshChatPageOutputData);
    public void prepareFailView(String error);
}
