package io.github.IBeHunting.EasyGui;

import io.github.IBeHunting.EasyGui.API.GuiAPI;
import io.github.IBeHunting.EasyGui.GuiClick.InventoryClick;
import org.bukkit.plugin.java.JavaPlugin;

public class EasyGui extends JavaPlugin
{
   private static EasyGui plugin;
   private GuiAPI api;

   @Override
   public void onEnable()
   {
      plugin = this;
      getServer().getPluginManager().registerEvents(new InventoryClick(), this);
   }

   public static GuiAPI hookIntoAPI()
   {
      if (plugin.api == null)
         plugin.api = new GuiAPI();
      return plugin.api;
   }
}
