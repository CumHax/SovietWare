package me.cumhax.soviet.api;

import me.cumhax.soviet.Client;
import me.cumhax.soviet.api.module.Module;
import java.util.Iterator;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;
import org.lwjgl.input.Keyboard;

public class EventHandler {
   @SubscribeEvent
   public void onKeyInput(KeyInputEvent event) {
      if (Keyboard.getEventKeyState()) {
         Iterator var2 = Client.moduleManager.getModules().iterator();

         while(var2.hasNext()) {
            Module module = (Module)var2.next();
            if (module.getBind() == Keyboard.getEventKey()) {
               module.toggle();
            }
         }

      }
   }

   @SubscribeEvent
   public void onChatSend(ClientChatEvent event) {
      if (Minecraft.getMinecraft().player != null && Minecraft.getMinecraft().world != null) {
         if (event.getMessage().startsWith(Client.commandManager.getPrefix())) {
            event.setCanceled(true);
            Minecraft.getMinecraft().ingameGUI.getChatGUI().addToSentMessages(event.getMessage());
            Client.commandManager.runCommand(event.getMessage());
         }

      }
   }
}
