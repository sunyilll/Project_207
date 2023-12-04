package app;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void main() {
        try {
            Main.main(new String[]{});
        } catch (Exception e) {
            fail();
        }
    }
}