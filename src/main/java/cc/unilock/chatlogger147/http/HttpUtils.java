package cc.unilock.chatlogger147.http;

import cc.unilock.chatlogger147.ChatLogger147;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtils {
    public static void postMessage(String username, String message) {
        HttpURLConnection http = null;
        try {
            String content = ("{"+
                "\"avatar_url\":\""+ChatLogger147.webhookAvatarUrl.replace("%username%", username)+"\","+
                "\"username\":\""+username+"\","+
                "\"content\":\""+message+"\""+
            "}");

            URL url = new URL("https://discord.com/api/webhooks/" + ChatLogger147.webhookId + "/" + ChatLogger147.webhookToken);
            http = (HttpURLConnection) url.openConnection();

            http.setRequestMethod("POST");
            http.setRequestProperty("Host", "discord.com");
            http.setRequestProperty("User-Agent", "curl/8.0.0");
            http.setRequestProperty("Accept", "*/*");
            http.setRequestProperty("Content-Type", "application/json");
            http.setRequestProperty("Content-Length", Integer.toString(content.getBytes().length));
            http.setUseCaches(false);
            http.setDoOutput(true);

            OutputStream os = http.getOutputStream();
            os.write(content.getBytes());
            os.flush();
            os.close();

            int code = http.getResponseCode();
            if (code != HttpURLConnection.HTTP_NO_CONTENT) {
                ChatLogger147.LOGGER.warning("Bad response code: " + code);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (http != null) {
                http.disconnect();
            }
        }
    }
}
