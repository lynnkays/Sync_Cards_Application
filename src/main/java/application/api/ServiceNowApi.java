package application.api;

import okhttp3.*;
import org.json.JSONObject;

import java.io.IOException;

public class ServiceNowApi implements Api {

    private static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    private final String authorizationCode;

    public ServiceNowApi (String authorizationCode){
        this.authorizationCode = authorizationCode;
    }

    public JSONObject getRequest(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .header("Authorization", authorizationCode)
                .url(url)
                .build();
        Response response = client.newCall(request).execute();
        JSONObject data = new JSONObject(response.body().string());
        data.put("ReplyCode", response.code());
        return data;

    }

    public JSONObject putRequest(String url, String messageBody) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .header("Authorization", authorizationCode)
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("X-HTTP-Method-Override", "PUT")
                .url(url)
                .post(RequestBody.create(JSON, messageBody))
                .build();

        Response response = client.newCall(request).execute();
        JSONObject data = new JSONObject(response.body().string());
        data.put("ReplyCode", response.code());
        return data;
    }

    public JSONObject postRequest (String url, String messageBody) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .header("Authorization", authorizationCode)
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .url(url)
                .post(RequestBody.create(JSON, messageBody))
                .build();
        Response response = client.newCall(request).execute();
        JSONObject data = new JSONObject(response.body().string());
        data.put("ReplyCode", response.code());
        return data;
    }
}
