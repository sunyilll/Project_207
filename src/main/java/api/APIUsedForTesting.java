package main.java.api;

import kotlin.Pair;
import main.java.entity.ChatChannel;
import main.java.entity.User;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class APIUsedForTesting {
    private final String API_TOKEN;
    public APIUsedForTesting() {
        this.API_TOKEN = "0ecfef313ab2989479b70e30e3ee37a1d105b770";
    }

    public void setUser(String user_id, String nickname, String profile_url, boolean issue_access_token) {
        OkHttpClient client = new OkHttpClient().newBuilder().build();

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\"user_id\": \"test2\",\n\"nickname\": \"test2\",\n\"profile_url\": \"\"}");
        Request request = new Request.Builder()
                .url("https://api-1F4C3D4F-01DB-4A99-8704-BE4CB1FE3AE5.sendbird.com/v3/users")
                .method("POST", body)
                .addHeader("Api-Token", API_TOKEN)
                .addHeader("Content-Type", "application/json")
                .build();

        try {
            Response response = client.newCall(request).execute();
            System.out.println(response);
            JSONObject responseBody = new JSONObject(response.body().string());

            if (responseBody.has("is_created")) {
                System.out.println("successful");
                ;
            } else {
                throw new RuntimeException(responseBody.getString("message"));
            }


        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public void createChannel(String[] user_ids) {
        OkHttpClient client = new OkHttpClient().newBuilder().build();

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\"user_ids\": [\"test1\", \"test2\"], \"name\": \"test1 and test2\", \"is_distinct\": true}");
        Request request = new Request.Builder()
                .url("https://api-1F4C3D4F-01DB-4A99-8704-BE4CB1FE3AE5.sendbird.com/v3/group_channels")
                .method("POST", body)
                .addHeader("Api-Token", API_TOKEN)
                .addHeader("Content-Type", "application/json")
                .build();

        try {
            Response response = client.newCall(request).execute();
            System.out.println(response);
            JSONObject responseBody = new JSONObject(response.body().string());
            System.out.println(responseBody);

            if (responseBody.has("name")) {
                System.out.println("successful");
                ;
            } else {
                throw new RuntimeException(responseBody.getString("message"));
            }


        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean sendMessage(String user_id, String message, ChatChannel channel) {
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\"message_type\": \"MESG\",\n\"user_id\": \"" + user_id + "\",\n\"message\": \"" + message + "\"}");

//                "{\"message_type\": \"MESG\",\n\"user_id\": \"randomperson1\",\n\"message\": \"Hello, Bob!\"}");
//                        .url("https://api-d7382e93-9788-4c47-96bd-c1a030963830.sendbird.com/v3/group_channels/sendbird_group_channel_196644307_c84d1445faa3181e31b0bfe491c8f62413505bbe/messages")
        Request request = new Request.Builder()
                .url("https://api-1F4C3D4F-01DB-4A99-8704-BE4CB1FE3AE5.sendbird.com/v3/group_channels/" + channel.getChannelURL() + "/messages")
                .post(body)
                .addHeader("content-type", "application/json")
                .addHeader("Api-Token", API_TOKEN)
                .build();

        try {
            Response response = client.newCall(request).execute();
            System.out.println("Response body of sending message:");
            JSONObject responseBody = new JSONObject(response.body().string());
            System.out.println(responseBody);
            if (responseBody.has("error")) {
//                 && responseBody.get("error").equals(true)
                return false;
            } else {
                System.out.println("sent");
                return true;

            }

        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }


    }
    public ArrayList<Pair<String, String>> getMessageListFromNovTenth(ChatChannel channel){
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");

        LocalDateTime dateTime = LocalDateTime.of(2023, 11, 10, 0, 0, 0);

        // Get the Unix timestamp (seconds since Unix epoch)
        long unixTimestamp = dateTime.toEpochSecond(ZoneOffset.UTC);


        RequestBody body = RequestBody.create(mediaType, "{\"channel_type\": \"group_channels\",\n\"channel_url\": \"" + channel.getChannelURL() + "\",\n\"message_ts\": \"" + unixTimestamp + "\"}");

        Request request = new Request.Builder()
                .url("https://api-1F4C3D4F-01DB-4A99-8704-BE4CB1FE3AE5.sendbird.com/v3/group_channels/" + channel.getChannelURL() + "/messages?channel_type=group_channels&channel_url="
                        + channel.getChannelURL() + "&message_ts=" + unixTimestamp)
                .get()
                .addHeader("content-type", "application/json")
                .addHeader("Api-Token", API_TOKEN)
                .build();
        try {
            Response response = client.newCall(request).execute();
            System.out.println("Response body of sending message:");
            JSONObject responseBody = new JSONObject(response.body().string());
            System.out.println(responseBody);
            if (responseBody.has("error")) {
//                 && responseBody.get("error").equals(true)
                throw new JSONException("Error Object is returned");
            } else {
                JSONArray messageList =  responseBody.getJSONArray("messages");
                ArrayList<Pair<String, String>> messageListToReturn = new ArrayList<Pair<String, String>>();
                for (int i = 0; i < messageList.length(); i++) {
                    JSONObject obj = messageList.getJSONObject(i);
                    String message = obj.getString("message");
                    JSONObject userObject = obj.getJSONObject("user");
                    String userId = userObject.getString("user_id");
                    messageListToReturn.add(new Pair<>(message, userId));
                }
                return messageListToReturn;

            }

        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }



    public static void main(String[] args) {


        User testUser1 = new User("test1", "test1", "test1", "male");
        User testUser2 = new User("test1", "test1", "test1", "male");

        Map<String, User> testMap = new HashMap<>();
        testMap.put("test1", testUser1);
        testMap.put("test2", testUser2);
        LocalDateTime currentDateTime = LocalDateTime.now();


        ChatChannel channel = new ChatChannel(testMap, currentDateTime, "sendbird_group_channel_12586989_cbf2eb24180c0399084a22b8acb6519571db23f7");



        APIUsedForTesting a = new APIUsedForTesting();
        ArrayList<Pair<String, String>> x = a.getMessageListFromNovTenth(channel);

        System.out.println(x);

//        String user_id = "test1";
//        String message = "message1";
//        System.out.println("{\"message_type\": \"MESG\",\n\"user_id\": \"" + user_id + "\",\n\"message\": \"" + message + "\"}");


//        System.out.println("{\"user_ids\": [\"test1\", \"test2\"], name: \"test1 and test2\", is_distinct: true}");
    }
}

//    Response{protocol=h2, code=200, message=, url=https://api-1f4c3d4f-01db-4a99-8704-be4cb1fe3ae5.sendbird.com/v3/group_channels}
//        {"data":"","custom_type":"","channel":{"cover_url":"https://static.sendbird.com/sample/cover/cover_11.jpg","data":"","custom_type":"","name":"test1 and test2","max_length_message":5000,"created_at":1700270304,"member_count":2,"channel_url":"sendbird_group_channel_12586989_cbf2eb24180c0399084a22b8acb6519571db23f7"},"disappearing_message":{"is_triggered_by_message_read":false,"message_survival_seconds":-1},"created_at":1700270304,"is_super":false,"last_message":null,"is_discoverable":false,"joined_member_count":2,"freeze":false,"is_distinct":true,"operators":[],"members":[{"metadata":{},"require_auth_for_profile_image":false,"is_active":true,"role":"","profile_url":"","muted_end_at":-1,"is_muted":false,"muted_description":"","user_id":"test2","nickname":"test2","is_online":false,"state":"joined","last_seen_at":-1},{"metadata":{},"require_auth_for_profile_image":false,"is_active":true,"role":"","profile_url":"","muted_end_at":-1,"is_muted":false,"muted_description":"","user_id":"test1","nickname":"test1","is_online":false,"state":"joined","last_seen_at":-1}],"max_length_message":5000,"id":5658396,"member_count":2,"message_survival_seconds":-1,"cover_url":"https://static.sendbird.com/sample/cover/cover_11.jpg","is_ephemeral":false,"is_created":true,"unread_mention_count":0,"unread_message_count":0,"created_by":null,"channel_url":"sendbird_group_channel_12586989_cbf2eb24180c0399084a22b8acb6519571db23f7","is_broadcast":false,"name":"test1 and test2","is_public":false,"sms_fallback":{"exclude_user_ids":[],"wait_seconds":-1},"ignore_profanity_filter":false,"is_access_code_required":false}
//        successful

//    Response body of sending message:
//        {"silent":false,"data":"","message_events":{"update_last_message":true,"update_mention_count":true,"send_push_notification":"receivers","update_unread_count":true},"custom_type":"","created_at":1700271912438,"message_id":83152821,"is_removed":false,"mention_type":"users","type":"MESG","message":"message1","channel_url":"sendbird_group_channel_12586989_cbf2eb24180c0399084a22b8acb6519571db23f7","is_op_msg":false,"file":{},"message_retention_hour":-1,"updated_at":0,"translations":{},"mentioned_users":[],"channel_type":"group","user":{"metadata":{},"require_auth_for_profile_image":false,"role":"","is_active":true,"user_id":"test1","profile_url":"","nickname":"test1"},"message_survival_seconds":-1}
//        sent

//{"messages":[{"silent":false,"data":"","message_events":{"update_last_message":true,"update_mention_count":true,"send_push_notification":"receivers","update_unread_count":true},"custom_type":"","created_at":1700271912438,"message_id":83152821,"is_removed":false,"mention_type":"users","type":"MESG","message":"message1","channel_url":"sendbird_group_channel_12586989_cbf2eb24180c0399084a22b8acb6519571db23f7","is_op_msg":false,"file":{},"message_retention_hour":-1,"updated_at":0,"translations":{},"mentioned_users":[],"channel_type":"group","user":{"metadata":{},"require_auth_for_profile_image":false,"role":"","is_active":true,"user_id":"test1","profile_url":"","nickname":"test1"},"message_survival_seconds":-1},{"silent":false,"data":"","message_events":{"update_last_message":true,"update_mention_count":true,"send_push_notification":"receivers","update_unread_count":true},"custom_type":"","created_at":1700277100486,"message_id":83154860,"is_removed":false,"mention_type":"users","type":"MESG","message":"ha","channel_url":"sendbird_group_channel_12586989_cbf2eb24180c0399084a22b8acb6519571db23f7","is_op_msg":false,"file":{},"message_retention_hour":-1,"updated_at":0,"translations":{},"mentioned_users":[],"channel_type":"group","user":{"metadata":{},"require_auth_for_profile_image":false,"role":"","is_active":true,"user_id":"test1","profile_url":"","nickname":"test1"},"message_survival_seconds":-1}]}