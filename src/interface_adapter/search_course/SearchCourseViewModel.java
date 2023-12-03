package interface_adapter.search_course;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SearchCourseViewModel extends ViewModel {
    public static final String TITLE_LABEL = "Search Course Page";
    private SearchCourseState state = new SearchCourseState();

    public SearchCourseViewModel(){
        super("search course");
    }
    public void setState(SearchCourseState state){this.state = state;}
    public SearchCourseState getState(){return state;}
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("search course", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
