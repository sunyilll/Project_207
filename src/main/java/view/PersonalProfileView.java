package main.java.view;

import main.java.interface_adapter.go_to_personal_profile.GoToPersonalProfileController;
import main.java.interface_adapter.go_to_personal_profile.GoToPersonalProfileViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class PersonalProfileView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "personal profile";
    private final GoToPersonalProfileViewModel personalProfileViewModel;
    private final GoToPersonalProfileController personalProfileController;

    private final JButton editProfile;
    private final JButton signOut;

    public PersonalProfileView(GoToPersonalProfileController personalProfileController, GoToPersonalProfileViewModel personalProfileViewModel) {
        this.personalProfileController = personalProfileController;
        this.personalProfileViewModel = personalProfileViewModel;
        personalProfileViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(GoToPersonalProfileViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel nickName = new JLabel(GoToPersonalProfileViewModel.NICKNAME_LABEL);
        nickName.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel bio
        LabelTextPanel passwordInfo = new LabelTextPanel(
                new JLabel(SignupViewModel.PASSWORD_LABEL), passwordInputField);
        LabelTextPanel repeatPasswordInfo = new LabelTextPanel(
                new JLabel(SignupViewModel.REPEAT_PASSWORD_LABEL), repeatPasswordInputField);

        JPanel buttons = new JPanel();
        editProfile = new JButton(GoToPersonalProfileViewModel.EDIT_PROFILE_BUTTON_LABEL);
        buttons.add(editProfile);
        signOut = new JButton(GoToPersonalProfileViewModel.SIGN_OUT_BUTTON_LABEL);
        buttons.add(signOut);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(buttons);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
