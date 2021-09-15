package me.cumhax.soviet;

import me.cumhax.soviet.api.EventHandler;
import me.cumhax.soviet.api.command.CommandManager;
import me.cumhax.soviet.api.gui.clickgui.ClickGUI;
import me.cumhax.soviet.api.manager.FriendManager;
import me.cumhax.soviet.api.module.ModuleManager;
import me.cumhax.soviet.api.setting.SettingManager;
import me.cumhax.soviet.api.gui.clickgui.util.font.CustomFontRenderer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import org.lwjgl.opengl.Display;
import me.cumhax.soviet.api.manager.ConfigManager;

import java.awt.*;
import java.io.IOException;

@Mod(modid = "sovietware", name = "SovietWare", version = "")
public class Client {
	
    public static final String MODID = "sovietware";
    public static final String MODNAME = "SovietWare";
    public static final String MODVER = "";
	public static final String DISPLAY ="Balls Nuts";
	public static ModuleManager moduleManager;
	public static SettingManager settingManager;
	public static CustomFontRenderer customFontRenderer;
	public static ClickGUI clickGUI;
	public static CommandManager commandManager;
	public static FriendManager friendManager;

	public static String getName () {
		return null;
	}

	@Mod.EventHandler
	public void initialize(FMLInitializationEvent event) throws IOException {
		commandManager = new CommandManager();
		settingManager = new SettingManager();
		moduleManager = new ModuleManager();
		friendManager = new FriendManager();
		customFontRenderer = new CustomFontRenderer(new Font("Arial", Font.PLAIN, 19), true, false);
		clickGUI = new ClickGUI();

		ConfigManager.loadConfig();

		Runtime.getRuntime().addShutdownHook(new ConfigManager());
		MinecraftForge.EVENT_BUS.register(new EventHandler());
	}
	public static ModuleManager getModuleManager()
	{
		return moduleManager;
	}
	public static FriendManager getFriendManager()
	{
		return friendManager;
	}
}


