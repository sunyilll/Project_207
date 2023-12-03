package interface_adapter;

import data_structure.ArrayListStack;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ViewManagerModel {
    private String activeViewName;
    private ArrayListStack allPreviousViewNames = new ArrayListStack();

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
        return (String) allPreviousViewNames.pop();
    }
    public void addPreviousView(String viewName) {
        allPreviousViewNames.push(viewName);
    }
}
