package use_case.go_to_public_profile;

public class GoToPublicProfileInputData {
    final private String target_userid;

    public GoToPublicProfileInputData(String target_userid) {
        this.target_userid = target_userid;
    }

    public String getTargetUserid() {
        return target_userid;
    }
}
