package use_case.save_profile;

import entity.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SaveProfileOutputData {
    private boolean saveSuccess;
    public SaveProfileOutputData(Boolean saveSuccess) {
        this.saveSuccess = saveSuccess;
    }
}
