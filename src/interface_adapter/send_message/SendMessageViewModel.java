package interface_adapter.send_message;

import entity.ChatChannel;
import entity.User;
import interface_adapter.ViewModel;

import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;

public class SendMessageViewModel extends ViewModel {
    public static final String TITLE_LABEL = "Chat Channel";
    private User currentUser = null;
    private ChatChannel channel = null;

    private SendMessageState state = new SendMessageState();

//    public SendMessageViewModel(){
//        gsuper("send message");
//    }//这个不应该存在，必须用user init

    public SendMessageViewModel(User currentUser, ChatChannel channel, SendMessageState state){
        super("send message");
        this.currentUser = currentUser;
        this.channel = channel;
        this.state = state;
    }
    public void setState(SendMessageState state){this.state = state;}
    public SendMessageState getState(){return state;}
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    // Presenter call firePropertyChanged() to let ViewModel know state changed
    public void firePropertyChanged(){
        if (this.state.getUser_id() != null) {
            support.firePropertyChange("state", null, this.state);
        } else {
            this.state.setUser_id(currentUser.getUserID());
            this.state.setChannel(channel);
        }
    }
    public void addPropertyChangeListener(PropertyChangeListener listener){
        support.addPropertyChangeListener(listener);
    }
}
