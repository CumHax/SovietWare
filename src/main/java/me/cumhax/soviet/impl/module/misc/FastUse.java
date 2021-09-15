package me.cumhax.soviet.impl.module.misc;

import me.cumhax.soviet.mixin.mixins.IMinecraft;
import me.cumhax.soviet.api.module.Category;
import me.cumhax.soviet.api.module.Module;
import me.cumhax.soviet.api.setting.Setting;
import net.minecraft.init.Items;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;


public class FastUse extends Module {

	private final Setting all = new Setting("All", this, false);
	private final Setting bow = new Setting("Bow", this, true);
	private final Setting exp = new Setting("Exp", this, true);
	private final Setting endCrystals = new Setting("End Crystals", this, true);
	private final Setting fireworks = new Setting("Fireworks", this, true);
	
    public FastUse() 
	{	
        super("FastUse", "", Category.MISC);
    }

	@SubscribeEvent
	public void onTick(final TickEvent.ClientTickEvent event) {
		if(mc.world == null) return;
		if(all.getBooleanValue()) {
			((IMinecraft) mc).setRightClickDelayTimer(0);
		}
		if(exp.getBooleanValue() && mc.player.getHeldItemMainhand().getItem() == Items.EXPERIENCE_BOTTLE || exp.getBooleanValue() && mc.player.getHeldItemOffhand().getItem() == Items.EXPERIENCE_BOTTLE) {
			((IMinecraft) mc).setRightClickDelayTimer(0);
		}
		if(endCrystals.getBooleanValue() && mc.player.getHeldItemMainhand().getItem() == Items.END_CRYSTAL || endCrystals.getBooleanValue() && mc.player.getHeldItemOffhand().getItem() == Items.END_CRYSTAL) {
			((IMinecraft) mc).setRightClickDelayTimer(0);
		}
		if(fireworks.getBooleanValue() && mc.player.getHeldItemMainhand().getItem() == Items.FIREWORKS || fireworks.getBooleanValue() && mc.player.getHeldItemOffhand().getItem() == Items.FIREWORKS) {
			((IMinecraft) mc).setRightClickDelayTimer(0);
		}
	}
}
