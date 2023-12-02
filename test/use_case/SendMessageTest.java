package use_case;
import data_access.SendMessageDataAccessObject;
import entity.ChatChannel;
import entity.User;
import interface_adapter.ViewManagerModel;
import interface_adapter.send_message.SendMessagePresenter;
import interface_adapter.send_message.SendMessageState;
import interface_adapter.send_message.SendMessageViewModel;
import org.junit.jupiter.api.Test;
import use_case.send_message.SendMessageInputData;
import use_case.send_message.SendMessageInteractor;
import use_case.send_message.SendMessageOutputData;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class SendMessageTest {
    private final User testUser1 = new User("test1", "test1", "test1");
    private final User testUser2 = new User("test1", "test1", "test1");
    private final Map<String, User> testMap = new HashMap<>();

    private final LocalDateTime currentDateTime = LocalDateTime.now();
    private final ChatChannel channel = new ChatChannel(testMap, currentDateTime, "sendbird_group_channel_12586989_cbf2eb24180c0399084a22b8acb6519571db23f7");
    private final SendMessageDataAccessObject sendMessageDataAccessObject= new SendMessageDataAccessObject(
                    "https://api-1F4C3D4F-01DB-4A99-8704-BE4CB1FE3AE5.sendbird.com/v3",
                            "1F4C3D4F-01DB-4A99-8704-BE4CB1FE3AE5");
    private final SendMessageViewModel sendMessageViewModel = new SendMessageViewModel();
    private final SendMessageState testState = new SendMessageState(testUser1, channel);
    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
    private final SendMessageInputData testInputData = new SendMessageInputData("test message", "test1", channel);
    private final SendMessageInputData badTestInputData = new SendMessageInputData("test message", "non-existent-user", channel);
    private final SendMessageOutputData testOutputData = new SendMessageOutputData("Sent successfully.");
    //    @Test
//    void testSendMessage() {
//        testMap.put("test1", testUser1);
//        testMap.put("test2", testUser2);
//
//
//        SendMessageState testState = new SendMessageState(testUser1, channel);
//        SendMessageViewModel sendMessageViewModel = new SendMessageViewModel();
//        sendMessageViewModel.setState(testState);
//        assertEquals(testState, sendMessageViewModel.getState());
//    }
    @Test
    void testSendMessageInputData(){

        assertEquals("test message", testInputData.getMessage());
        assertEquals("test1", testInputData.getUserId());
        assertEquals(channel, testInputData.getChannel());
    }
    @Test
    void testSendMessageInteractorSuccess(){
        sendMessageViewModel.setState(testState);
        SendMessagePresenter sendMessagePresenter = new SendMessagePresenter(sendMessageViewModel, viewManagerModel);
        SendMessageInteractor testInteractor = new SendMessageInteractor(sendMessageDataAccessObject, sendMessagePresenter);
        testInteractor.execute(testInputData);

        assertTrue(sendMessageViewModel.getState().isMessageSentSuccessful());

    }
    @Test
    void testSendMessageInteractorFail(){
        SendMessagePresenter sendMessagePresenter = new SendMessagePresenter(sendMessageViewModel, viewManagerModel);
        SendMessageInteractor testInteractor = new SendMessageInteractor(sendMessageDataAccessObject, sendMessagePresenter);
        testInteractor.execute(badTestInputData);

        assertFalse(sendMessageViewModel.getState().isMessageSentSuccessful());
    }
    @Test
    void testSendMessageOutputData(){
        assertEquals("Sent successfully.", testOutputData.getMessage());
    }
}
