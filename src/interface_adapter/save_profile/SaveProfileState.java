package interface_adapter.save_profile;

public class SaveProfileState {
    private Boolean saveSuccess = false;
    public SaveProfileState() {
    }
    public SaveProfileState(SaveProfileState copy) {
        this.saveSuccess = copy.saveSuccess;
    }
    public Boolean getSaveSuccess() {
        return saveSuccess;
    }
    public void setSaveSuccess(Boolean saveSuccess) {
        this.saveSuccess = saveSuccess;
    }
}
