package me.cumhax.soviet.impl.module.render;

import me.cumhax.soviet.api.module.Category;
import me.cumhax.soviet.api.module.Module;
import me.cumhax.soviet.api.setting.Setting;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Fullbright extends Module
{
	private final Setting mode = new Setting("Mode", this,  Arrays.asList("Gamma", "Potion"));

    public Fullbright() 
	{	
        super("Fullbright", "", Category.RENDER);
    }

	@SubscribeEvent
	public void onTick(TickEvent.ClientTickEvent event)
	{
		if (mc.player == null || mc.world == null) return;

		if (mode.getEnumValue().equals("Gamma"))
		{
			mc.gameSettings.gammaSetting = 100f;
		}
		else
		{
			mc.gameSettings.gammaSetting = 1f;
			mc.player.addPotionEffect(new PotionEffect(Objects.requireNonNull(Potion.getPotionById(16)), 1, 0));
		}
	}

	@Override
	public void onDisable()
	{
		mc.gameSettings.gammaSetting = 1f;
	}
}
