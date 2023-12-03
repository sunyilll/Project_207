package interface_adapter;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

public class ViewManagerModel {
    public boolean propertyChangedFired;
    String activeViewName;
    private List<String> allPreviousViewNames = new ArrayList<>();

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public String getActiveView() {
        return activeViewName;
    }

    public void setActiveView(String activeView) {
        this.activeViewName = activeView;
    }

    // This is what the Signup Presenter will call to let the ViewModel know
    // to alert the View
    public void firePropertyChanged() {
        support.firePropertyChange("view", null, this.activeViewName);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
    public String popPreviousView() {
        if (allPreviousViewNames.size() > 0) {
            String prevView = allPreviousViewNames.get(allPreviousViewNames.size() - 1);
            allPreviousViewNames.remove(allPreviousViewNames.size() - 1);
            return prevView;
        }
        return null;
    }
    public void addPreviousView(String viewName) {
        allPreviousViewNames.add(viewName);
    }
}
