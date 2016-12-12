package subside.plugins.koth.events;

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
public class KothLeftEvent extends Event implements IEvent, Cancellable {
    private Capper capper;
    private boolean isCancelled;
    private int amountSecondsCapped;
    private Capper nextCapper;
    private RunningKoth runningKoth;
    private Koth koth;
    private Capable captureZone;
    
    public KothLeftEvent(RunningKoth runningKoth, Capable captureZone, Capper capper, int amountSecondsCapped){
        this.runningKoth = runningKoth;
        this.koth = this.runningKoth.getKoth();
        this.captureZone = captureZone;
        this.capper = capper;
        this.amountSecondsCapped = amountSecondsCapped;
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

    public Capper getCapper() {
        return capper;
    }

    public int getAmountSecondsCapped() {
        return amountSecondsCapped;
    }

    public Capper getNextCapper() {
        return nextCapper;
    }

    public void setNextCapper(Capper nextCapper) {
        this.nextCapper = nextCapper;
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
