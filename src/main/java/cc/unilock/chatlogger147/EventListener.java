package cc.unilock.chatlogger147;

import cc.unilock.chatlogger147.http.HttpUtils;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.ServerChatEvent;

public class EventListener {
    @ForgeSubscribe
    public void onServerChat(ServerChatEvent event) {
        try {
            HttpUtils.postMessage(event.username, event.message);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
