package cc.unilock.chatlogger147.proxy;

import cc.unilock.chatlogger147.ChatLogger147;
import cc.unilock.chatlogger147.EventListener;
import net.minecraftforge.common.MinecraftForge;

public class ServerProxy implements CommonProxy {
    @Override
    public void preinit() {
        MinecraftForge.EVENT_BUS.register(new EventListener());
        ChatLogger147.LOGGER.info("ChatLogger147 initialized!");
    }
}
