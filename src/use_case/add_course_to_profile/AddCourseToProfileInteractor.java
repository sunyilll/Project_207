package use_case.add_course_to_profile;
import entity.User;
import use_case.GetUserDataAccessInterface;

public class AddCourseToProfileInteractor implements AddCourseToProfileInputBoundary{
    final AddCourseToProfileDataAccessInterface dao;
    final GetUserDataAccessInterface userDao;
    final AddCourseToProfileOutputBoundary presenter;
    public AddCourseToProfileInteractor(AddCourseToProfileDataAccessInterface dao,
                                        GetUserDataAccessInterface userDao,
                                        AddCourseToProfileOutputBoundary presenter){
        this.dao = dao;
        this.userDao = userDao;
        this.presenter = presenter;
    }

    @Override
    public void execute(boolean addToTutor, String courseCode) {
        User me = userDao.getCurrentUser();
        if (addToTutor) {
            me.addCourseToTeach(courseCode);
            userDao.save(me);
            dao.addTutor(me, courseCode);
        } else {
            me.addCourseToLearn(courseCode);
            userDao.save(me);
            dao.addStudent(me, courseCode);
        }
        presenter.prepareSuccessView();
    }
}
