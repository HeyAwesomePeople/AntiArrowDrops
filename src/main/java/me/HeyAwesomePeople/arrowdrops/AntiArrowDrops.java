package me.HeyAwesomePeople.arrowdrops;

import org.bukkit.entity.Animals;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Monster;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.java.JavaPlugin;

public class AntiArrowDrops extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {}

    @EventHandler
    public void entityHitWithArrow(EntityDamageByEntityEvent e) {
        if (e.getEntity() instanceof Monster || e.getEntity() instanceof Animals) {
            Entity en = e.getEntity();
            if (e.getDamager() instanceof Arrow) {
                en.setMetadata("willdrop", new FixedMetadataValue(this, false));
            }
        }
    }

    @EventHandler
    public void entityDeath(EntityDeathEvent e) {
        if (e.getEntity() instanceof Monster || e.getEntity() instanceof Animals) {
            if (e.getEntity().getLastDamageCause().getCause() == EntityDamageEvent.DamageCause.FALL || e.getEntity().hasMetadata("willdrop")) {
                e.getDrops().clear();
            }
        }
    }

}
