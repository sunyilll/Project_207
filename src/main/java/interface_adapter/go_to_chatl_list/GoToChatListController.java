package main.java.interface_adapter.go_to_chatl_list;

import main.java.entity.User;
import main.java.use_case.go_to_chat_list.GoToChatListInputBoundary;
import main.java.use_case.go_to_chat_list.GoToChatListInputData;

public class GoToChatListController {
    final GoToChatListInputBoundary GoToChatListUseCaseInteractor;
    public GoToChatListController(GoToChatListInputBoundary GoToChatListUseCaseInteractor) {
        this.GoToChatListUseCaseInteractor = GoToChatListUseCaseInteractor;
    }
    public void execute(User user) {
        GoToChatListInputData goToChatListInputData = new GoToChatListInputData(user);
        GoToChatListUseCaseInteractor.execute(goToChatListInputData);
    }
}
