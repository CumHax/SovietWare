package me.cumhax.soviet.api.module;

public enum Category {
   COMBAT("Combat"),
   EXPLOIT("Exploit"),
   RENDER("Render"),
   MOVEMENT("Movement"),
   MISC("Miscellaneous"),
   HUD("Hud");
   

   private String name;

   private Category(String name) {
      this.setName(name);
   }

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name;
   }
}
