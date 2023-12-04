package api;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SendBirdAPITest {
    SendBirdAPI sendBirdAPI = new SendBirdAPI("https://api-1F4C3D4F-01DB-4A99-8704-BE4CB1FE3AE5.sendbird.com/v3",
            "1F4C3D4F-01DB-4A99-8704-BE4CB1FE3AE5",
            "0ecfef313ab2989479b70e30e3ee37a1d105b770");
    @Test
    void getUser() {
        try{
            sendBirdAPI.getUser("test1");
        } catch (RuntimeException e){
            fail();
        }
    }

    @Test
    void getMessage() {
        try{
            sendBirdAPI.getMessage();
        } catch (RuntimeException e){
            fail();
        }
    }

    @Test
    void getAllChatChannels() {
        try{
            sendBirdAPI.getAllChatChannels("test1");
        } catch (RuntimeException e){
            fail();
        }
    }
}