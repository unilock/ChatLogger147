package cc.unilock.chatlogger147;

import cc.unilock.chatlogger147.proxy.CommonProxy;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import net.minecraftforge.common.Configuration;

import java.io.File;
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
	public static File chatlog;

	public ChatLogger147() {
		LOGGER.setParent(FMLLog.getLogger());
	}

	@Mod.PreInit
	public void preinit(FMLPreInitializationEvent e) {
		config = new Configuration(e.getSuggestedConfigurationFile());
		try {
			config.load();
			chatlog = new File(config.get("main", "chatlog", "chatlog.txt").value);
		} finally {
			config.save();
		}

		proxy.preinit();
	}
}
