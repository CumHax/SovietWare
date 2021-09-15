package me.cumhax.soviet.impl.module.combat;

import me.cumhax.soviet.api.module.Category;
import me.cumhax.soviet.api.module.Module;
import me.cumhax.soviet.api.util.LoggerUtil;
import me.cumhax.soviet.impl.event.PacketSendEvent;
import net.minecraft.network.play.client.CPacketUseEntity;
import net.minecraft.network.play.client.CPacketPlayer.Position;
import net.minecraft.network.play.client.CPacketUseEntity.Action;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class Criticals extends Module {
   public Criticals(String name, String description, Category category) {
      super(name, description, category);
   }

   @SubscribeEvent
   public void onPacketSend(PacketSendEvent event) {
      if (!this.nullCheck()) {
         if (event.getPacket() instanceof CPacketUseEntity && ((CPacketUseEntity)event.getPacket()).getAction() == Action.ATTACK && this.mc.player.onGround) {
            this.mc.player.connection.sendPacket(new Position(this.mc.player.posX, this.mc.player.posY + 0.1D, this.mc.player.posZ, false));
            this.mc.player.connection.sendPacket(new Position(this.mc.player.posX, this.mc.player.posY, this.mc.player.posZ, false));
         }

      }
   }

   public void onEnable() {
      LoggerUtil.sendMessage("Criticals ON");
   }

   public void onDisable() {
      LoggerUtil.sendMessage("Criticals OFF");
   }
}
