package me.cumhax.soviet.impl.module.render;

import me.cumhax.soviet.api.module.Category;
import me.cumhax.soviet.api.module.Module;
import me.cumhax.soviet.api.setting.Setting;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class ItemViewmodle extends Module {

	private final Setting fov = new Setting("Fov", this, 120, 90, 160);
	private final Setting armPitch = new Setting("Arm Pitch", this, 90, 0, 360);
	private final Setting armYaw = new Setting("Arm Yaw", this, 220, 0, 360);
	
    public ItemViewmodle() 
	{	
        super("ViewModel", "", Category.RENDER);
    }

	@SubscribeEvent
    public void onTick(final TickEvent.ClientTickEvent event) {
		if(mc.world == null) {
			return;
		}
		mc.player.renderArmPitch = (float) armPitch.getIntegerValue();
        //mc.player.renderArmYaw = (float) armYaw.getIntegerValue();
	}
	
	@SubscribeEvent
    public void FOVEvent(EntityViewRenderEvent.FOVModifier event) {
        event.setFOV((float) fov.getIntegerValue());
    }
}