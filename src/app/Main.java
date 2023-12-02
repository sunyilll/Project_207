package app;

import data_access.*;
import entity.*;
import interface_adapter.go_to_chatl_list.GoToChatListViewModel;
import interface_adapter.go_to_personal_profile.GoToPersonalProfileViewModel;
import interface_adapter.search_course.SearchCourseViewModel;
import interface_adapter.search_course_result.SearchCourseResultViewModel;
import view.*;

import interface_adapter.ViewManagerModel;
import interface_adapter.go_to_signup.ToSignupViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupViewModel;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;


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

        // This is a temporary solution to the problem of not having a user to pass to the home bar.
        // FIXME: Will be removed.
        User user = new User("heyyyy", "ARandomUser", "1234556");

        LoginViewModel loginViewModel = new LoginViewModel();
        SignupViewModel signupViewModel = new SignupViewModel();
        ToSignupViewModel toSignupViewModel = new ToSignupViewModel();
        SearchCourseViewModel searchCourseViewModel = new SearchCourseViewModel();
        SearchCourseResultViewModel searchCourseResultViewModel = new SearchCourseResultViewModel();
        GoToPersonalProfileViewModel goToPersonalProfileViewModel = new GoToPersonalProfileViewModel();
        goToPersonalProfileViewModel.getState().setUser(user);

        // FIXME: instantiating a ViewModel shouldn't depend on inputs
        GoToChatListViewModel goToChatListViewModel = new GoToChatListViewModel();


        FileUserDataAccessObject userDataAccessObject;
        try {
            userDataAccessObject = new FileUserDataAccessObject("./users.csv", new UserFactory() {
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        GoToChatListDataAccessObject goToChatListDataAccessObject;
        goToChatListDataAccessObject = new GoToChatListDataAccessObject();


        FileCourseDataAccessObject fileCourseDataAccessObject;
        try {
            fileCourseDataAccessObject = new FileCourseDataAccessObject("./courses.csv", new CourseFactory(userDataAccessObject));
        } catch (IOException e){throw new RuntimeException(e);}

        SignupView signupView = SignupUseCaseFactory.create(viewManagerModel, loginViewModel, signupViewModel,
                userDataAccessObject);
        views.add(signupView, signupView.viewName);

        LoginView loginView = LoginUseCaseFactory.create(viewManagerModel, loginViewModel, toSignupViewModel, signupViewModel,searchCourseViewModel,userDataAccessObject, userDataAccessObject);
        views.add(loginView, loginView.viewName);

        SearchCourseView searchCourseView= SearchCourseUseCaseFactory.create(viewManagerModel, searchCourseViewModel,
                searchCourseResultViewModel, fileCourseDataAccessObject, userDataAccessObject, goToPersonalProfileViewModel, goToChatListViewModel, goToChatListDataAccessObject);
        views.add(searchCourseView, searchCourseView.viewName);

        SearchCourseResultView searchCourseResultView = SearchCourseResultUseCaseFactory.create(viewManagerModel, searchCourseViewModel,
                searchCourseResultViewModel, goToPersonalProfileViewModel, goToChatListViewModel, goToChatListDataAccessObject);
        views.add(searchCourseResultView, searchCourseResultView.viewName);
      
        PersonalProfileView personalProfileView = ToPersonalProfileUseCaseFactory.create(viewManagerModel,
                goToPersonalProfileViewModel, goToChatListViewModel, goToChatListDataAccessObject, searchCourseViewModel);
        views.add(personalProfileView, personalProfileView.viewName);

        // This is for testing purposes. Please delete this for final submission
//        HomeBar homeBar = HomeBarUseCaseFactory.create(viewManagerModel, goToPersonalProfileViewModel,
//                goToChatListViewModel, goToChatListDataAccessObject);
//        views.add(homeBar, homeBar.viewName);

        // This is for testing purposes. Please change the View name to the one you want to test.
//        viewManagerModel.setActiveView(homeBar.viewName);
        viewManagerModel.setActiveView(loginView.viewName);

        viewManagerModel.firePropertyChanged();

        application.setVisible(true);
    }
}
