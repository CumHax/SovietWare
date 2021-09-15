package me.cumhax.soviet.impl.module.misc;

import me.cumhax.soviet.api.module.Category;
import me.cumhax.soviet.api.module.Module;
import me.cumhax.soviet.api.setting.Setting;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class YawLock extends Module {

	private final Setting slice = new Setting("Slice", this, 8, 0, 8);
	
    public YawLock() 
	{	
        super("YawLock", "", Category.MISC);
    }
	
	@SubscribeEvent
	public void onTick(TickEvent.ClientTickEvent event) {
		if(mc.world == null) return;
		if(slice.getIntegerValue() == 0) return;
		int angle = 360 / slice.getIntegerValue();
        float yaw = mc.player.rotationYaw;
        mc.player.rotationYaw = yaw = (float)(Math.round(yaw / (float)angle) * angle);
        if (mc.player.isRiding()) {
            mc.player.getRidingEntity().rotationYaw = yaw;
        }
	}
}
