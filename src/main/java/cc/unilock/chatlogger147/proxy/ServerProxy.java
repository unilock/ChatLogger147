package cc.unilock.chatlogger147.proxy;

import cc.unilock.chatlogger147.ChatLogger147;
import cc.unilock.chatlogger147.EventListener;
import cc.unilock.chatlogger147.http.HttpServerHandler;
import net.minecraftforge.common.MinecraftForge;

public class ServerProxy implements CommonProxy {
    @Override
    public void preInit() {
        MinecraftForge.EVENT_BUS.register(new EventListener());
        ChatLogger147.LOGGER.info("ChatLogger147 initialized!");
    }

    @Override
    public void serverStarting() {
        HttpServerHandler.start();
    }

    @Override
    public void serverStopping() {
        HttpServerHandler.stop();
    }
}
