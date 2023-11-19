package main.java.interface_adapter.refresh_chat_page;


import main.java.entity.ChatChannel;
import main.java.entity.User;
import main.java.interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class RefreshChatPageViewModel extends ViewModel{
    public static final String TITLE_LABEL = "Refresh";
    private User currentUser = null;
    private ChatChannel channel = null;

    private RefreshChatPageState state = new RefreshChatPageState();

    public RefreshChatPageViewModel(User currentUser, ChatChannel channel, RefreshChatPageState state) {
        super("refresh chat page");
        this.channel = channel;
        this.currentUser = currentUser;
        this.state = state;
    }
    public void setState(RefreshChatPageState state){
        this.state = state;
    }
    public RefreshChatPageState getState(){
        return state;
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
