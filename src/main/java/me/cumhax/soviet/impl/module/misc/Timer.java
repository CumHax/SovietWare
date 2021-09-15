package me.cumhax.soviet.impl.module.misc;

import me.cumhax.soviet.api.module.Category;
import me.cumhax.soviet.api.module.Module;
import me.cumhax.soviet.api.setting.Setting;
import me.cumhax.soviet.api.util.LoggerUtil;
import me.cumhax.soviet.mixin.mixins.accessor.IMinecraft;
import me.cumhax.soviet.mixin.mixins.accessor.ITimer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;

public class Timer extends Module {
   private final Setting speed = new Setting("Speed", this, 20, 1, 300);

   public Timer(String name, String description, Category category) {
      super(name, description, category);
   }

   @SubscribeEvent
   public void onTick(ClientTickEvent event) {
      ((ITimer)((IMinecraft)this.mc).getTimer()).setTickLength(50.0F / ((float)this.speed.getIntegerValue() / 10.0F));
   }

   public void onEnable() {
      LoggerUtil.sendMessage("Timer ON");
   }

   public void onDisable() {
      ((ITimer)((IMinecraft)this.mc).getTimer()).setTickLength(50.0F);
      LoggerUtil.sendMessage("Timer OFF");
   }
}
