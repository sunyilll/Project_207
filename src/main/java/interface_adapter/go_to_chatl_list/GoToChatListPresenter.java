package main.java.interface_adapter.go_to_chatl_list;

import main.java.interface_adapter.ViewManagerModel;
import main.java.use_case.go_to_chat_list.GoToChatListOutputBoundary;
import main.java.use_case.go_to_chat_list.GoToChatListOutputData;

public class GoToChatListPresenter implements GoToChatListOutputBoundary {
    private final GoToChatListViewModel goToChatListViewModel;
    private ViewManagerModel viewManagerModel;
    public GoToChatListPresenter(GoToChatListViewModel GoToChatListViewModel, ViewManagerModel viewManagerModel) {
        this.goToChatListViewModel = GoToChatListViewModel;
        this.viewManagerModel = viewManagerModel;
    }
    public void prepareSuccessView(GoToChatListOutputData goToChatListOutputData) {
        GoToChatListState GoToChatListState = goToChatListViewModel.getState();
        GoToChatListState.setSuccess(true);
        this.goToChatListViewModel.setState(GoToChatListState);
        goToChatListViewModel.firePropertyChanged();

        viewManagerModel.setActiveView("chatList");
        viewManagerModel.firePropertyChanged();
        System.out.println("I am at step 5");
        System.out.println(viewManagerModel.getActiveView());
    }

    public void prepareFailView(String error) {
        GoToChatListState GoToChatListState = goToChatListViewModel.getState();
        GoToChatListState.setErrorMessage(error);
        goToChatListViewModel.firePropertyChanged();
    }
}
