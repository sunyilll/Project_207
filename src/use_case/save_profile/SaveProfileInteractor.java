package use_case.save_profile;

import entity.User;
import entity.UserBuilder;

public class SaveProfileInteractor implements SaveProfileInputBoundary {
    final private SaveProfileOutputBoundary presenter;
    final private SaveProfileDataAccessInterface dataAccessObject;
    final private UserBuilder userBuilder = new UserBuilder();

    public SaveProfileInteractor(SaveProfileOutputBoundary presenter,
                                 SaveProfileDataAccessInterface dataAccessObject) {
        this.presenter = presenter;
        this.dataAccessObject = dataAccessObject;
    }
    @Override
    public void execute(SaveProfileInputData inputData) {
        User user = dataAccessObject.getCurrentUser();
        if (user == null) {
            presenter.prepareFailView("User not logged in.");
            return;
        }
        userBuilder.create(user.getUserID(), inputData.getNickname(),user.getPassword());
        userBuilder.setPronouns(inputData.getPronouns());
        userBuilder.setDescription(inputData.getDescription());
        userBuilder.setTutorAvailability(inputData.getTutorAvailability());
        for (String tag : inputData.getPersonalityTags()) {
            userBuilder.addPersonalityTag(tag);
        }
        for (String course : inputData.getCoursesToTeach()) {
            userBuilder.addCoursesToTeach(course);
        }
        for (String course : inputData.getCoursesToLearn()) {
            userBuilder.addCoursesToLearn(course);
        }
        for (String mode : inputData.getPreferredModeOfTeaching()) {
            userBuilder.addPreferredModeOfTeaching(mode);
        }
        for (String mode : inputData.getPreferredModeOfLearning()) {
            userBuilder.addPreferredModeOfLearning(mode);
        }
        for (String course : inputData.getExpectedWage().keySet()) {
            userBuilder.setExpectedWage(course, inputData.getExpectedWage().get(course));
        }
        for (String course : inputData.getExpectedPrice().keySet()) {
            userBuilder.setExpectedPrice(course, inputData.getExpectedPrice().get(course));
        }

        user = userBuilder.getUser();
        dataAccessObject.save(user);
        SaveProfileOutputData outputData = new SaveProfileOutputData(true);

        presenter.prepareSuccessView(outputData);
    }
}
