package main.java.interface_adapter.go_to_search;

import main.java.use_case.go_to_search.GoToSearchInputBoundary;
import main.java.use_case.go_to_search.GoToSearchInteractor;

public class GoToSearchController {
    final GoToSearchInputBoundary goToSearchInputBoundary;
    public GoToSearchController(GoToSearchInputBoundary interactor){
        this.goToSearchInputBoundary = interactor;
    }
    public  void execute(){
        goToSearchInputBoundary.execute();
    }
}
