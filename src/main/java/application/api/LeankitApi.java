package application.api;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONObject;

import java.io.IOException;

public class LeankitApi implements Api {

    private static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    private String authorizationCode;

    public LeankitApi(String authorizationCode) {
        this.authorizationCode = authorizationCode;
    }

    public JSONObject getRequest (String url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .header("Authorization", authorizationCode)
                .url(url)
                .build();
        Response response = client.newCall(request).execute();
        return new JSONObject(response.body().string());
    }

    public JSONObject postRequest(String url, String messageBody) throws IOException {
        Request request = new Request.Builder()
                .header("Authorization", authorizationCode)
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .url(url)
                .post(RequestBody.create(JSON, messageBody))
                .build();
        OkHttpClient client = new OkHttpClient();
        Response response = client.newCall(request).execute();
        JSONObject jsonObject = new JSONObject(response.body().string());
        return jsonObject;

    }
}
