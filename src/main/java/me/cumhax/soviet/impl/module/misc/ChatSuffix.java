package me.cumhax.soviet.impl.module.misc;

import me.cumhax.soviet.api.module.Category;
import me.cumhax.soviet.api.module.Module;
import me.cumhax.soviet.api.util.LoggerUtil;
import java.util.Arrays;
import java.util.Iterator;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ChatSuffix extends Module {
   public ChatSuffix(String name, String description, Category category) {
      super(name, description, category);
   }

   @SubscribeEvent
   public void onChat(ClientChatEvent event) {
      Iterator var2 = Arrays.asList("/", ".", "-", ",", ":", ";", "'", "\"", "+", "\\").iterator();

      String s;
      do {
         if (!var2.hasNext()) {
            event.setMessage(event.getMessage() + " ｜ SovietWare");
            return;
         }

         s = (String)var2.next();
      } while(!event.getMessage().startsWith(s));

   }

   public void onEnable() {
      LoggerUtil.sendMessage("Chat Suffix ON");
   }

   public void onDisable() {
      LoggerUtil.sendMessage("Chat Suffix OFF");
   }
}
