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


    public static void setUser(String user_id, String nickname, String profile_url, boolean issue_access_token){
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
                System.out.println("successful");;
            } else {
                throw new RuntimeException(responseBody.getString("message"));
            }


        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }


//    GET https://api-{application_id}.sendbird.com/v3/users/{user_id}
public static void getUser(String user_id){
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



    public static void main(String[] args) {
//        setUser("test", "test", "test", true);
        getUser("test");
    }
}
