package interface_adapter.go_to_chatl_list;

import entity.User;
import use_case.go_to_chat_list.GoToChatListInputBoundary;

public class GoToChatListController {
    final GoToChatListInputBoundary GoToChatListUseCaseInteractor;
    public GoToChatListController(GoToChatListInputBoundary GoToChatListUseCaseInteractor) {
        this.GoToChatListUseCaseInteractor = GoToChatListUseCaseInteractor;
    }
    public void execute() {
        GoToChatListUseCaseInteractor.execute();
    }
}
