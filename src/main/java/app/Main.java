package main.java.app;

import main.java.data_access.*;
import main.java.entity.*;
import main.java.interface_adapter.go_to_personal_profile.GoToPersonalProfileViewModel;
import main.java.interface_adapter.search_course.SearchCourseViewModel;
import main.java.interface_adapter.search_course_result.SearchCourseResultState;
import main.java.interface_adapter.search_course_result.SearchCourseResultViewModel;
import main.java.view.*;

import main.java.interface_adapter.ViewManagerModel;
import main.java.interface_adapter.To_signup.ToSignupViewModel;
import main.java.interface_adapter.login.LoginViewModel;
import main.java.interface_adapter.signup.SignupViewModel;
import main.java.interface_adapter.refresh_chat_page.RefreshChatPageViewModel;
import main.java.interface_adapter.send_message.SendMessageViewModel;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.time.LocalDateTime;


public class Main {

    public static void main(String[] args) {
        // Login & Signup
        JFrame application = new FrameModel("DYP");

        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();

        // The various View objects. Only one view is visible at a time.
        JPanel views = new JPanel(cardLayout);

        application.add(views);

        // This keeps track of and manages which view is currently showing.
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        LoginViewModel loginViewModel = new LoginViewModel();
        SignupViewModel signupViewModel = new SignupViewModel();
        ToSignupViewModel toSignupViewModel = new ToSignupViewModel();
        SearchCourseViewModel searchCourseViewModel = new SearchCourseViewModel();
        SearchCourseResultViewModel searchCourseResultViewModel = new SearchCourseResultViewModel();
        GoToPersonalProfileViewModel goToPersonalProfileViewModel = new GoToPersonalProfileViewModel();

        FileUserDataAccessObject userDataAccessObject;
        try {
            userDataAccessObject = new FileUserDataAccessObject("./users.csv", new UserFactory() {
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        FileCourseDataAccessObject fileCourseDataAccessObject;
        try {
            fileCourseDataAccessObject = new FileCourseDataAccessObject("./courses.csv", new CourseFactory(userDataAccessObject));
        } catch (IOException e){throw new RuntimeException(e);}

        SignupView signupView = SignupUseCaseFactory.create(viewManagerModel, loginViewModel, signupViewModel, userDataAccessObject);
        views.add(signupView, signupView.viewName);

        LoginView loginView = LoginUseCaseFactory.create(viewManagerModel, loginViewModel, toSignupViewModel, signupViewModel,userDataAccessObject, userDataAccessObject);
        views.add(loginView, loginView.viewName);

        SearchCourseView searchCourseView= SearchCourseUseCaseFactory.create(viewManagerModel, searchCourseViewModel, searchCourseResultViewModel, fileCourseDataAccessObject, userDataAccessObject);
        views.add(searchCourseView, searchCourseView.viewName);

        PersonalProfileView personalProfileView = ToPersonalProfileUseCaseFactory.create(viewManagerModel, goToPersonalProfileViewModel);
        views.add(personalProfileView, personalProfileView.viewName);

        viewManagerModel.setActiveView(searchCourseView.viewName);

        viewManagerModel.firePropertyChanged();

        application.setVisible(true);
    }
}
