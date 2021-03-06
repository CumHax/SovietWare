package me.cumhax.soviet.api.command;

import me.cumhax.soviet.api.util.LoggerUtil;
//import me.cumhax.soviet.impl.command.Font;
import me.cumhax.soviet.impl.command.Help;
import me.cumhax.soviet.impl.command.Login;
import me.cumhax.soviet.impl.command.Prefix;
import java.util.ArrayList;
import java.util.Iterator;

public class CommandManager {
   private final ArrayList commands = new ArrayList();
   private String prefix = ".";

   public CommandManager() {
      this.commands.add(new Help("Help", new String[]{"h", "help"}, "help"));
      this.commands.add(new Prefix("Prefix", new String[]{"prefix"}, "prefix <char>"));
      this.commands.add(new Login("Login", new String[]{"login"}, "login <email> <password>"));
   }

   public void runCommand(String args) {
      boolean found = false;
      String[] split = args.split(" ");
      String startCommand = split[0];
      String arguments = args.substring(startCommand.length()).trim();
      Iterator var6 = this.getCommands().iterator();

      while(var6.hasNext()) {
         Command command = (Command)var6.next();
         String[] var8 = command.getAlias();
         int var9 = var8.length;

         for(int var10 = 0; var10 < var9; ++var10) {
            String alias = var8[var10];
            if (startCommand.equals(this.getPrefix() + alias)) {
               command.onTrigger(arguments);
               found = true;
            }
         }
      }

      if (!found) {
         LoggerUtil.sendMessage("Unknown command");
      }

   }

   public ArrayList getCommands() {
      return this.commands;
   }

   public String getPrefix() {
      return this.prefix;
   }

   public void setPrefix(String prefix) {
      this.prefix = prefix;
   }
}
