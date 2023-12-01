package use_case.go_to_chat;

public interface GoToChannelOutputBoundary {
    public void prepareSuccessView(GoToChannelOutputData goToChannelOutputData);
    public void prepareFailView(String errorMessage);
}
