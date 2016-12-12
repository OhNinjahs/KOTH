package subside.plugins.koth.events;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import subside.plugins.koth.adapter.Koth;

/**
 * @author Thomas "SubSide" van den Bulk
 *
 */
public class KothStartEvent extends Event implements IEvent, Cancellable {
    private int captureTime;
    private int maxLength;
    private Koth koth;
    private boolean scheduled;
    private String entityType;
    
    private boolean isCancelled;
    
    public KothStartEvent(Koth koth, int captureTime, int maxLength, boolean scheduled, String entityType){
        this.koth = koth;
        this.captureTime = captureTime;
        this.maxLength = maxLength;
        this.scheduled = scheduled;
        this.entityType = entityType;
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
    public boolean isCancelled() {
        return isCancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        isCancelled = cancel;
    }

    public int getCaptureTime() {
        return captureTime;
    }

    public void setCaptureTime(int captureTime) {
        this.captureTime = captureTime;
    }

    public int getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }

    @Override
    public Koth getKoth() {
        return koth;
    }

    public boolean isScheduled() {
        return scheduled;
    }

    public String getEntityType() {
        return entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }
}
