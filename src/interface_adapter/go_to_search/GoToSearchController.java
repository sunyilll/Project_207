package interface_adapter.go_to_search;

import use_case.go_to_search.GoToSearchInputBoundary;

public class GoToSearchController {
    final GoToSearchInputBoundary goToSearchInputBoundary;
    public GoToSearchController(GoToSearchInputBoundary interactor){
        this.goToSearchInputBoundary = interactor;
    }
    public  void execute(){
        goToSearchInputBoundary.execute();
    }
}
