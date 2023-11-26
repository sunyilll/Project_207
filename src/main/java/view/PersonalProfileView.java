package main.java.view;

import main.java.interface_adapter.go_to_personal_profile.GoToPersonalProfileController;
import main.java.interface_adapter.go_to_personal_profile.GoToPersonalProfileState;
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
    private final JButton home;
    private final JButton chatList;
    private final JButton personalProfile;

    private JLabel nickName;
    private JLabel descriptionText;

    public PersonalProfileView(GoToPersonalProfileController personalProfileController, GoToPersonalProfileViewModel personalProfileViewModel) {
        this.personalProfileController = personalProfileController;
        this.personalProfileViewModel = personalProfileViewModel;
        personalProfileViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(personalProfileViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        nickName = new JLabel(personalProfileViewModel.nicknameText);
        nickName.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

        JLabel descriptionTitle = new JLabel(personalProfileViewModel.DESCRIPTION_LABEL);
        content.add(descriptionTitle);
        descriptionText = new JLabel(personalProfileViewModel.descriptionText);
        content.add(descriptionText);
        JLabel personalityTags = new JLabel(personalProfileViewModel.PERSONALITY_TAGS_LABEL);
        content.add(personalityTags);
        // TODO: add personality tags
        JLabel coursesToTeach = new JLabel(personalProfileViewModel.COURSES_TO_TEACH_LABEL);
        content.add(coursesToTeach);
        JLabel tutorRating = new JLabel(personalProfileViewModel.TUTOR_RATING_LABEL);
        content.add(tutorRating);
        JLabel coursesToLearn = new JLabel(personalProfileViewModel.COURSES_TO_LEARN_LABEL);
        content.add(coursesToLearn);
        JLabel studentRating = new JLabel(personalProfileViewModel.STUDENT_RATING_LABEL);
        content.add(studentRating);

        JPanel buttons = new JPanel();
        editProfile = new JButton(personalProfileViewModel.EDIT_PROFILE_BUTTON_LABEL);
        buttons.add(editProfile);
        signOut = new JButton(personalProfileViewModel.SIGN_OUT_BUTTON_LABEL);
        buttons.add(signOut);
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));
        buttons.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel bottomBar = new JPanel();
        home = new JButton(personalProfileViewModel.HOME_BUTTON_LABEL);
        bottomBar.add(home);
        chatList = new JButton(personalProfileViewModel.CHAT_LIST_BUTTON_LABEL);
        bottomBar.add(chatList);
        personalProfile = new JButton(personalProfileViewModel.PRIVATE_PROFILE_BUTTON_LABEL);
        bottomBar.add(personalProfile);
        bottomBar.setLayout(new BoxLayout(bottomBar, BoxLayout.X_AXIS));
        bottomBar.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(nickName);

        JPanel contentwrapper = new JPanel();
        contentwrapper.add(content);
        contentwrapper.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.add(contentwrapper);
        contentwrapper.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.add(buttons);
        this.add(bottomBar);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(personalProfile)) {
            personalProfileController.excute(personalProfileViewModel.getState().getUser());
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        GoToPersonalProfileState state = (GoToPersonalProfileState) evt.getNewValue();
        personalProfileViewModel.setState(state);
        nickName.setText(personalProfileViewModel.nicknameText);
        descriptionText.setText(personalProfileViewModel.descriptionText);
    }
}
