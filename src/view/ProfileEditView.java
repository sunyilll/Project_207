package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.edit_profile.EditProfileState;
import interface_adapter.edit_profile.EditProfileViewModel;
import interface_adapter.go_to_personal_profile.GoToPersonalProfileController;
import interface_adapter.go_to_personal_profile.GoToPersonalProfileState;
import interface_adapter.go_to_personal_profile.GoToPersonalProfileViewModel;
import interface_adapter.login.LoginState;
import interface_adapter.save_profile.SaveProfileController;
import interface_adapter.save_profile.SaveProfileState;
import interface_adapter.save_profile.SaveProfileViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ProfileEditView extends JPanel implements ActionListener, PropertyChangeListener {
    public static final String viewName = "edit profile";
    private ViewManagerModel viewManagerModel;
    private SaveProfileController saveProfileController;
    private SaveProfileViewModel saveProfileViewModel;
    private GoToPersonalProfileViewModel goToPersonalProfileViewModel;
    private GoToPersonalProfileController goToPersonalProfileController;
    private EditProfileViewModel editProfileViewModel;
    private final JButton save;
    private final JButton back;
    private final JPanel mainPanel = new JPanel();
    final JLabel userIDLabel = new JLabel();
    final JTextField nicknameInput = new JTextField(15);
    final JTextField pronounsInput = new JTextField(15);
    final JTextField personalityTagInput = new JTextField(15);
    final JTextField descriptionInput = new JTextField(15);
    final JTextField tutorAvailabilityInput = new JTextField(15);
    final JTextField expectedWageInput = new JTextField(15);
    final JTextField modeOfTeachingInput = new JTextField(15);
    final JTextField expectedPriceInput = new JTextField(15);
    final JTextField modeOfLearningInput = new JTextField(15);
    public ProfileEditView(ViewManagerModel viewManagerModel,
                           SaveProfileController saveProfileController,
                           SaveProfileViewModel saveProfileViewModel,
                           GoToPersonalProfileViewModel goToPersonalProfileViewModel,
                           GoToPersonalProfileController goToPersonalProfileController, EditProfileViewModel editProfileViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.saveProfileController = saveProfileController;
        this.saveProfileViewModel = saveProfileViewModel;
        this.goToPersonalProfileViewModel = goToPersonalProfileViewModel;
        this.goToPersonalProfileController = goToPersonalProfileController;
        this.editProfileViewModel = editProfileViewModel;
        saveProfileViewModel.addPropertyChangeListener(this);
        goToPersonalProfileViewModel.addPropertyChangeListener(this);
        editProfileViewModel.addPropertyChangeListener(this);

        JPanel topBar = new JPanel();
        back = new JButton(EditProfileViewModel.BACK_BUTTON_LABEL);
        topBar.add(back);
        topBar.setLayout(new GridLayout(1, 1));

        JPanel bottomBar = new JPanel();
        save = new JButton(EditProfileViewModel.SAVE_BUTTON_LABEL);
        bottomBar.add(save);
        bottomBar.setLayout(new GridLayout(1, 1));

        buildMainPanel();

        this.setLayout(new BorderLayout());
        this.add(topBar, BorderLayout.NORTH);
        this.add(mainPanel, BorderLayout.CENTER);
        this.add(bottomBar, BorderLayout.SOUTH);

        back.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    goToPersonalProfileController.execute();
                    viewManagerModel.setActiveView(goToPersonalProfileViewModel.getViewName());
                    viewManagerModel.firePropertyChanged();
                }
            }
        );
        save.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    EditProfileState state = editProfileViewModel.getState();
                    saveProfileController.execute(nicknameInput.getText(), pronounsInput.getText(), personalityTagInput.getText(), descriptionInput.getText(),
                            editProfileViewModel.coursesToTeachText,
                            tutorAvailabilityInput.getText(), expectedWageInput.getText(), modeOfTeachingInput.getText(),editProfileViewModel.coursesToLearnText,
                            expectedPriceInput.getText(), modeOfLearningInput.getText());
                    goToPersonalProfileController.execute();
                    viewManagerModel.setActiveView(goToPersonalProfileViewModel.getViewName());
                    viewManagerModel.firePropertyChanged();
                }
            }
        );
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("edit profile")) {
            // TODO
            updateMainPanel();
        }
    }

    private void updateMainPanel() {
        nicknameInput.setText(editProfileViewModel.nicknameText);
        pronounsInput.setText(editProfileViewModel.pronounsText);
        personalityTagInput.setText(editProfileViewModel.personalityTagsText);
        descriptionInput.setText(editProfileViewModel.descriptionText);
        tutorAvailabilityInput.setText(editProfileViewModel.tutorAvailabilityText);
        expectedWageInput.setText(editProfileViewModel.expectedWageText);
        expectedPriceInput.setText(editProfileViewModel.expectedPriceText);
    }
    private void buildMainPanel() {
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        LabelLabelPanel userIDInfo = new LabelLabelPanel(
                new JLabel(EditProfileViewModel.USERID_LABLE), userIDLabel);
        LabelTextPanel nicknameInfo = new LabelTextPanel(new JLabel(EditProfileViewModel.NICKNAME_LABEL), nicknameInput);
        LabelTextPanel pronounsInfo = new LabelTextPanel(new JLabel(EditProfileViewModel.PRONOUNS_LABEL), pronounsInput);
        LabelTextPanel personalityTagInfo = new LabelTextPanel(new JLabel(EditProfileViewModel.PERSONALITY_TAGS_LABEL), personalityTagInput);
        LabelTextPanel descriptionInfo = new LabelTextPanel(new JLabel(EditProfileViewModel.DESCRIPTION_LABEL), descriptionInput);
        LabelTextPanel tutorAvailabilityInfo = new LabelTextPanel(new JLabel(EditProfileViewModel.TUTOR_AVAILABILITY_LABEL), tutorAvailabilityInput);
        LabelTextPanel expectedWageInfo = new LabelTextPanel(new JLabel(EditProfileViewModel.EXPECTED_WAGE_LABEL), expectedWageInput);
        LabelTextPanel expectedPriceInfo = new LabelTextPanel(new JLabel(EditProfileViewModel.EXPECTED_PRICE_LABEL), expectedPriceInput);
        mainPanel.add(userIDInfo);
        mainPanel.add(nicknameInfo);
        mainPanel.add(pronounsInfo);
        mainPanel.add(personalityTagInfo);
        mainPanel.add(descriptionInfo);
        mainPanel.add(tutorAvailabilityInfo);
        mainPanel.add(expectedWageInfo);
        mainPanel.add(expectedPriceInfo);
    }
}
