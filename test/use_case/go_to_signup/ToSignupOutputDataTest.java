package use_case.go_to_signup;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ToSignupOutputDataTest {
    private final ToSignupOutputData toSignupOutputData = new ToSignupOutputData("username");
    @Test
    void getUsername() {
        assertEquals("username", toSignupOutputData.getUsername());
    }
}