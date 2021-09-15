package me.cumhax.soviet.impl.module.misc;

import me.cumhax.soviet.api.module.Category;
import me.cumhax.soviet.api.module.Module;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class NoBedtrapMsg extends Module
{

	public NoBedtrapMsg()
	{
		super("NoBedtrapMsg", "", Category.MISC);
	}

	@SubscribeEvent
	public void onChatReceived(ClientChatReceivedEvent event)
	{
		if (mc.player == null || mc.world == null) return;

		if (event.getMessage().getUnformattedComponentText().contains("thought they were jj20051") || event.getMessage().getUnformattedComponentText().contains("burned to death") || event.getMessage().getUnformattedComponentText().contains("went up in flames") || event.getMessage().getUnformattedComponentText().contains("swim in lava"))
		{
			event.setCanceled(true);
		}
	}
}
