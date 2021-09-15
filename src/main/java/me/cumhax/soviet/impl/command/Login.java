package me.cumhax.soviet.impl.command;

import me.cumhax.soviet.api.command.Command;
import me.cumhax.soviet.api.util.LoggerUtil;
import me.cumhax.soviet.api.util.SessionUtil;

public class Login extends Command {
   public Login(String name, String[] alias, String usage) {
      super(name, alias, usage);
   }

   public void onTrigger(String arguments) {
      String[] split = arguments.split(" ");

      try {
         if (split[0].equals("") || split[1].equals("")) {
            this.printUsage();
            return;
         }
      } catch (Exception var4) {
         this.printUsage();
         return;
      }

      if (SessionUtil.login(split[0], split[1])) {
         LoggerUtil.sendMessage("Logged in to " + SessionUtil.getSession().getUsername());
      } else {
         LoggerUtil.sendMessage("Failed to log in");
      }

   }
}
