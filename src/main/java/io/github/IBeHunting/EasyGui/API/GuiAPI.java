package io.github.IBeHunting.EasyGui.API;

import io.github.IBeHunting.EasyGui.GuiInventory.GUI;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GuiAPI
{
   private static final String DEFAULT_TITLE = ChatColor.GOLD + "GUI";

   private GUI createGui()
   {
      return new GUI(54, DEFAULT_TITLE);
   }

   public GUI createGui(int size)
   {
      return new GUI(size, DEFAULT_TITLE);
   }

   public GUI createGui(int size, String title)
   {
      return new GUI(size, title);
   }

   public GUI createGui(String title)
   {
      return new GUI (54, title);
   }

   public GUI createGui(int size, String title, GUI prev)
   {
      return new GUI (size, title, prev);
   }

   public ItemStack addName (ItemStack item, String title)
   {
      ItemMeta meta = item.getItemMeta();
      if (meta != null)
      {
         meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', title));
         item.setItemMeta(meta);
      }
      return item;
   }

   public ItemStack addLore (ItemStack item, String ... lore)
   {
      List<String> final_lore = Arrays.stream(lore).map(
              line -> ChatColor.translateAlternateColorCodes('&', line)
      ).collect(Collectors.toList());
      ItemMeta meta = item.getItemMeta();
      if (meta != null)
      {
         meta.setLore(final_lore);
         item.setItemMeta(meta);
      }
      return item;
   }

}
