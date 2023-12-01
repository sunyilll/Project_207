package interface_adapter.search_course_result;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SearchCourseResultViewModel extends ViewModel{
    private SearchCourseResultState state = new SearchCourseResultState();


    public SearchCourseResultViewModel(){
        super("search course result");
    }
    public void setState(SearchCourseResultState state){this.state = state;}
    public SearchCourseResultState getState(){return this.state;}
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