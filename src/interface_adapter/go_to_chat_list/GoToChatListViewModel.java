package interface_adapter.go_to_chat_list;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class GoToChatListViewModel extends ViewModel {
    public static final String TITLE = "go to chat list";
    private GoToChatListState state = new GoToChatListState();
    public GoToChatListViewModel() {
        super("go to chat list");
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
        support.firePropertyChange("chatList", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
