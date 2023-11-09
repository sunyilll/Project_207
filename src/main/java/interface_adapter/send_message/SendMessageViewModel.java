package main.java.interface_adapter.send_message;

import main.java.entity.ChatChannel;
import main.java.entity.User;
import main.java.interface_adapter.ViewModel;

import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;

public class SendMessageViewModel extends ViewModel {
    public static final String TITLE_LABEL = "Chat Channel";
    private final User currentUser;
    private final ChatChannel channel;

    private SendMessageState state = new SendMessageState();
    public SendMessageViewModel(User currentUser, ChatChannel channel){
        super("send message");
        this.currentUser = currentUser;
        this.channel = channel;
    }
    public void setState(SendMessageState state){this.state = state;}
    public SendMessageState getState(){return state;}
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    // Presenter call firePropertyChanged() to let ViewModel know state changed
    public void firePropertyChanged(){support.firePropertyChange("state", null, this.state);}
    public void addPropertyChangeListener(PropertyChangeListener listener){
        support.addPropertyChangeListener(listener);
    }
}
