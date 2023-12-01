package use_case.go_to_chat_list;

import entity.User;

public class GoToChatListInputData {
    final private User user;
    public GoToChatListInputData(User user) {
        this.user = user;
    }
    public User getUser() {
        return user;
    }
}
