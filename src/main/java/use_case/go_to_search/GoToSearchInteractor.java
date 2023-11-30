package main.java.use_case.go_to_search;

import main.java.use_case.go_to_personal_profile.GoToPersonalProfileOutputBoundary;

public class GoToSearchInteractor implements GoToSearchInputBoundary{
    final private GoToSearchOutputBoundary presenter;
    public GoToSearchInteractor(GoToSearchOutputBoundary presenter){this.presenter = presenter;}
    @Override
    public void execute() {
        presenter.prepareSuccessView();
    }
}
