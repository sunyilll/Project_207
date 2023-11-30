package main.java.interface_adapter.go_to_chatl_list;

import main.java.entity.ChatChannel;
import main.java.entity.User;
import main.java.interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class GoToChatListViewModel extends ViewModel {
    public static final String TITLE = "go to chat list";
    private User currectUser;
    private ArrayList<ChatChannel> chatChannels;
    private GoToChatListState state = new GoToChatListState();
    public GoToChatListViewModel() {
        super("go to chat list");
    }

//    public GoToChatListViewModel(User user, ArrayList<ChatChannel> chatChannels, GoToChatListState state) {
//        super("go to chat list");
//        this.currectUser = user;
//        this.chatChannels = chatChannels;
//        this.state = state;
//    }

    public GoToChatListState getState() {
        return state;
    }
    public User getCurrentUser() {
        return currectUser;
    }
    public ArrayList<ChatChannel> getChatChannels() {
        return chatChannels;
    }

    public void setState(GoToChatListState goToChatListState) {
        this.state = goToChatListState;
        this.currectUser = goToChatListState.getUser();
        this.chatChannels = goToChatListState.getChatChannels();

    }
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
