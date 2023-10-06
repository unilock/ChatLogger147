package cc.unilock.chatlogger147.http;

import cc.unilock.chatlogger147.ChatLogger147;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import net.minecraft.server.MinecraftServer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.regex.Pattern;

public class DiscordHandler implements HttpHandler {
    private static final char RECORD_SEPARATOR = '␞';
    private static final Pattern SPLITTER = Pattern.compile(Pattern.quote(String.valueOf(RECORD_SEPARATOR)));

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if (exchange.getRequestMethod().equals("POST")) {
            handlePost(exchange);
        } else {
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
        }

        exchange.getResponseBody().close();
    }

    private static void handlePost(HttpExchange exchange) throws IOException {
        String body = inputStreamToString(exchange.getRequestBody());
        // illegal chars
        body = body.replace('§', 'S');
        String[] parts = SPLITTER.split(body);

        if (parts.length != 2) {
            ChatLogger147.LOGGER.warning("Wrong number of parts in request body: " + body);
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
            return;
        }

        String username = parts[0];
        String message = parts[1];

        // TODO: make format configurable
        MinecraftServer.getServer().getConfigurationManager().sendChatMsg("[Discord] <"+username+"> "+message);

        exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
    }

    private static String inputStreamToString(InputStream is) throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        for (int length; (length = is.read(buffer)) != -1;) {
            os.write(buffer, 0, length);
        }
        return os.toString("UTF-8");
    }
}
