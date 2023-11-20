package main.java.app;

import main.java.data_access.SendMessageDataAccessObject;
import main.java.entity.*;
import main.java.interface_adapter.ViewManagerModel;
import main.java.interface_adapter.go_to_personal_profile.*;
import main.java.interface_adapter.send_message.*;
import main.java.use_case.go_to_personal_profile.*;
import main.java.view.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // Build the main program window, the main panel containing the
        // various cards, and the layout, and stitch them together.

        // The main application window.
        JFrame application = new JFrame("Example");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();

        // The various View objects. Only one view is visible at a time.
        JPanel views = new JPanel(cardLayout);
        application.add(views);

        // This keeps track of and manages which view is currently showing.
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        // The data for the views, such as username and password, are in the ViewModels.
        // This information will be changed by a presenter object that is reporting the
        // results from the use case. The ViewModels are observable, and will
        // be observed by the Views.
        User current_user = new User("user1", "sushidog", "123456","she/her");
        GoToPersonalProfileViewModel personalProfileViewModel = new GoToPersonalProfileViewModel();

        GoToPersonalProfileOutputBoundary personalProfilePresenter = new GoToPersonalProfilePresenter(personalProfileViewModel, viewManagerModel);

        GoToPersonalProfileInteractor personalProfileInteractor = new GoToPersonalProfileInteractor(personalProfilePresenter);

        GoToPersonalProfileController personalProfileController = new GoToPersonalProfileController(personalProfileInteractor);

        GoToPersonalProfileState personalProfileState = new GoToPersonalProfileState();
        personalProfileState.setUser(current_user);
        personalProfileViewModel.setState(personalProfileState);

        PersonalProfileView personalProfileView = new PersonalProfileView(personalProfileController, personalProfileViewModel);
        views.add(personalProfileView, personalProfileView.viewName);

        viewManagerModel.setActiveView(personalProfileView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }
}
