package subside.plugins.koth.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import subside.plugins.koth.adapter.Koth;
import subside.plugins.koth.adapter.RunningKoth.EndReason;
import subside.plugins.koth.adapter.captypes.Capper;

/**
 * @author Thomas "SubSide" van den Bulk
 *
 */
public class KothEndEvent extends Event implements IEvent {
    private Capper winner;
    private boolean creatingChest;
    private EndReason reason;
    private Koth koth;
    
    
    public KothEndEvent(Koth koth, Capper capper, EndReason reason){
        this.koth = koth;
        this.winner = capper;
        this.creatingChest = true;
        this.reason = reason;
    }
    

    private static final HandlerList handlers = new HandlerList();

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    
    public static HandlerList getHandlerList() {
        return handlers;
    }

    public Capper getWinner() {
        return winner;
    }

    public boolean isCreatingChest() {
        return creatingChest;
    }

    public void setCreatingChest(boolean creatingChest) {
        this.creatingChest = creatingChest;
    }

    public EndReason getReason() {
        return reason;
    }

    @Override
    public Koth getKoth() {
        return koth;
    }
}
