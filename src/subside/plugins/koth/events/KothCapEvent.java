package subside.plugins.koth.events;

import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import subside.plugins.koth.adapter.Capable;
import subside.plugins.koth.adapter.Koth;
import subside.plugins.koth.adapter.RunningKoth;
import subside.plugins.koth.adapter.captypes.Capper;

/**
 * @author Thomas "SubSide" van den Bulk
 *
 */
public class KothCapEvent extends Event implements IEvent, Cancellable {
    private Capper nextCapper;
    private List<Player> playersInArea;
    private boolean isCancelled;
    private RunningKoth runningKoth;
    private Koth koth;
    private Capable captureZone;
    
    public KothCapEvent(RunningKoth runningKoth, Capable captureZone, List<Player> playersInArea, Capper nextCapper){
        this.runningKoth = runningKoth;
        this.koth = this.runningKoth.getKoth();
        this.playersInArea = playersInArea;
        this.nextCapper = nextCapper;
        this.captureZone = captureZone;
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

    public Capper getNextCapper() {
        return nextCapper;
    }

    public void setNextCapper(Capper nextCapper) {
        this.nextCapper = nextCapper;
    }

    public List<Player> getPlayersInArea() {
        return playersInArea;
    }

    public RunningKoth getRunningKoth() {
        return runningKoth;
    }

    @Override
    public Koth getKoth() {
        return koth;
    }

    public Capable getCaptureZone() {
        return captureZone;
    }
}
