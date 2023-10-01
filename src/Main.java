import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        new Main().newUser();
        new Main().getMessage();
        new Main().sendMessage();
    }

    public void newUser() {
        // create a new user
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\"user_id\": \"test\",\n\"nickname\": \"test\",\n\"profile_url\": \"\"}");
        Request request = new Request.Builder()
                .url("https://api-D7382E93-9788-4C47-96BD-C1A030963830.sendbird.com/v3/users")
                .method("POST", body)
                .addHeader("Api-Token", "e1f5fd4563f9eb2a9435349febb34c4fb2428c4b")
                .addHeader("Content-Type", "application/json")
                .build();

        try {
            Response response = client.newCall(request).execute();
            System.out.println(response);

        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public void getMessage() {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api-d7382e93-9788-4c47-96bd-c1a030963830.sendbird.com/v3/group_channels/sendbird_group_channel_196644307_c84d1445faa3181e31b0bfe491c8f62413505bbe/messages/7455047004")
                .get()
                .addHeader("Api-Token", "e1f5fd4563f9eb2a9435349febb34c4fb2428c4b")
                .build();

        try {
            Response response = client.newCall(request).execute();
            System.out.println(response);

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
            System.out.println(response);

        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
