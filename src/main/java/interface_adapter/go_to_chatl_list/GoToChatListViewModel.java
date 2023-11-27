package main.java.interface_adapter.go_to_chatl_list;

import main.java.entity.ChatChannel;
import main.java.entity.User;
import main.java.interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class GoToChatListViewModel extends ViewModel {
    public static final String TITLE = "go to chat list";
    private User currectUder;
    private ArrayList<ChatChannel> chatChannels;
    private GoToChatListState state = new GoToChatListState();

    public GoToChatListViewModel(User user, ArrayList<ChatChannel> chatChannels, GoToChatListState state) {
        super("go to chat list");
        this.currectUder = user;
        this.chatChannels = chatChannels;
        this.state = state;
    }

    public GoToChatListState getState() {
        return state;
    }

    public void setState(GoToChatListState goToChatListState) {
        this.state = goToChatListState;
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
