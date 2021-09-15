package me.cumhax.soviet.impl.module.misc;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.mojang.authlib.GameProfile;

import me.cumhax.soviet.api.module.Category;
import me.cumhax.soviet.api.module.Module;
import net.minecraft.client.entity.EntityOtherPlayerMP;

public class FakePlayer extends Module {
	
	private final List<Integer> fakePlayerIdList = new ArrayList<>();
	
    public FakePlayer() 
	{	
        super("FakePlayer", "", Category.MISC);
    }
     
	public void onEnable() {
		
		if(mc.world == null || mc.player == null) {
			disable();
		}
		
		GameProfile profile = new GameProfile(UUID.fromString("b4e468dc-f67f-494c-8214-23e248cf1706"), "Nigga Balls Seggs");
		EntityOtherPlayerMP fakePlayer = new EntityOtherPlayerMP(mc.world, profile);

		fakePlayer.copyLocationAndAnglesFrom(mc.player);
		fakePlayer.setHealth(mc.player.getHealth() + mc.player.getAbsorptionAmount());

		mc.world.addEntityToWorld(-69, fakePlayer);

		fakePlayerIdList.add(-69);
	}
	
	public void onDisable() {
		for (int id : fakePlayerIdList)
		{
			mc.world.removeEntityFromWorld(id);
		}
	}
}
