package use_case.send_message;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SendMessageOutputDataTest {
    private final SendMessageOutputData testOutputData = new SendMessageOutputData("Sent successfully.");
    @Test
    void getMessage() {
        assertEquals("Sent successfully.", testOutputData.getMessage());
    }
}