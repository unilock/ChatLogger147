package cc.unilock.chatlogger147.proxy;

import cc.unilock.chatlogger147.ChatLogger147;

public class ClientProxy implements CommonProxy {
	@Override
	public void preinit() {
		ChatLogger147.LOGGER.warning("ChatLogger147 will not run on the client.");
	}
}
