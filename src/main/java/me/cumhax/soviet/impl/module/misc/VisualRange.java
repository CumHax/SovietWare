package me.cumhax.soviet.impl.module.misc;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import com.mojang.realmsclient.gui.ChatFormatting;
import me.cumhax.soviet.api.module.Category;
import me.cumhax.soviet.api.module.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class VisualRange extends Module {
	
    public VisualRange() 
	{	
        super("VisualRange", "", Category.MISC);
    }
	
	List<Entity> knownPlayers = new ArrayList<>();;
    List<Entity> players;
	
	@SubscribeEvent
	public void onTick(TickEvent.ClientTickEvent event) {
		if(mc.player == null) return;
        players = mc.world.loadedEntityList.stream().filter(e-> e instanceof EntityPlayer).collect(Collectors.toList());
        try {
            for (Entity e : players) {
                if (e instanceof EntityPlayer && !e.getName().equalsIgnoreCase(mc.player.getName())) {
                    if (!knownPlayers.contains(e)) {
                        knownPlayers.add(e);
                        Minecraft.getMinecraft().player.sendMessage(new TextComponentString(ChatFormatting.DARK_GRAY + "[SovietWare] " + ChatFormatting.GOLD +  e.getName() + " that nigga entered to ur visual range"));
                    }
                }
            }
        } catch(Exception e){} 
        try {
            for (Entity e : knownPlayers) {
                if (e instanceof EntityPlayer && !e.getName().equalsIgnoreCase(mc.player.getName())) {
                    if (!players.contains(e)) {
                        knownPlayers.remove(e);;
                    }
                }
            }
        } catch(Exception e){}
	}

}
