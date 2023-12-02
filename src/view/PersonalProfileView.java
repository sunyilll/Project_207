package view;

import interface_adapter.go_to_chatl_list.GoToChatListController;
import interface_adapter.go_to_chatl_list.GoToChatListViewModel;
import interface_adapter.go_to_personal_profile.GoToPersonalProfileController;
import interface_adapter.go_to_personal_profile.GoToPersonalProfileState;
import interface_adapter.go_to_personal_profile.GoToPersonalProfileViewModel;
import interface_adapter.go_to_search.GoToSearchController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class PersonalProfileView extends JPanel implements ActionListener, PropertyChangeListener {
    public static final String viewName = "personal profile";
    private final GoToChatListViewModel goToChatListViewModel;
    private final GoToChatListController goToChatListController;
    private final GoToPersonalProfileViewModel goToPersonalProfileViewModel;
    private final GoToPersonalProfileController goToPersonalProfileController;
    private final JPanel homeBar;
    private final JButton editProfile;
    private final JButton signOut;

    private JLabel nickName;
    private JLabel descriptionText;

    public PersonalProfileView(GoToPersonalProfileViewModel goToPersonalProfileViewModel,
                               GoToPersonalProfileController personalProfileController,
                               GoToChatListViewModel goToChatListViewModel,
                               GoToChatListController goToChatListController,
                               GoToSearchController goToSearchController) {
        this.goToChatListViewModel = goToChatListViewModel;
        this.goToChatListViewModel.addPropertyChangeListener(this);
        this.goToChatListController = goToChatListController;
        this.goToPersonalProfileViewModel = goToPersonalProfileViewModel;
        this.goToPersonalProfileViewModel.addPropertyChangeListener(this);
        this.goToPersonalProfileController = personalProfileController;

        homeBar = new HomeBar(goToPersonalProfileViewModel, personalProfileController, goToChatListViewModel,
                goToChatListController, goToSearchController);

        JLabel title = new JLabel(GoToPersonalProfileViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        nickName = new JLabel(goToPersonalProfileViewModel.nicknameText);
        nickName.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

        JLabel descriptionTitle = new JLabel(GoToPersonalProfileViewModel.DESCRIPTION_LABEL);
        content.add(descriptionTitle);
        descriptionText = new JLabel(goToPersonalProfileViewModel.descriptionText);
        content.add(descriptionText);
        JLabel personalityTags = new JLabel(GoToPersonalProfileViewModel.PERSONALITY_TAGS_LABEL);
        content.add(personalityTags);
        // TODO: add personality tags
        JLabel coursesToTeach = new JLabel(GoToPersonalProfileViewModel.COURSES_TO_TEACH_LABEL);
        content.add(coursesToTeach);
        JLabel tutorRating = new JLabel(GoToPersonalProfileViewModel.TUTOR_RATING_LABEL);
        content.add(tutorRating);
        JLabel coursesToLearn = new JLabel(GoToPersonalProfileViewModel.COURSES_TO_LEARN_LABEL);
        content.add(coursesToLearn);
        JLabel studentRating = new JLabel(GoToPersonalProfileViewModel.STUDENT_RATING_LABEL);
        content.add(studentRating);

        JPanel buttons = new JPanel();
        editProfile = new JButton(GoToPersonalProfileViewModel.EDIT_PROFILE_BUTTON_LABEL);
        buttons.add(editProfile);
        signOut = new JButton(GoToPersonalProfileViewModel.SIGN_OUT_BUTTON_LABEL);
        buttons.add(signOut);
        buttons.setLayout(new GridLayout(1, 2));

        JPanel leftAlignedContent = new JPanel();
        leftAlignedContent.add(content);
        leftAlignedContent.setLayout(new FlowLayout(FlowLayout.LEFT));

        JPanel mainPanel = new JPanel();

        mainPanel.add(title);
        mainPanel.add(nickName);
        mainPanel.add(leftAlignedContent);
        mainPanel.add(buttons);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        this.setLayout(new BorderLayout());
        this.add(homeBar, BorderLayout.SOUTH);
        this.add(mainPanel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
//        GoToPersonalProfileState state = (GoToPersonalProfileState) evt.getNewValue();
//        goToPersonalProfileViewModel.setState(state);
//        nickName.setText(goToPersonalProfileViewModel.nicknameText);
//        descriptionText.setText(goToPersonalProfileViewModel.descriptionText);
    }
}
