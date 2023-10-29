package api;

import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

public class SendBirdAPI {
    private static final String API_URL = "https://api-1F4C3D4F-01DB-4A99-8704-BE4CB1FE3AE5.sendbird.com/v3";


    private static final String applicationId = "1F4C3D4F-01DB-4A99-8704-BE4CB1FE3AE5";
    // you have to create your own API_TOKEN
    private static final String API_TOKEN = System.getenv("API_TOKEN");

    public static String getApiToken() {
        return API_TOKEN;
    }


    public static void setUser(String user_id, String nickname, String profile_url, boolean issue_access_token) {
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
    public static void getUser(String user_id) {
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

    public void sendMessage() {
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\"message_type\": \"MESG\",\n\"user_id\": \"randomperson1\",\n\"message\": \"Hello, Bob!\"}");
        Request request = new Request.Builder()
                .url("https://api-d7382e93-9788-4c47-96bd-c1a030963830.sendbird.com/v3/group_channels/sendbird_group_channel_196644307_c84d1445faa3181e31b0bfe491c8f62413505bbe/messages")
                .post(body)
                .addHeader("content-type", "application/json")
                .addHeader("Api-Token", "e1f5fd4563f9eb2a9435349febb34c4fb2428c4b")
                .build();

        try {
            Response response = client.newCall(request).execute();
            System.out.println("Response body of sending message:");
            JSONObject responseBody = new JSONObject(response.body().string());
            System.out.println(responseBody);

        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }


    }
//qwq
    public static void main(String[] args) {
        getUser("test");
    }
}