package io.github.IBeHunting.EasyGui.GuiClick;

import io.github.IBeHunting.EasyGui.GuiInventory.GUI;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class GuiClickEvent extends Event implements Cancellable
{
   private static final HandlerList HANDLERS = new HandlerList();

   private GUI gui;
   private Player clicked;
   private int slot;
   private boolean top;
   private boolean cancelEvent;

   GuiClickEvent(GUI gui, Player clicked, int slot, boolean top)
   {
      this.gui = gui;
      this.clicked = clicked;
      this.slot = slot;
      this.top = top;
      this.cancelEvent = true;
   }

   public HandlerList getHandlers() {
      return HANDLERS;
   }

   public static HandlerList getHandlerList() {
      return HANDLERS;
   }

   public GUI getGUI()
   {
      return gui;
   }

   public Player getPlayer()
   {
      return clicked;
   }

   public int getSlot()
   {
      return slot;
   }

   public boolean isTopInventory()
   {
      return top;
   }

   public void setCancelled(boolean cancel)
   {
      this.cancelEvent = cancel;
   }

   public boolean isCancelled()
   {
      return cancelEvent;
   }
}
