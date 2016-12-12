package subside.plugins.koth.events;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

import subside.plugins.koth.adapter.Koth;

/**
 * @author Thomas "SubSide" van den Bulk
 *
 */
public class KothChestCreationEvent extends Event implements IEvent, Cancellable {
    private boolean isCancelled;
    private Koth koth;
    private ItemStack[] loot;
    
    public KothChestCreationEvent(Koth koth, ItemStack[] loot){
        this.koth = koth;
        this.loot = loot;
    }
    
    
    @Override
    public boolean isCancelled() {
        return isCancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        isCancelled = cancel;
    }

    

    private static final HandlerList handlers = new HandlerList();

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    
    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public Koth getKoth() {
        return koth;
    }

    public ItemStack[] getLoot() {
        return loot;
    }

    public void setLoot(ItemStack[] loot) {
        this.loot = loot;
    }
}
