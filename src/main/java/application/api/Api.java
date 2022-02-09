package application.api;

import org.json.JSONObject;
import java.io.IOException;

interface Api {

    JSONObject getRequest (String url) throws IOException;

    JSONObject postRequest (String url, String messageBody) throws IOException;
}
