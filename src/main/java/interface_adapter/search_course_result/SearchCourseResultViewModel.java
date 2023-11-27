package main.java.interface_adapter.search_course_result;

import main.java.interface_adapter.ViewModel;
import main.java.interface_adapter.search_course.SearchCourseState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SearchCourseResultViewModel extends ViewModel{
    private SearchCourseResultState state;


    public SearchCourseResultViewModel(SearchCourseResultState state){
        super("search course result");
        this.state = state;
    }

    public void setState(SearchCourseResultState state){this.state = state;}
    public SearchCourseResultState getState(){return state;}
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
    @Override
    public void firePropertyChanged() {
        //todo: add smth
    }
}