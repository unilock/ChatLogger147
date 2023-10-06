package cc.unilock.chatlogger147.proxy;

public class ClientProxy implements CommonProxy {
	@Override
	public void preInit() {
		throw new RuntimeException("ChatLogger147 is not supported on the client!");
	}

	@Override
	public void serverStarting() {
		throw new RuntimeException("ChatLogger147 is not supported on the client!");
	}

	@Override
	public void serverStopping() {
		throw new RuntimeException("ChatLogger147 is not supported on the client!");
	}
}
