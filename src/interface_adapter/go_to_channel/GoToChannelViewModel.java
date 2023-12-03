package interface_adapter.go_to_channel;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class GoToChannelViewModel extends ViewModel {
    public static final String TITLE = "go to channel";
    private GoToChannelState state = new GoToChannelState();
    public GoToChannelViewModel() {
        super("go to channel");
    }
    public GoToChannelState getState() {
        return state;
    }
    public void setState(GoToChannelState goToChannelState) {
        this.state = goToChannelState;
    }
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("channel", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
