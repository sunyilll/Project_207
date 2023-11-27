package main.java.use_case.go_to_chat_list;

public interface GoToChatListOutputBoundary {
    public void prepareSuccessView(GoToChatListOutputData goToChatListOutputData);
    public void prepareFailView(String error);
}
