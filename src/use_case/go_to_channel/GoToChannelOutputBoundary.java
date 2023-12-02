package use_case.go_to_channel;

public interface GoToChannelOutputBoundary {
    public void prepareSuccessView(GoToChannelOutputData goToChannelOutputData);
    public void prepareFailView(String errorMessage);
}
