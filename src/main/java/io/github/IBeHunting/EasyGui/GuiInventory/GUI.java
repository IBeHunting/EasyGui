package io.github.IBeHunting.EasyGui.GuiInventory;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class GUI implements InventoryHolder
{
   private String id;
   private Map<String, Object> info;
   private boolean updated;
   private Inventory inv;
   private GUI previous;
   private Map<Integer, ItemStack> contents;
   private Set<Integer> return_items;
   private ItemStack background;

   public GUI (int size, String title)
   {
      this.updated = false;
      this.background = new ItemStack(Material.AIR);
      this.contents = new HashMap<>();
      this.return_items = new HashSet<>();
      this.info = new HashMap<>();
      this.id = "";
      if (isValidSize(size))
         this.inv = Bukkit.createInventory(this, size, ChatColor.translateAlternateColorCodes('&', title));
      else
         this.inv = Bukkit.createInventory(this, 54, ChatColor.translateAlternateColorCodes('&', title));
   }

   public GUI (int size, String title, GUI previous)
   {
      this(size, title);
      this.previous = previous;
   }

   public <T> T getInfo (String tag, Class<T> type)
   {
      try
      {
         if (info.containsKey(tag))
         {
            return type.cast(info.get(tag));
         }
         return null;
      }
      catch (ClassCastException e)
      {
         return null;
      }
   }

   public void addInfo (String name, Object data)
   {
      this.info.put(name, data);
   }

   public void setId (String id)
   {
      this.id = id;
   }

   public String getId ()
   {
      return id;
   }

   public Inventory getInventory()
   {
      if (!updated)
      {
         updateInventory();
      }
      return inv;
   }

   public void addItem(ItemStack item)
   {
      for (int i = 0; i < inv.getSize(); i++)
      {
         if (!contents.containsKey(i))
         {
            contents.put(i, item);
            this.updated = false;
            return;
         }
      }
   }

   public void setItem (int slot, ItemStack item)
   {
      contents.put(slot, item);
      this.updated = false;
   }

   public void setReturnItem(int slot, ItemStack item)
   {
      contents.put(slot, item);
      this.updated = false;
      this.return_items.add(slot);
   }

   public void setBackGroundItem(ItemStack item)
   {
      this.background = item;
      this.updated = false;
   }

   public void setPreviousInventory (GUI prev)
   {
      this.previous = prev;
   }

   public void open(Player player)
   {
      player.openInventory(getInventory());
   }

   public void close(Player player)
   {
      player.closeInventory();
   }

   public GUI getPreviousInventory()
   {
      return previous;
   }

   public boolean isReturnItem (int slot)
   {
      return return_items.contains(slot);
   }

   private boolean isValidSize (int size)
   {
      return (size <= 54) && (size > 0) && (size % 9 == 0);
   }

   private void updateInventory()
   {
      ItemStack[] items = new ItemStack[inv.getSize()];
      Arrays.fill(items, background);
      for (Map.Entry<Integer, ItemStack> entry : contents.entrySet())
      {
         if (entry.getKey() < items.length)
         {
            items[entry.getKey()] = entry.getValue();
         }
      }
      this.inv.setContents(items);
      this.updated = true;
   }
}
