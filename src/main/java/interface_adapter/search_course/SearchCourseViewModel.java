package main.java.interface_adapter.search_course;

import main.java.interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SearchCourseViewModel extends ViewModel {
    public static final String TITLE_LABEL = "Search Course Page";
    private SearchCourseState state;

    public SearchCourseViewModel(SearchCourseState state){
        super("search course");
        this.state = state;
    }
    public void setState(SearchCourseState state){this.state = state;}
    public SearchCourseState getState(){return state;}
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
