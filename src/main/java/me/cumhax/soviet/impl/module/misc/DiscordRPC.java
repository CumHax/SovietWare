package me.cumhax.soviet.impl.module.misc;

import me.cumhax.soviet.Discord;
import me.cumhax.soviet.api.module.Category;
import me.cumhax.soviet.api.module.Module;

public class DiscordRPC extends Module {

	public DiscordRPC()
	{
		super("RPC", "", Category.MISC);
	}
	
	public void onEnable() {
		Discord.startRPC();
	}
	
	public void onDisable() {
		Discord.stopRPC();
	}

}
