package me.cumhax.soviet.impl.module.render;

import me.cumhax.soviet.api.module.Category;
import me.cumhax.soviet.api.module.Module;
import me.cumhax.soviet.api.setting.Setting;
import me.cumhax.soviet.api.util.RenderUtil;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class BlockHighlight extends Module
{
	private final Setting color = new Setting("Color", this, Arrays.asList("Static", "Rainbow"));
	private final Setting red = new Setting("Red", this, 0, 255, 255);
	private final Setting green = new Setting("Green", this, 0, 20, 255);
	private final Setting blue = new Setting("Blue", this, 0, 20, 255);
	private final Setting alpha = new Setting("Alpha", this, 0, 100, 255);
	private final Setting rainbowSpeed = new Setting("RainbowSpeed", this, 0, 5, 10);

	private float hue = 0f;

    public BlockHighlight() 
	{	
        super("BlockHighlight", "", Category.RENDER);
    }

	@SubscribeEvent
	public void renderWorld(RenderWorldLastEvent event)
	{
		hue += rainbowSpeed.getIntValue() / 1000f;
		int rgb = Color.HSBtoRGB(hue, 1.0F, 1.0F);

		int r = rgb >> 16 & 255;
		int g = rgb >> 8 & 255;
		int b = rgb & 255;

		RayTraceResult rayTraceResult = mc.objectMouseOver;

		if (rayTraceResult != null && rayTraceResult.typeOfHit == RayTraceResult.Type.BLOCK)
		{
			BlockPos blockPos = rayTraceResult.getBlockPos();

			if (mc.world.getBlockState(blockPos).getMaterial() != Material.AIR)
			{
				if (color.getEnumValue().equals("Rainbow"))
				{
					RenderUtil.drawBoxFromBlockpos(blockPos, r / 255f, g / 255f, b / 255f, alpha.getIntValue() / 255f);
				}
				else
				{
					RenderUtil.drawBoxFromBlockpos(blockPos, red.getIntValue() / 255f, green.getIntValue() / 255f, blue.getIntValue() / 255f, alpha.getIntValue() / 255f);
				}
			}
		}
	}
}
