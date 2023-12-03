package interface_adapter.add_course_to_profile;

import use_case.add_course_to_profile.AddCourseToProfileInputBoundary;
import use_case.add_course_to_profile.AddCourseToProfileInteractor;

public class AddCourseToProfileController {
    final  AddCourseToProfileInputBoundary interactor;
    public AddCourseToProfileController(AddCourseToProfileInputBoundary interactor){
        this.interactor = interactor;
    }
    public void execute(boolean addToTutor, String courseCode){
        this.interactor.execute(addToTutor, courseCode);
    }
}
