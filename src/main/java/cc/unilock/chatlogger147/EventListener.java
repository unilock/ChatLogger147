package cc.unilock.chatlogger147;

import cc.unilock.chatlogger147.stolen.Charsets;
import cc.unilock.chatlogger147.stolen.FileUtils;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.ServerChatEvent;

import java.io.IOException;
import java.text.SimpleDateFormat;

public class EventListener {
    @ForgeSubscribe
    public void onServerChat(ServerChatEvent event) {
        try {
            FileUtils.writeStringToFile(ChatLogger147.chatlog, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis()) + " : " + event.line + "\n", Charsets.UTF_8, true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
