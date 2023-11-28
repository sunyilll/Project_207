package main.java.view;

import main.java.interface_adapter.login.LoginController;
import main.java.interface_adapter.login.LoginState;
import main.java.interface_adapter.login.LoginViewModel;
import main.java.interface_adapter.signup.SignupViewModel;
import main.java.interface_adapter.signup.SignupState;
import main.java.interface_adapter.To_signup.ToSignupState;
import main.java.interface_adapter.search.SearchViewModel;

import main.java.interface_adapter.To_signup.ToSignupViewModel;
import main.java.interface_adapter.To_signup.ToSignupController;



import main.java.interface_adapter.signup.SignupController;
import main.java.interface_adapter.ViewManagerModel;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class LoginView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "log in";
    private final LoginViewModel loginViewModel;
    private final ToSignupViewModel toSignupViewModel;
    private final SignupViewModel signupViewModel;
    private final SearchViewModel searchViewModel;


    private final ViewManagerModel viewManagerModel;




    final JTextField usernameInputField = new JTextField(15);
    private final JLabel usernameErrorField = new JLabel();

    final JPasswordField passwordInputField = new JPasswordField(15);
    private final JLabel passwordErrorField = new JLabel();

    private final ToSignupController toSignupController;


    final JButton logIn;
    final JButton tosignup;
    final JButton cancel;
    private final LoginController loginController;


    public LoginView(LoginViewModel loginViewModel, ToSignupViewModel toSignupViewModel, SignupViewModel signupViewModel, ViewManagerModel viewManagerModel,
                     LoginController controller, ToSignupController toSignupController, SearchViewModel searchViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.toSignupController = toSignupController;
        this.toSignupViewModel = toSignupViewModel;
        this.searchViewModel = searchViewModel;
        this.signupViewModel = signupViewModel;
        this.loginController = controller;
        this.loginViewModel = loginViewModel;
        this.loginViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel("Login Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel usernameInfo = new LabelTextPanel(
                new JLabel("Username"), usernameInputField);
        LabelTextPanel passwordInfo = new LabelTextPanel(
                new JLabel("Password"), passwordInputField);

        JPanel buttons = new JPanel();
        logIn = new JButton(loginViewModel.LOGIN_BUTTON_LABEL);
        buttons.add(logIn);
        tosignup = new JButton(toSignupViewModel.TOSIGNUP_BUTTON_LABEL);
        buttons.add(tosignup);
        cancel = new JButton(loginViewModel.CANCEL_BUTTON_LABEL);
        buttons.add(cancel);

        logIn.addActionListener(                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
//                    public void actionPerformed(ActionEvent evt) {
//                        viewManagerModel.setActiveView(searchViewModel.getViewName());
//                        viewManagerModel.firePropertyChanged();
//                    }
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(logIn)) {
                            LoginState currentState = loginViewModel.getState();

                            loginController.execute(
                                    currentState.getUsername(),
                                    currentState.getPassword()
                            );
                        }
                    }
                }
        );

        tosignup.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        viewManagerModel.setActiveView(signupViewModel.getViewName());
                        viewManagerModel.firePropertyChanged();
                    }
//                    public void actionPerformed(ActionEvent evt) {
//                        if (evt.getSource().equals(tosignup)) {
////                            ToSignupState currentState = ToSignupViewModel.getState();
//
//                            toSignupController.execute(
//
//                            );
//                        }
//                    }

                }
        );

        cancel.addActionListener(this);

        usernameInputField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                LoginState currentState = loginViewModel.getState();
                currentState.setUsername(usernameInputField.getText() + e.getKeyChar());
                loginViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        passwordInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        LoginState currentState = loginViewModel.getState();
                        currentState.setPassword(passwordInputField.getText() + e.getKeyChar());
                        loginViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });

        this.add(title);
        this.add(usernameInfo);
        this.add(usernameErrorField);
        this.add(passwordInfo);
        this.add(passwordErrorField);
        this.add(buttons);
    }

    /**
     * React to a button click that results in evt.
     */
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        LoginState state = (LoginState) evt.getNewValue();
        setFields(state);
    }

    private void setFields(LoginState state) {
        usernameInputField.setText(state.getUsername());
    }

}