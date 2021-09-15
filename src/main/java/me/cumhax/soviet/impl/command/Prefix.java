package me.cumhax.soviet.impl.command;

import me.cumhax.soviet.Client;
import me.cumhax.soviet.api.command.Command;
import me.cumhax.soviet.api.util.LoggerUtil;

public class Prefix extends Command {
   public Prefix(String name, String[] alias, String usage) {
      super(name, alias, usage);
   }

   public void onTrigger(String arguments) {
      if (arguments.equals("")) {
         this.printUsage();
      } else {
         Client.commandManager.setPrefix(arguments);
         LoggerUtil.sendMessage("Prefix set to " + arguments);
      }
   }
}
