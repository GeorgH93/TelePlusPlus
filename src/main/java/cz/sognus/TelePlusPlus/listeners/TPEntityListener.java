package cz.sognus.TelePlusPlus.listeners;

import cz.sognus.TelePlusPlus.TelePlusPlus;
import org.bukkit.ChatColor;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.inventory.ItemStack;

public class TPEntityListener implements Listener {
    private final TelePlusPlus plugin;
    private long lastMoverAction = System.nanoTime();

    public TPEntityListener(TelePlusPlus plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        if (event.getCause().equals(DamageCause.FALL)) {
            if (event.getEntity() instanceof Player) {
                Player player = (Player) event.getEntity();

                if (plugin.pm.hasPermission(player, plugin.pm.tool) && !plugin.sm.disableTool && player.getEquipment().getItemInMainHand().getType().equals(plugin.sm.toolItem)) {
                    event.setCancelled(true);
                }

                if (plugin.pm.hasPermission(player, plugin.pm.mover) && !plugin.sm.disableMover && player.getEquipment().getItemInMainHand().getType().equals(plugin.sm.moverItem)) {
                    event.setCancelled(true);
                }

                if (plugin.gm.isFallDamageImmune(player)) {
                    event.setCancelled(true);
                }
            }
        }

        if (event instanceof EntityDamageByEntityEvent) {
            EntityDamageByEntityEvent sub = (EntityDamageByEntityEvent) event;
            if (sub.getDamager() instanceof Player) {
                Player player = (Player) sub.getDamager();
                Entity entity = sub.getEntity();
                ItemStack item = player.getEquipment().getItemInMainHand();

                if (item.getType().equals(plugin.sm.moverItem) && plugin.pm.hasPermission(player, plugin.pm.mover) && !plugin.sm.disableMover) {
                    event.setCancelled(true);

                    // Action cooldown
                    if(!timeElapsed()) {
                        if (plugin.sm.actionMessage)
                        {
                            player.sendMessage(ChatColor.DARK_PURPLE + "You need to wait before you tag another object!");
                        }
                        return;
                    }
                    plugin.mm.addMovedEntity(player, entity);
                    updateTime();

                    if (plugin.sm.sayMover) {
                        if (entity instanceof Player) {
                            player.sendMessage(ChatColor.DARK_PURPLE + "Player tagged");
                        } else if (entity instanceof Monster) {
                            player.sendMessage(ChatColor.DARK_PURPLE + "Mob tagged");
                        } else if (entity instanceof Animals) {
                            player.sendMessage(ChatColor.DARK_PURPLE + "Animal tagged");
                        } else if (entity instanceof Vehicle) {
                            player.sendMessage(ChatColor.DARK_PURPLE + "Vehicle tagged");
                        } else if (entity != null) {
                            player.sendMessage(ChatColor.DARK_PURPLE + "Entity tagged");
                        }
                    }
                }
            }
        }
    }

    private void updateTime()
    {
        lastMoverAction = System.nanoTime();
    }

    private boolean timeElapsed()
    {
        long estimatedTime = System.nanoTime() - lastMoverAction;
        return estimatedTime > plugin.sm.actionCooldown;
    }
}
