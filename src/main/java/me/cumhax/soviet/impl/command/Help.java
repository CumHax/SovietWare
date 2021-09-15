package me.cumhax.soviet.impl.command;

import me.cumhax.soviet.Client;
import me.cumhax.soviet.api.command.Command;
import me.cumhax.soviet.api.util.LoggerUtil;
import java.util.Iterator;

public class Help extends Command {
   public Help(String name, String[] alias, String usage) {
      super(name, alias, usage);
   }

   public void onTrigger(String arguments) {
      LoggerUtil.sendMessage("SovietWare");
      Iterator var2 = Client.commandManager.getCommands().iterator();

      while(var2.hasNext()) {
         Command command = (Command)var2.next();
         LoggerUtil.sendMessage(command.getName() + " - " + command.getUsage());
      }

   }
}
