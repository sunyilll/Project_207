package interface_adapter.refresh_chat_page;


import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class RefreshChatPageViewModel extends ViewModel{
    public static final String TITLE_LABEL = "Refresh";

    private RefreshChatPageState state = new RefreshChatPageState();
    public RefreshChatPageViewModel() {
        super("refresh chat page");
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
