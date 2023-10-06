package cc.unilock.chatlogger147.http;

import cc.unilock.chatlogger147.ChatLogger147;
import com.sun.net.httpserver.HttpServer;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public class HttpServerHandler {
    private static HttpServer httpServer;

    public static void start() {
        try {
            ChatLogger147.LOGGER.info("HttpServer starting...");
            httpServer = HttpServer.create(new InetSocketAddress(ChatLogger147.httpPort), 0);
            httpServer.createContext("/discord", new DiscordHandler());
            httpServer.setExecutor(Executors.newCachedThreadPool());
            httpServer.start();
            ChatLogger147.LOGGER.info("HttpServer started! (port "+ChatLogger147.httpPort+")");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void stop() {
        ChatLogger147.LOGGER.info("HttpServer stopping...");
        httpServer.stop(0);
        ChatLogger147.LOGGER.info("HttpServer stopped!");
    }
}
