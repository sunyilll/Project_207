package main.java.interface_adapter.go_to_personal_profile;

import main.java.interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;

public class GoToPersonalProfileViewModel extends ViewModel {
    public GoToPersonalProfileViewModel(String viewName) {
        super(viewName);
    }

    @Override
    public void firePropertyChanged() {
        // TODO Auto-generated method stub
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        // TODO Auto-generated method stub
    }
}
