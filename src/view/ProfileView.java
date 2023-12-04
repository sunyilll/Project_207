package view;

import interface_adapter.go_to_chat_list.GoToChatListController;
import interface_adapter.go_to_chat_list.GoToChatListViewModel;
import interface_adapter.go_to_personal_profile.GoToPersonalProfileController;
import interface_adapter.go_to_personal_profile.GoToPersonalProfileState;
import interface_adapter.go_to_personal_profile.GoToPersonalProfileViewModel;
import interface_adapter.go_to_profile.ProfileViewModel;
import interface_adapter.go_to_search.GoToSearchController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

abstract class ProfileView extends JPanel {
    final ProfileViewModel profileViewModel;
    protected JPanel mainPanel = new JPanel();
    private ProfilePanelBuilder profilePanelBuilder = new ProfilePanelBuilder();

    protected JLabel useridText = new JLabel();
    protected JLabel nicknameText = new JLabel();
    protected JLabel pronounsText = new JLabel();
    protected JLabel descriptionText = new JLabel();
    protected JLabel personalityTagsText = new JLabel();
    protected JLabel tutorRatingText = new JLabel();
    protected JLabel coursesToTeachText = new JLabel();
    protected JLabel tutorAvailabilityText = new JLabel();
    protected JLabel expectedWageText = new JLabel();
    protected JLabel studentRatingText = new JLabel();
    protected JLabel coursesToLearnText = new JLabel();
    protected JLabel expectedPriceText = new JLabel();

    public ProfileView(ProfileViewModel profileViewModel) {
        this.profileViewModel = profileViewModel;
        this.buildMainPanel();
    }

    public void buildMainPanel(){
        profilePanelBuilder.create();

        this.useridText = profilePanelBuilder.add(ProfileViewModel.USERID_LABLE, profileViewModel.useridText, "horizontal");
        this.nicknameText = profilePanelBuilder.add(ProfileViewModel.NICKNAME_LABEL, profileViewModel.nicknameText, "horizontal");
        this.pronounsText = profilePanelBuilder.add(ProfileViewModel.PRONOUNS_LABEL, profileViewModel.pronounsText, "horizontal");
        this.descriptionText = profilePanelBuilder.add(ProfileViewModel.DESCRIPTION_LABEL, profileViewModel.descriptionText, "vertical");
        this.personalityTagsText = profilePanelBuilder.add(ProfileViewModel.PERSONALITY_TAGS_LABEL, profileViewModel.personalityTagsText, "vertical");

        profilePanelBuilder.add(ProfileViewModel.TUTOR_TITLE_LABLE);
        this.tutorRatingText = profilePanelBuilder.add(ProfileViewModel.TUTOR_RATING_LABEL, profileViewModel.tutorRatingText, "horizontal");
        this.coursesToTeachText = profilePanelBuilder.add(ProfileViewModel.COURSES_TO_TEACH_LABEL, profileViewModel.coursesToTeachText, "vertical");
        this.tutorAvailabilityText = profilePanelBuilder.add(ProfileViewModel.TUTOR_AVAILABILITY_LABEL, profileViewModel.tutorAvailabilityText, "vertical");
        this.expectedWageText = profilePanelBuilder.add(ProfileViewModel.EXPECTED_WAGE_LABEL, profileViewModel.expectedWageText, "vertical");

        profilePanelBuilder.add(ProfileViewModel.STUDENT_TITLE_LABLE);
        this.studentRatingText = profilePanelBuilder.add(ProfileViewModel.STUDENT_RATING_LABEL, profileViewModel.studentRatingText, "horizontal");
        this.coursesToLearnText = profilePanelBuilder.add(ProfileViewModel.COURSES_TO_LEARN_LABEL, profileViewModel.coursesToLearnText, "vertical");
        this.expectedPriceText = profilePanelBuilder.add(ProfileViewModel.EXPECTED_PRICE_LABEL, profileViewModel.expectedPriceText, "vertical");

        mainPanel = profilePanelBuilder.build();
    }

    public void updateMainPanel() {
        this.useridText.setText(profileViewModel.useridText);
        this.nicknameText.setText(profileViewModel.nicknameText);
        this.pronounsText.setText(profileViewModel.pronounsText);
        this.descriptionText.setText(profileViewModel.descriptionText);
        this.personalityTagsText.setText(profileViewModel.personalityTagsText);
        this.tutorRatingText.setText(profileViewModel.tutorRatingText);
        this.coursesToTeachText.setText(profileViewModel.coursesToTeachText);
        this.tutorAvailabilityText.setText(profileViewModel.tutorAvailabilityText);
        this.expectedWageText.setText(profileViewModel.expectedWageText);
        this.studentRatingText.setText(profileViewModel.studentRatingText);
        this.coursesToLearnText.setText(profileViewModel.coursesToLearnText);
        this.expectedPriceText.setText(profileViewModel.expectedPriceText);

    }
}
