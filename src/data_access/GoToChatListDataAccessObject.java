//package data_access;
//
//import api.SendBirdAPI;
//import entity.ChatChannel;
//import entity.User;
//import use_case.go_to_chat_list.GoToChatListDataAccessInterface;
//
//import java.io.IOException;
//import java.util.ArrayList;
//
//public class GoToChatListDataAccessObject implements GoToChatListDataAccessInterface {
//    public GoToChatListDataAccessObject(){
//    }
//
//    @Override
//    public ArrayList<ChatChannel> getAllChatChannels() throws RuntimeException {
//
//        SendBirdAPI sendBirdAPIObject = new SendBirdAPI("https://api-1F4C3D4F-01DB-4A99-8704-BE4CB1FE3AE5.sendbird.com/v3",
//                "1F4C3D4F-01DB-4A99-8704-BE4CB1FE3AE5",
//                "0ecfef313ab2989479b70e30e3ee37a1d105b770");
//        try {
//            ArrayList<ChatChannel> channels = sendBirdAPIObject.getAllChatChannels(user.getUserID());
//            if (channels == null || channels.isEmpty()) {
//                throw new RuntimeException("Failed to get all chat channels");
//            }
//            return channels;
//        } catch (RuntimeException e) {
//            throw new RuntimeException(e);
//        }
//    }
//}
