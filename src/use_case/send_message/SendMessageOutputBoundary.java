package use_case.send_message;

public interface SendMessageOutputBoundary {

    public void prepareSuccessView(SendMessageOutputData message);

    public void prepareFailView(String error);
}
