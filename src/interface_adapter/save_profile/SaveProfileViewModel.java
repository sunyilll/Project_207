package interface_adapter.save_profile;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SaveProfileViewModel {
    private SaveProfileState state = new SaveProfileState();
    public SaveProfileViewModel() {
    }
    public SaveProfileState getState() {
        return state;
    }
    public void setState(SaveProfileState state) {
        this.state = state;
    }
    public String getViewName() {
        return "edit profile";
    }
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    public void firePropertyChanged() {
        support.firePropertyChange("save profile", null, this.state);
    }
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

}
