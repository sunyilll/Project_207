package interface_adapter.go_to_chat;

import entity.ChatChannel;
import entity.User;
import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class GoToChannelViewModel extends ViewModel {
    public static final String TITLE = "go to channel";
    private GoToChannelState state = new GoToChannelState();
    private User currentUser = null;
    private ChatChannel currentChannel = null;
    public GoToChannelViewModel() {
        super("go to channel");
    }
    public GoToChannelState getState() {
        return state;
    }
    public void setState(GoToChannelState goToChannelState) {
        this.state = goToChannelState;
        this.currentUser = goToChannelState.getCurrentUser();
        this.currentChannel = goToChannelState.getCurrentChannel();
    }
    public User getCurrentUser() {
        return currentUser;
    }
    public ChatChannel getCurrentChannel() {
        return currentChannel;
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
