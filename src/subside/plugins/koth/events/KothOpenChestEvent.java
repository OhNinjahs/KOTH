package subside.plugins.koth.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import subside.plugins.koth.adapter.Koth;

/**
 * @author Thomas "SubSide" van den Bulk
 *
 */
public class KothOpenChestEvent extends Event implements IEvent, Cancellable {

    private Player player;
    private boolean isCancelled;
    private Koth koth;
    
    public KothOpenChestEvent(Koth koth, Player player) {
        this.koth = koth;
        this.player = player;
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

    public Player getPlayer() {
        return player;
    }

    @Override
    public Koth getKoth() {
        return koth;
    }
}
