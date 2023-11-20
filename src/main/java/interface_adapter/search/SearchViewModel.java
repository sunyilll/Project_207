package main.java.interface_adapter.search;

import main.java.interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SearchViewModel extends ViewModel {
    public static final String TITLE_LABEL = "Search Page";
    private SearchState state;

    public SearchViewModel(SearchState state){
        super(TITLE_LABEL);
        this.state = state;
    }
    public void setState(SearchState state){this.state = state;}
    public SearchState getState(){return state;}
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    @Override
    public void firePropertyChanged() {
        //todo: add smth
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
