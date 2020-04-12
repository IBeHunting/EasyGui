package io.github.IBeHunting.EasyGui.GuiClick;

import io.github.IBeHunting.EasyGui.GuiInventory.GUI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClick implements Listener
{
   @EventHandler
   public void onClick(InventoryClickEvent event)
   {
      if (event.getInventory().getHolder() instanceof GUI
              && event.getWhoClicked() instanceof Player
              && event.getClickedInventory() != null)
      {
         GUI gui = (GUI) event.getInventory().getHolder();
         Player player = (Player) event.getWhoClicked();
         GuiClickEvent guiEvent = new GuiClickEvent(
                 gui,
                 player,
                 event.getSlot(),
                 event.getSlot() == event.getRawSlot()
         );
         Bukkit.getPluginManager().callEvent(guiEvent);
         if (guiEvent.isCancelled())
         {
            event.setCancelled(true);
            if (guiEvent.isTopInventory() && gui.isReturnItem(event.getSlot()))
            {
               gui.close(player);
               if (gui.getPreviousInventory() != null)
               {
                  gui.getPreviousInventory().open(player);
               }
            }
         }
      }
   }
}
