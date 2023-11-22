package main.java.api;

import kotlin.Pair;
import main.java.entity.ChatChannel;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class SendBirdAPI {
    // TODO: Change all the concrete API info to be passed in by parameter, initialized by constructor

    private final String API_URL; // = "https://api-1F4C3D4F-01DB-4A99-8704-BE4CB1FE3AE5.sendbird.com/v3";


    private final String applicationId; // = "1F4C3D4F-01DB-4A99-8704-BE4CB1FE3AE5";
    // you have to create your own API_TOKEN
    private final String API_TOKEN; // = System.getenv("API_TOKEN");

//    public static String getApiToken() {
//        return API_TOKEN;
//    }

    public SendBirdAPI(String apiURL, String applicationId, String apiToken) {
        this.API_URL = apiURL;
        this.applicationId = applicationId;
        this.API_TOKEN = apiToken;

    }

    public void setUser(String user_id, String nickname, String profile_url, boolean issue_access_token) {
        OkHttpClient client = new OkHttpClient().newBuilder().build();

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\"user_id\": \"test\",\n\"nickname\": \"test\",\n\"profile_url\": \"\"}");
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

            if (responseBody.getBoolean("is_created")) {
                System.out.println("successful");
                ;
            } else {
                throw new RuntimeException(responseBody.getString("message"));
            }


        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }


    //    GET https://api-{application_id}.sendbird.com/v3/users/{user_id}
    public void getUser(String user_id) {
        OkHttpClient client = new OkHttpClient().newBuilder().build();

        MediaType mediaType = MediaType.parse("application/json");
//    RequestBody body = RequestBody.create(mediaType, "{\"user_id\": \"test\"}");
        JSONObject requestBody = new JSONObject();
        RequestBody body = RequestBody.create(mediaType, requestBody.toString());
        Request request = new Request.Builder()
                .url("https://api-1F4C3D4F-01DB-4A99-8704-BE4CB1FE3AE5.sendbird.com/v3/users/test")
                .get()
                .addHeader("Api-Token", API_TOKEN)
                .addHeader("Content-Type", "application/json")
                .build();

        try {
            Response response = client.newCall(request).execute();
            System.out.println(response);
            JSONObject responseBody = new JSONObject(response.body().string());

            if (Objects.equals(responseBody.getString("user_id"), user_id)) {
                System.out.println("successful");
                System.out.println(responseBody);
            } else {
                throw new RuntimeException(responseBody.getString("message"));
            }


        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public void getMessage() {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api-d7382e93-9788-4c47-96bd-c1a030963830.sendbird.com/v3/group_channels/sendbird_group_channel_196644307_c84d1445faa3181e31b0bfe491c8f62413505bbe/messages/7455047004")
                .get()
                .addHeader("Api-Token", API_TOKEN)
                .build();

        try {
            Response response = client.newCall(request).execute();
            System.out.println("Response body of getting a message");
            JSONObject responseBody = new JSONObject(response.body().string());
            System.out.println(responseBody);

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


}