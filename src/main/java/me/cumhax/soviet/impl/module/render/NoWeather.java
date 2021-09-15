package me.cumhax.soviet.impl.module.render;

import me.cumhax.soviet.api.module.Category;
import me.cumhax.soviet.api.module.Module;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class NoWeather extends Module
{
    public NoWeather() 
	{	
        super("NoWeather", "", Category.RENDER);
    }

	@SubscribeEvent
	public void onTick(TickEvent.ClientTickEvent event)
	{
		if (mc.player == null || mc.world == null) return;

		if (mc.world.isRaining()) mc.world.setRainStrength(0f);
	}
}
