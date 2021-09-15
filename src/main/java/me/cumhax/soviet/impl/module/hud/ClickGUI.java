package me.cumhax.soviet.impl.module.hud;

import me.cumhax.soviet.Client;
import me.cumhax.soviet.api.module.Category;
import me.cumhax.soviet.api.module.Module;
import me.cumhax.soviet.api.setting.Setting;
import me.cumhax.soviet.api.util.LoggerUtil;
import java.util.Arrays;

public class ClickGUI extends Module {
   public ClickGUI(String name, String description, Category category) {
      super(name, description, category);
      this.setBind(54);
   }

   public void onEnable() {
      this.mc.displayGuiScreen(Client.clickGUI);
      LoggerUtil.sendMessage("Click Gui ON");
   }

   public void onDisable() {
      LoggerUtil.sendMessage("Click Gui OFF");
   }
}
