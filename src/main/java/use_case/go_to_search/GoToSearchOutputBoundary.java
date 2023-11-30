package main.java.use_case.go_to_search;


public interface GoToSearchOutputBoundary{
    void prepareSuccessView();
    void prepareFailView(String error);
}
