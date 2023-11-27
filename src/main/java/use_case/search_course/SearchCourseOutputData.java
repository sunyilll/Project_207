package main.java.use_case.search_course;

public class SearchCourseOutputData {
    private String courseCode;
    String[] listUserNames;
    String[] listPictureUrl;
    Float[] listRating;
    String[] listSlogan;
    public SearchCourseOutputData(String courseCode,
                                  String[] listUserNames,
                                  String[] listPictureUrl,
                                  Float[] listRating,
                                  String[] listSlogan){
        assert listRating.length == listSlogan.length;
        assert listUserNames.length == listPictureUrl.length; // todo: probably change argument to a list
        this.courseCode = courseCode;
        this.listUserNames = listUserNames;
        this.listPictureUrl = listPictureUrl;
        this.listRating = listRating;
        this.listSlogan = listSlogan;
    }
    public void getUsersInfo(){
        return;  //todo: implement me
    }
}
