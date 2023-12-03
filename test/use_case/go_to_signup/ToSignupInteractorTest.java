package use_case.go_to_signup;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ToSignupInteractorTest {
    private final ToSignupInteractor toSignupInteractor = new ToSignupInteractor();
    @Test
    void execute() {
        ToSignupInputData toSignupInputData = new ToSignupInputData();
        toSignupInteractor.execute(toSignupInputData);
    }
}