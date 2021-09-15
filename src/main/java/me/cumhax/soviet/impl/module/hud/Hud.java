package me.cumhax.soviet.impl.module.hud;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import com.mojang.realmsclient.gui.ChatFormatting;

import me.cumhax.soviet.Client;
import me.cumhax.soviet.api.module.Category;
import me.cumhax.soviet.api.module.Module;
import me.cumhax.soviet.api.setting.Setting;
import me.cumhax.soviet.api.util.MathUtil;
import me.cumhax.soviet.api.gui.clickgui.util.RenderUtil;
import me.cumhax.soviet.api.gui.clickgui.util.font.CustomFontRenderer;
import me.cumhax.soviet.api.gui.clickgui.util.font.FontUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;    

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Hud extends Module {
	
	Setting ArrayList = new Setting("ArrayList", this, true);
	Setting Watermark = new Setting("Watermark", this, true);
	Setting xpCount = new Setting("Inventory", this, false);
	private final Setting invX = new Setting("InvX", this, 394, 1, 780);
	private final Setting invY = new Setting("InvY", this, 434, 1, 480);
	
    public Hud() 
	{	
        super("Hud", "", Category.HUD);
    }
	
	@SubscribeEvent
    public void onRender(RenderGameOverlayEvent.Post event) {
		if(mc.world == null) return;
		ScaledResolution resolution = new ScaledResolution(mc);
		if (event.getType() == RenderGameOverlayEvent.ElementType.HOTBAR) {
			if((ArrayList.getBooleanValue())) {
				final ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
	            int y = 2;
	            final ArrayList<String> list = new ArrayList<String>();
	            for (final Module mod : Client.moduleManager.getModules()) {
	                if (mod.isEnabled()) {
	                    list.add(mod.getName());
	                }
	            }
	            list.sort((s1, s2) -> FontUtil.getStringWidth(s1) - Minecraft.getMinecraft().fontRenderer.getStringWidth(s2));
	            Collections.reverse(list);
	            for (final String name : list) {
	            	CustomFontRenderer fr = Client.customFontRenderer;
	            	fr.drawStringWithShadow(name, (float)(sr.getScaledWidth() - fr.getStringWidth(name) - 3), (float)y, new Color(225,225,225, 255).getRGB());
	            	y += 11;
	            }
			}
			if((Watermark.getBooleanValue())) {
				int X = 5;
				int Y = 7;
				int W = 67;
				int H = 16;
				
				String ping = "NONE";
				for (EntityPlayer player : mc.world.playerEntities) {
					if(player.getName() == mc.player.getName()) {
						ping = getPing(player) + "MS";
					}
				}
				String server = Minecraft.getMinecraft().isSingleplayer() ? "singleplayer".toUpperCase() : mc.getCurrentServerData().serverIP.toUpperCase();
				SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");  
				Date date = new Date();
				String waterma = "SOVIETWARE.CC | " + formatter.format(date) + " | " + server + " | " + ping;
				Gui.drawRect(X, Y - 2, X + Client.customFontRenderer.getStringWidth(waterma) + 7, Y + H - 3, new Color(24,14,60, 185).getRGB());
				RenderUtil.drawGradientSideways(X, Y - 2, X + Client.customFontRenderer.getStringWidth(waterma) + 7, Y, new Color(64,41,213,255).getRGB(), new Color(124,9,77, 255).getRGB());
				FontUtil.drawStringWithShadow(waterma, X + 2, Y + 4, new Color(255,255,255,255).getRGB());
			}
			if((xpCount.getBooleanValue())) {
				
				GlStateManager.pushMatrix();
				RenderHelper.enableGUIStandardItemLighting();

				Gui.drawRect(invX.getIntegerValue() - 1, invY.getIntegerValue(), invX.getIntegerValue(), invY.getIntegerValue()+ 57, new Color(64,41,213, 255).getRGB());
				Gui.drawRect(invX.getIntegerValue() + 177, invY.getIntegerValue(), invX.getIntegerValue() + 178, invY.getIntegerValue()+ 57, new Color(64,41,213, 255).getRGB());
				Gui.drawRect(invX.getIntegerValue() + 177, invY.getIntegerValue() - 1, invX.getIntegerValue() + 178, invY.getIntegerValue() + 58, new Color(124,9,77, 255).getRGB());
				RenderUtil.drawGradientSideways(invX.getIntegerValue() - 1, invY.getIntegerValue() - 1, invX.getIntegerValue() + 178, invY.getIntegerValue(), new Color(64,41,213,255).getRGB(), new Color(124,9,77, 255).getRGB());
				RenderUtil.drawGradientSideways(invX.getIntegerValue() - 1, invY.getIntegerValue() - 1, invX.getIntegerValue() + 178, invY.getIntegerValue(), new Color(64,41,213,255).getRGB(), new Color(124,9,77, 255).getRGB());
				RenderUtil.drawGradientSideways(invX.getIntegerValue() - 1, invY.getIntegerValue() + 57, invX.getIntegerValue() + 178, invY.getIntegerValue() + 58, new Color(64,41,213,255).getRGB(), new Color(124,9,77, 255).getRGB());
				RenderUtil.drawGradientSideways(invX.getIntegerValue() - 1, invY.getIntegerValue() - 1, invX.getIntegerValue() + 178, invY.getIntegerValue() + 58, new Color(64,41,213,85).getRGB(), new Color(124,9,77,85).getRGB());
				
				for (int i = 0; i < 27; i++) {
					ItemStack item_stack = mc.player.inventory.mainInventory.get(i + 9);

					int item_position_x = (int) invX.getIntegerValue() + (i % 9) * 20;
					int item_position_y = (int) invY.getIntegerValue() + (i / 9) * 20;

					mc.getRenderItem().renderItemAndEffectIntoGUI(item_stack, item_position_x, item_position_y);
					mc.getRenderItem().renderItemOverlayIntoGUI(mc.fontRenderer, item_stack, item_position_x, item_position_y, null);
				}

				mc.getRenderItem().zLevel = - 5.0f;

				RenderHelper.disableStandardItemLighting();			
				
				GlStateManager.popMatrix();

			}
		}
	}
	
	public int getPing(final EntityPlayer player) {
        int ping = 0;
        try {
            ping = (int) MathUtil.clamp((float) Objects.requireNonNull(mc.getConnection()).getPlayerInfo(player.getUniqueID()).getResponseTime(), 1, 300.0f);
        }
        catch (NullPointerException ignored) {
        }
        return ping;
	
}
}