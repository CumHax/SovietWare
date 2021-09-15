package me.cumhax.soviet.api.module;

import me.cumhax.soviet.Client;
import me.cumhax.soviet.api.setting.Setting;
import me.cumhax.soviet.impl.module.combat.*;
import me.cumhax.soviet.impl.module.exploit.*;
import me.cumhax.soviet.impl.module.misc.*;
import me.cumhax.soviet.impl.module.movement.*;
import me.cumhax.soviet.impl.module.render.*;
import me.cumhax.soviet.impl.module.hud.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class ModuleManager
{
	private final ArrayList<Module> modules = new ArrayList<>();

	public ModuleManager()
	{
		 //Combat
	  this.modules.add(new KillAura());
	  this.modules.add(new AntiTrap());
	  this.modules.add(new Auto32K());
	  this.modules.add(new AutoArmor());
	  this.modules.add(new CrystalAura());
      this.modules.add(new AutoLog("AutoLog", "Automatically logs out when your health is low", Category.COMBAT));
      this.modules.add(new AutoTrap("AutoTrap", "Traps players", Category.COMBAT));
	  this.modules.add(new BowSpam());
	  this.modules.add(new Criticals("Criticals", "Deal critical hits without jumping", Category.COMBAT));
	  this.modules.add(new Offhand());
	  this.modules.add(new Reach());
      this.modules.add(new Surround("Surround", "Places blocks around you", Category.COMBAT));
      this.modules.add(new Trapper());
      //this.modules.add(new CrystalAura("CrystalAura", "Places and brakes crystals", Category.COMBAT));
	  
	  //Exploit
	  this.modules.add(new AntiHunger());
      this.modules.add(new Blink("Blink", "Fake lag", Category.EXPLOIT));
	  this.modules.add(new Dupe());
	  this.modules.add(new EchestBP());
	  this.modules.add(new EntitySpeed());
	  this.modules.add(new HitBox());
	  this.modules.add(new NoSwing());
      this.modules.add(new PacketMine("PacketMine", "Mine blocks with packets", Category.EXPLOIT));
	  this.modules.add(new PortalGodmode());
	  
	  //Misc
	  this.modules.add(new ChatSuffix("ChatSuffix", "Adds a suffix to your chat messages", Category.MISC));
	  this.modules.add(new DiscordRPC());
	  this.modules.add(new FakePlayer());
	  this.modules.add(new FastUse());
	  this.modules.add(new MiddleClickPearl());
	  this.modules.add(new NoBedtrapMsg());
	  this.modules.add(new Refill());
      this.modules.add(new Timer("Timer", "Speeds up your game", Category.MISC));
	  this.modules.add(new VisualRange());
	  this.modules.add(new YawLock());
	  
	  //Movement
	  this.modules.add(new AntiVoid());
	  this.modules.add(new FastWeb());
	  this.modules.add(new Flight());
	  this.modules.add(new HoleTP());
      this.modules.add(new LongJump("LongJump", "Jumps far", Category.MOVEMENT));
	  this.modules.add(new NoSlow());
	  this.modules.add(new Speed("Strafe", "Allows you to move faster", Category.MOVEMENT));
      this.modules.add(new Sprint("Sprint", "Sprints, Obviously", Category.MOVEMENT));
	  this.modules.add(new Step());
	  this.modules.add(new Velocity());
	  
	  //Render
	  this.modules.add(new BlockHighlight());
	  this.modules.add(new Chams());
	  this.modules.add(new ItemViewmodle());
	  this.modules.add(new Fullbright());
	  this.modules.add(new HoleESP());
	  this.modules.add(new NoHurtCam());
	  this.modules.add(new NoWeather());
	  this.modules.add(new VoidESP());
	  
	  //Hud
     this.modules.add(new ClickGUI("ClickGUI", "Toggle modules by clicking on them", Category.HUD));
	 this.modules.add(new Hud());
		
		for (Module module : modules) {
			for (Field declaredField : module.getClass().getDeclaredFields()) {
				declaredField.setAccessible(true);
				if (declaredField.getType() == Setting.class) {
					try {
						Client.settingManager.addSetting((Setting) declaredField.get(module));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	public static boolean isModuleEnabled ( String zoom ) {
		return false;
	}

	public ArrayList<Module> getModules()
	{
		return modules;
	}

	public Module getModule(String name)
	{
		for (Module module : modules)
		{
			if (module.getName().equalsIgnoreCase(name)) return module;
		}

		return null;
	}

	public ArrayList<Module> getModules(Category category)
	{
		ArrayList<Module> mods = new ArrayList<>();

		for (Module module : modules)
		{
			if (module.getCategory().equals(category)) mods.add(module);
		}

		return mods;
	}

	public ArrayList<Module> getEnabledModules()
	{
		return modules.stream().filter(Module::isEnabled).collect(Collectors.toCollection(ArrayList::new));
	}

	public Module getModuleByName ( String moduleName ) {
		return null;
	}
}
