package cc.unilock.chatlogger147;

import cc.unilock.chatlogger147.proxy.CommonProxy;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.event.FMLServerStoppingEvent;
import cpw.mods.fml.common.network.NetworkMod;
import net.minecraftforge.common.Configuration;

import java.util.logging.Logger;

@Mod(
		modid = ChatLogger147.MODID,
		useMetadata = true
)
@NetworkMod(
		clientSideRequired = false,
		serverSideRequired = true
)
public class ChatLogger147 {
	public static final String MODID = "chatlogger147";
	public static final String NAME = "ChatLogger147";

	public static final Logger LOGGER = Logger.getLogger(NAME);

	@SidedProxy(clientSide = "cc.unilock.chatlogger147.proxy.ClientProxy", serverSide = "cc.unilock.chatlogger147.proxy.ServerProxy")
	public static CommonProxy proxy;

	public static Configuration config;
	public static String webhookAvatarUrl;
	public static String webhookId;
	public static String webhookToken;
	public static int httpPort;


	public ChatLogger147() {
		LOGGER.setParent(FMLLog.getLogger());
	}

	@Mod.PreInit
	public void preInit(FMLPreInitializationEvent event) {
		config = new Configuration(event.getSuggestedConfigurationFile());
		try {
			config.load();
			webhookAvatarUrl = config.get("webhook", "avatarURL", "https://minotar.net/helm/%username%/128.png").value;
			webhookId = config.get("webhook", "id", "").value;
			webhookToken = config.get("webhook", "token", "").value;
			httpPort = config.get("httpserver", "port", 14700).getInt();
		} finally {
			config.save();
		}

		proxy.preInit();
	}

	@Mod.ServerStarting
	public void serverStarting(FMLServerStartingEvent event) {
		proxy.serverStarting();
	}

	@Mod.ServerStopping
	public void serverStopping(FMLServerStoppingEvent event) {
		proxy.serverStopping();
	}
}
