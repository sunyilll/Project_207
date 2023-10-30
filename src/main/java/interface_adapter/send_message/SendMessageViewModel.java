package main.java.interface_adapter.send_message;

import main.java.interface_adapter.ViewModel;

import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;

public class SendMessageViewModel extends ViewModel {
    private SendMessageState state = new SendMessageState();
    public SendMessageViewModel(){super("send message");}
    public void setState(SendMessageState state){this.state = state;}
    public SendMessageState getState(){return state;}
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    // Presenter call firePropertyChanged() to let ViewModel know state changed
    public void firePropertyChanged(){support.firePropertyChange("state", null, this.state);}
    public void addPropertyChangeListener(PropertyChangeListener listener){
        support.addPropertyChangeListener(listener);
    }
}
